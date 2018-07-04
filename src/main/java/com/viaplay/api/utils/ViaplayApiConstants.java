package com.viaplay.api.utils;

public class ViaplayApiConstants {

    public static final String UUID_REGEX =
            "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}";

    // Constants based on MusicBrainz API specification
    // http://musicbrainz.org/doc/Development/XML_Web_Service/Version_2

    public static final String MUSICBRAINZ_API_URL = "musicbrainz.api.url";

    public static final String MUSICBRAINZ_API_URL_ARTIST_SUBPATH = "musicbrainz.api.url.artist.subpath";

    public static final String MUSICBRAINZ_API_URL_FORMAT_NAME = "musicbrainz.api.url.format.name";
    public static final String MUSICBRAINZ_API_URL_FORMAT_VALUE = "musicbrainz.api.url.format.value";

    public static final String MUSICBRAINZ_API_URL_INCLUDE_NAME = "musicbrainz.api.url.include.name";
    public static final String MUSICBRAINZ_API_URL_INCLUDE_VALUE = "musicbrainz.api.url.include.value";

    public static final String MUSICBRAINZ_API_URL_TYPE_NAME = "musicbrainz.api.url.type.name";
    public static final String MUSICBRAINZ_API_URL_TYPE_VALUE = "musicbrainz.api.url.type.value";


    public static final String MUSICBRAINZ_API_URL_ERROR_KEY = "musicbrainz.api.url.error.key";
    public static final String MUSICBRAINZ_API_URL_ERROR_VALUE_INVALID = "musicbrainz.api.url.error.value.invalid";
    public static final String MUSICBRAINZ_API_URL_ERROR_VALUE_NOT_FOUND = "musicbrainz.api.url.error.value.not.found";

    public static final String MUSICBRAINZ_API_PING_URL = "musicbrainz.api.ping.url";

    // Constants based on CoverArtArchive API specification

    public static final String COVERART_ARCHIVE_API_URL = "coverart.api.url";
    public static final String COVERART_ARCHIVE_API_RELEASE_SUBPATH = "coverart.archive.api.release.subpath";

    // Constants based on Discogs API specification

    public static final String DISCOGS_API_URL = "discogs.api.url";
    public static final String DISCOGS_API_ARTISTS_PATH = "discogs.api.artists.path";

    public static final String MUSICBRAINZ_DISCOGS_IDENTIFIER = "musibrainz.discogs.identifier";





    public static final String MESSAGE_ERROR_MBID_SERVICE_UNAVAILABLE = "message.error.mbid.service.unavailable";

    public static final String MESSAGE_ERROR_INVALID_MBID = "message.error.invalid.mbid";

    public static final String MESSAGE_ERROR_MBID_NOT_FOUND = "message.error.mbid.not.found";

    public static final String MUSICBRAINZ_ERROR_UNPARSABLE_RESPONSE = "musicbrainz.error.unparsable.response";


    public static final String COVERART_API_URL_ERROR_UNPARSABLE_RESPONSE = "coverart.api.url.error.unparsable.response";


    public static final String MUSICBRAINZ_HEALTHCHECK_LABEL = "musicbrainz.healthcheck.label";
    public static final String COVERART_HEALTHCHECK_LABEL = "coverart.healthcheck.label";
    public static final String DISCOGS_HEALTHCHECK_LABEL = "discogs.healthcheck.label";

    public static final String HEALTHCHECK_SERVICE_UP = "healthcheck.service.up";
    public static final String HEALTHCHECK_SERVICE_DOWN = "healthcheck.service.down";
    public static final String HEALTHCHECK_TIMEOUT_TIME = "healthcheck.timeout.time";

    public static final String VIAPLAY_SERVICE_UNAVAILABLE = "viaplay.api.service.unavailable";

    public static final String VIAPLAY_SERVICE_ERROR_LABEL = "viaplay.service.error.label";

    public static final String COVERART_COVER_FRONT_LABEL = "coverart.cover.front.label";
    public static final String COVERART_COVER_BACK_LABEL = "coverart.cover.back.label";


}
