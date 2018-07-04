package com.viaplay.api.exceptions;

public class ViaplayServiceException extends Exception {

    public ViaplayServiceException(String message) {
        super(message);
    }

    public ViaplayServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
