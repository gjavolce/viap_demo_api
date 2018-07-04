package com.viaplay.api.integrations.impl;


import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.viaplay.api.exceptions.ViaplayCoverArtArchiveUnparsableResponseException;
import com.viaplay.api.integrations.CoverArtArchiveService;
import com.viaplay.api.models.coverart.CoverArtArchiveResponse;
import com.viaplay.api.utils.PropertiesCache;
import com.viaplay.api.utils.RestConsumer;
import com.viaplay.api.utils.UriBuilderHelper;
import com.viaplay.api.utils.ViaplayValidators;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.viaplay.api.utils.ViaplayApiConstants.COVERART_API_URL_ERROR_UNPARSABLE_RESPONSE;


@Service("coverArtArchiveService")
public class CoverArtArchiveServiceImpl implements CoverArtArchiveService {

    PropertiesCache props = PropertiesCache.getInstance();

    @HystrixCommand(
            fallbackMethod = "defaultCoverArtsForAlbumsInList",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            })
    public Map<String, CoverArtArchiveResponse> getAllCoverArtsForAlbumsInList(List<String> coverArtIdList) {

        List<String> validatedCoverArtIds = validateCoverArtIdList(coverArtIdList);

        return getCoverArtStringResponses(validatedCoverArtIds);
    }

    private Map<String, CoverArtArchiveResponse> defaultCoverArtsForAlbumsInList(List<String> coverArtIdList) {

        return new HashMap<>();

    }

    private List<String> validateCoverArtIdList(List<String> coverArtIdList) {

        return coverArtIdList
                .stream()
                .filter(ViaplayValidators::validateMbid)
                .collect(Collectors.toList());

    }

    private Map<String, CoverArtArchiveResponse> getCoverArtStringResponses(List<String> coverArtIdList) {

        Map<String, CoverArtArchiveResponse> resultList = new HashMap<>();

        coverArtIdList
                .parallelStream()
                .forEach(s -> consumeCoverArtElementById(s)
                        .ifPresent(t -> resultList.put(s, t)));

        return resultList;

    }

    private Optional<CoverArtArchiveResponse> consumeCoverArtElementById(String id) {

        RestConsumer consumer = new RestConsumer();

        consumer.setConsumerDataURL(UriBuilderHelper.getCoverArtArchiveUri(id).toUriString());

        ResponseEntity<String> response = consumer.getRestResponse();

        if (response.getStatusCode().is3xxRedirection()) {

            consumer.setConsumerDataURL(response.getHeaders().get(0).get(0));

            response = consumer.getRestResponse();

        }

        try {

            return Optional.of(parseCoverArtResponse(response));

        } catch (ViaplayCoverArtArchiveUnparsableResponseException ex) {

            return Optional.empty();

        }

    }

    private CoverArtArchiveResponse parseCoverArtResponse(ResponseEntity<String> stringResponse)
            throws ViaplayCoverArtArchiveUnparsableResponseException {

        try {

            CoverArtArchiveResponse result = new Gson()
                    .fromJson(stringResponse.getBody(),
                            CoverArtArchiveResponse.class);

            // hack for reported problem of Gson not throwing exceptions when parsing
            if (result != null) {

                return result;

            } else {

                throw new JsonParseException("");
            }


        } catch (Exception e1) {

            throw new ViaplayCoverArtArchiveUnparsableResponseException(
                    props.getProperty(COVERART_API_URL_ERROR_UNPARSABLE_RESPONSE));

        }

    }


}
