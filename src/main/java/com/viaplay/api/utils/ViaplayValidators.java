package com.viaplay.api.utils;

import org.apache.commons.lang.StringUtils;

import java.util.List;

public class ViaplayValidators {

    public static boolean validateMbid(String mbid) {

        return mbid != null && !mbid.isEmpty() ? mbid.matches(ViaplayApiConstants.UUID_REGEX) : false;

    }

}
