package com.viaplay.api.exceptions;

public class ViaplayMusicBrainzIdNotFoundException extends Exception {

    public ViaplayMusicBrainzIdNotFoundException(String message) {
        super(message);
    }

    public ViaplayMusicBrainzIdNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
