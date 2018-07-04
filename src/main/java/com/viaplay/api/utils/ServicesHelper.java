package com.viaplay.api.utils;

import java.util.HashMap;
import java.util.Map;
import static com.viaplay.api.utils.ViaplayApiConstants.*;

public class ServicesHelper {
    private static ServicesHelper instance = null;
    private static PropertiesCache props = PropertiesCache.getInstance();

    private final static Map<String, String> servicesMap = new HashMap<>();

    private ServicesHelper() {
        // Exists only to defeat instantiation.
    }
    public static ServicesHelper getInstance() {
        if(instance == null) {
            servicesMap.put(props.getProperty(MUSICBRAINZ_HEALTHCHECK_LABEL), props.getProperty(MUSICBRAINZ_API_PING_URL));
            servicesMap.put(props.getProperty(DISCOGS_HEALTHCHECK_LABEL), props.getProperty(DISCOGS_API_URL));
            servicesMap.put(props.getProperty(COVERART_HEALTHCHECK_LABEL), props.getProperty(COVERART_ARCHIVE_API_URL));
            instance = new ServicesHelper();
        }
        return instance;
    }

    public Map<String, String> servicesMap() {
        return servicesMap;
    }
}