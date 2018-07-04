package com.viaplay.api.utils;

import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class RestConsumer {

    private RestTemplate defaultRestTemplate;

    private String consumerDataURL;

    private HttpHeaders headers;

    public RestConsumer() {
        this.defaultRestTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.keySet();
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        defaultRestTemplate.setErrorHandler(new ResponseErrorHandler());
    }

    public RestConsumer(String forUrl) {
        this();
        this.consumerDataURL = forUrl;
    }


    public ResponseEntity<String> getRestResponse() throws RestClientException {
        return getConsumerRestTemplate()
                .exchange(this.consumerDataURL, HttpMethod.GET,null, String.class);
    }

    public RestTemplate getConsumerRestTemplate() {
        return defaultRestTemplate;
    }

    public void setConsumerDataURL(String url) {
        this.consumerDataURL = url;
    }


}
