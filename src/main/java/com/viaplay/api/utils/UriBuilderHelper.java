package com.viaplay.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import static com.viaplay.api.utils.ViaplayApiConstants.*;

@Slf4j
public class UriBuilderHelper {

    private static PropertiesCache props = PropertiesCache.getInstance();

    public static UriComponents getMusicBrainzUri(String mbid) {

        log.info("Building MusicBrainz URI for MBID: {}. ", mbid);

        return UriComponentsBuilder
                .fromHttpUrl(props.getProperty(MUSICBRAINZ_API_URL))
                .path(props.getProperty(MUSICBRAINZ_API_URL_ARTIST_SUBPATH))
                .path("/{mbid}")
                .queryParam(props.getProperty(MUSICBRAINZ_API_URL_FORMAT_NAME),
                        props.getProperty(MUSICBRAINZ_API_URL_FORMAT_VALUE))
                .queryParam(props.getProperty(MUSICBRAINZ_API_URL_INCLUDE_NAME),
                        props.getProperty(MUSICBRAINZ_API_URL_INCLUDE_VALUE))
                .queryParam(props.getProperty(MUSICBRAINZ_API_URL_TYPE_NAME),
                        props.getProperty(MUSICBRAINZ_API_URL_TYPE_VALUE))
                .buildAndExpand(mbid);

    }

    public static UriComponents getCoverArtArchiveUri(String mbid) {

        log.info("Building CoverArt URI for MBID: {}. ", mbid);

        return UriComponentsBuilder
                .fromHttpUrl(props.getProperty(COVERART_ARCHIVE_API_URL))
                .path(props.getProperty(COVERART_ARCHIVE_API_RELEASE_SUBPATH))
                .path("/{mbid}")
                .buildAndExpand(mbid);

    }

    public static UriComponents getDiscogsUri(String id) {

        log.info("Building Discogs URI for ID: {}. ", id);

        return UriComponentsBuilder
                .fromHttpUrl(props.getProperty(DISCOGS_API_URL))
                .path(props.getProperty(DISCOGS_API_ARTISTS_PATH))
                .path("/{id}")
                .buildAndExpand(id);

    }

    public static String getDiscogsIdFromUrl(String url) {
        return url.replaceFirst(".*/([^/?]+).*", "$1");
    }

}
