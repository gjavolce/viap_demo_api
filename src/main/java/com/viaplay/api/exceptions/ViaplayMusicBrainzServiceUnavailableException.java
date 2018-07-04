package com.viaplay.api.exceptions;

public class ViaplayMusicBrainzServiceUnavailableException extends Exception {

    public ViaplayMusicBrainzServiceUnavailableException(String message) {
        super(message);
    }

    public ViaplayMusicBrainzServiceUnavailableException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ViaplayMusicBrainzServiceUnavailableException() {

    }
}
