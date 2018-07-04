package com.viaplay.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;


@Slf4j
public class ResponseErrorHandler implements org.springframework.web.client.ResponseErrorHandler {


    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.warn("Response error: {} {}", response.getStatusCode(), response.getStatusText());
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return RestUtil.isError(response.getStatusCode());
    }
}