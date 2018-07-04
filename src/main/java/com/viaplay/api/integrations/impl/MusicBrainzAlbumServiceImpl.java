package com.viaplay.api.integrations.impl;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.viaplay.api.exceptions.*;
import com.viaplay.api.utils.PropertiesCache;
import com.viaplay.api.utils.RestConsumer;
import com.viaplay.api.models.musicbrainz.MusicBrainzResponse;
import com.viaplay.api.integrations.MusicBrainzAlbumService;
import com.viaplay.api.utils.UriBuilderHelper;
import com.viaplay.api.utils.ViaplayApiConstants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.viaplay.api.utils.ViaplayApiConstants.*;

@Service("musicBrainzService")
public class MusicBrainzAlbumServiceImpl implements MusicBrainzAlbumService {

    private static PropertiesCache props = PropertiesCache.getInstance();

    @HystrixCommand(
            fallbackMethod = "defaultMusicBrainzAlbum",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
            }
    )
    public MusicBrainzResponse getMusicBrainzAlbum(String mbid) throws
            ViaplayMusicBrainzIdNotFoundException,
            ViaplayMusicBrainzServiceUnavailableException,
            ViaplayMusicBrainzInvalidIdException,
            ViaplayMusicBrainzUnparsableResultException{

        String requestUrl = UriBuilderHelper.getMusicBrainzUri(mbid).toUriString();

        RestConsumer musicBrainzConsumer = new RestConsumer(requestUrl);

        ResponseEntity<String> response = musicBrainzConsumer.getRestResponse();

        validateMusicBrainzResponse(response);

        return parseResponse(response);

    }

    public MusicBrainzResponse defaultMusicBrainzAlbum(String mbid) throws
            ViaplayMusicBrainzServiceUnavailableException {

        throw new ViaplayMusicBrainzServiceUnavailableException(
                props.getProperty(MESSAGE_ERROR_MBID_SERVICE_UNAVAILABLE));

    }


    private void validateMusicBrainzResponse(ResponseEntity<String> response) throws
            ViaplayMusicBrainzIdNotFoundException,
            ViaplayMusicBrainzServiceUnavailableException,
            ViaplayMusicBrainzInvalidIdException {

        if (response.getStatusCode().is4xxClientError()) {

            JsonObject jsonObject = new Gson().fromJson(response.getBody(), JsonObject.class);

            String message = jsonObject.get(props.getProperty(MUSICBRAINZ_API_URL_ERROR_KEY)).getAsString();

            if (props.getProperty(MUSICBRAINZ_API_URL_ERROR_VALUE_NOT_FOUND).equalsIgnoreCase(message)) {
                throw new ViaplayMusicBrainzIdNotFoundException(props.getProperty(MESSAGE_ERROR_MBID_NOT_FOUND));
            }

            if (ViaplayApiConstants.MUSICBRAINZ_API_URL_ERROR_VALUE_INVALID.equalsIgnoreCase(message)) {
                throw new ViaplayMusicBrainzInvalidIdException(props.getProperty(MUSICBRAINZ_ERROR_UNPARSABLE_RESPONSE));
            }

        } else if (response.getStatusCode().is5xxServerError()) {

            throw new ViaplayMusicBrainzServiceUnavailableException(
                    props.getProperty(MESSAGE_ERROR_MBID_SERVICE_UNAVAILABLE));

        } else {

            if(StringUtils.isEmpty(response.getBody())) {

                throw new ViaplayMusicBrainzServiceUnavailableException(
                        props.getProperty(MESSAGE_ERROR_MBID_SERVICE_UNAVAILABLE));

            }

        }

    }

    private MusicBrainzResponse parseResponse(ResponseEntity<String> response)
            throws ViaplayMusicBrainzUnparsableResultException {

        try {

            MusicBrainzResponse result = new Gson().fromJson(response.getBody(), MusicBrainzResponse.class);

            // hack for reported problem of Gson not throwing exceptions when parsing
            if (result != null) {
                return result;
            } else {
                throw new JsonParseException("");
            }

        } catch (Exception e1) {
            throw new ViaplayMusicBrainzUnparsableResultException(
                    props.getProperty(MUSICBRAINZ_ERROR_UNPARSABLE_RESPONSE));
        }

    }

}
