package com.viaplay.api.exceptions;

public class ViaplayValidationException extends Exception {

    public ViaplayValidationException(String message) {
        super(message);
    }

    public ViaplayValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
