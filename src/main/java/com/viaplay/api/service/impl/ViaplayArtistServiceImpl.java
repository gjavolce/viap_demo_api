package com.viaplay.api.service.impl;

import com.viaplay.api.exceptions.*;
import com.viaplay.api.integrations.CoverArtArchiveService;
import com.viaplay.api.integrations.DiscogsService;
import com.viaplay.api.integrations.MusicBrainzAlbumService;
import com.viaplay.api.models.coverart.CoverArtArchiveResponse;
import com.viaplay.api.models.discogs.DiscogsResponse;
import com.viaplay.api.models.musicbrainz.MusicBrainzResponse;
import com.viaplay.api.models.musicbrainz.ReleaseGroup;
import com.viaplay.api.models.viaplay.ViaplayMusicAlbum;
import com.viaplay.api.models.viaplay.ViaplayArtistResponse;
import com.viaplay.api.service.ViaplayArtistService;
import com.viaplay.api.utils.PropertiesCache;
import com.viaplay.api.utils.UriBuilderHelper;
import com.viaplay.api.utils.ViaplayValidators;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.viaplay.api.utils.ViaplayApiConstants.*;

@Service("viaplayArtistService")
public class ViaplayArtistServiceImpl implements ViaplayArtistService {

    @Autowired
    @Qualifier("musicBrainzService")
    MusicBrainzAlbumService musicBrainzAlbumService;

    @Autowired
    @Qualifier("coverArtArchiveService")
    CoverArtArchiveService coverArtArchiveService;

    @Autowired
    @Qualifier("discogsService")
    DiscogsService discogsService;

    PropertiesCache props = PropertiesCache.getInstance();


    @Override
    @Cacheable(value = "artistDetails", key = "#mbid")
    public ViaplayArtistResponse getAlbumData(String mbid) throws
            ViaplayValidationException,
            ViaplayMusicBrainzIdNotFoundException,
            ViaplayMusicBrainzInvalidIdException,
            ViaplayMusicBrainzServiceUnavailableException,
            ViaplayMusicBrainzUnparsableResultException,
            ViaplayServiceException {

        try {

            validateMbidFormat(mbid);

            MusicBrainzResponse musicBrainzResponse = musicBrainzAlbumService.getMusicBrainzAlbum(mbid);

            List<String> coverIds = musicBrainzResponse
                    .getReleaseGroups()
                    .stream()
                    .map(ReleaseGroup::getId)
                    .collect(Collectors.toList());

            Optional<String> discogsId = musicBrainzResponse
                    .getRelations()
                    .stream()
                    .filter(s -> s.getType()
                            .equalsIgnoreCase(props.getProperty(MUSICBRAINZ_DISCOGS_IDENTIFIER)))
                    .filter(s -> s.getUrl() != null)
                    .map(s -> UriBuilderHelper.getDiscogsIdFromUrl(s.getUrl().getResource()))
                    .findFirst();

            Optional<DiscogsResponse> discogsDataResponse = Optional.empty();

            if (discogsId.isPresent()) {
                discogsDataResponse = discogsService.getDiscogsData(discogsId.get());
            }

            Map<String, CoverArtArchiveResponse> covertArchiveResponseMap =
                    coverArtArchiveService.getAllCoverArtsForAlbumsInList(coverIds);

            return combineMusicBrainzAndCoverArtData(musicBrainzResponse, covertArchiveResponseMap, discogsDataResponse);

        } catch (com.netflix.hystrix.exception.HystrixRuntimeException e) {

            throw new ViaplayServiceException(props.getProperty(VIAPLAY_SERVICE_UNAVAILABLE));

        }
    }

    public ViaplayArtistResponse combineMusicBrainzAndCoverArtData(
            MusicBrainzResponse musicBrainzResponse,
            Map<String, CoverArtArchiveResponse> coverArtArchiveResponseMap,
            Optional<DiscogsResponse> discogsData) {

        List<ViaplayMusicAlbum> resultList = musicBrainzResponse
                .getReleaseGroups()
                .stream()
                .map(a -> new ViaplayMusicAlbum(a.getTitle(),
                       a.getId(),
                        extractImageFromCoverArtMapById(coverArtArchiveResponseMap, a.getId())))
                .collect(Collectors.toList());

        return new ViaplayArtistResponse(musicBrainzResponse.getId(),
                discogsData.isPresent() ? discogsData.get().getProfile() : musicBrainzResponse.getName(),
                resultList);

    }

    private void validateMbidFormat(String mbid) throws ViaplayValidationException {

        if(StringUtils.isEmpty(mbid) || !ViaplayValidators.validateMbid(mbid)) {

            throw new ViaplayValidationException(props.getProperty(MESSAGE_ERROR_INVALID_MBID));

        }

    }

    protected String extractImageFromCoverArtMapById(Map<String, CoverArtArchiveResponse> map, String id) {

        if (map.containsKey(id)
                && map.get(id).getImages() != null
                && !map.get(id).getImages().isEmpty()) {

            return map.get(id).getImages()
                    .stream()
                    .filter(s ->
                            containsCaseInsensitive(props.getProperty(COVERART_COVER_FRONT_LABEL), s.getTypes())
                             ||containsCaseInsensitive(props.getProperty(COVERART_COVER_BACK_LABEL), s.getTypes()))
                    .findFirst()
                    .get()
                    .getImage();
        }

        return null;
    }

    public boolean containsCaseInsensitive(String s, List<String> l){
        return l.stream().anyMatch(x -> x.equalsIgnoreCase(s));
    }


}
