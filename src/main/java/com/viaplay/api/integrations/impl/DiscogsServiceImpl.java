package com.viaplay.api.integrations.impl;

import com.viaplay.api.models.discogs.DiscogsResponse;
import com.viaplay.api.integrations.DiscogsService;
import com.viaplay.api.utils.RestConsumer;
import com.viaplay.api.utils.UriBuilderHelper;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("discogsService")
public class DiscogsServiceImpl implements DiscogsService {

    @HystrixCommand(fallbackMethod = "defaultDiscogResponse")
    public Optional<DiscogsResponse> getDiscogsData(String id) {

        String requestUrl = UriBuilderHelper.getDiscogsUri(id).toUriString();

        RestConsumer discogsConsumer = new RestConsumer(requestUrl);

        return Optional.ofNullable(parseResponse(discogsConsumer.getRestResponse()));

    }

    public Optional<DiscogsResponse> defaultDiscogResponse(String id) {

        DiscogsResponse temp = new DiscogsResponse();
        temp.setProfile("-- default profile --");
        return Optional.of(temp);

    }

    private DiscogsResponse parseResponse(ResponseEntity<String> response) {


        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {

            return new Gson().fromJson(response.getBody(), DiscogsResponse.class);
            
        } else {

            // logger

            return null;

        }

    }


}
