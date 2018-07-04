package com.viaplay.api.utils;

import com.viaplay.api.models.coverart.CoverArtArchiveResponse;
import com.viaplay.api.models.discogs.DiscogsResponse;
import com.viaplay.api.models.musicbrainz.MusicBrainzResponse;
import com.viaplay.api.models.musicbrainz.ReleaseGroup;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.*;
import java.util.stream.Collectors;

import static com.viaplay.api.utils.ViaplayApiConstants.COVERART_COVER_FRONT_LABEL;
import static com.viaplay.api.utils.ViaplayApiConstants.MUSICBRAINZ_DISCOGS_IDENTIFIER;

public class ModelGeneratorHelperTest {


    private TestPropertiesCacheTest props = TestPropertiesCacheTest.getInstance();

    private PodamFactory factory;

    public ModelGeneratorHelperTest() {

        this.factory = new PodamFactoryImpl();

        this.factory.getStrategy().addOrReplaceTypeManufacturer(String.class, new CustomStringManufacturerTest());

    }

    public MusicBrainzResponse generateMusicBrainzResponse() {

        MusicBrainzResponse mockMusicBrainzResponse = factory.manufacturePojo(MusicBrainzResponse.class);

        mockMusicBrainzResponse.getRelations().get(0).setType(props.getProperty(MUSICBRAINZ_DISCOGS_IDENTIFIER));

        System.out.println(mockMusicBrainzResponse.getRelations().get(0).getUrl().getResource());

        return mockMusicBrainzResponse;

    }

    public CoverArtArchiveResponse generateArtArchiveResponse() {

        CoverArtArchiveResponse mockCoverArtResponse = factory.manufacturePojo(CoverArtArchiveResponse.class);

        mockCoverArtResponse.getImages().get(0).setTypes(List.of(props.getProperty(COVERART_COVER_FRONT_LABEL)));

        mockCoverArtResponse.setImages(List.of(mockCoverArtResponse.getImages().get(0)));

//        mockCoverArtResponse.getImages().get(0)
        return mockCoverArtResponse;
    }

    public List<CoverArtArchiveResponse> generateListOfCoverArtArchiveResponses(int listSize) {


        List<CoverArtArchiveResponse> mockCoverArtArchiveResponseList = new ArrayList<>();

        do {

            mockCoverArtArchiveResponseList.add(generateArtArchiveResponse());

            listSize--;

        } while(listSize > 0);

        return mockCoverArtArchiveResponseList;

    }

    public DiscogsResponse generateDiscogsResponse() {

        return factory.manufacturePojo(DiscogsResponse.class);

    }

    public Map<String, CoverArtArchiveResponse> generateMapOfAlbumIdsAndCoverArtResponse(List<String> albumIds, List<CoverArtArchiveResponse> coverArtList) {

        Map<String, CoverArtArchiveResponse> mockedMap = new HashMap<>();



        for(int i = 0; i < albumIds.size(); i++) {

            mockedMap.put(albumIds.get(i), coverArtList.get(i));

        }

        return mockedMap;

    }

    public List<String> getCoverArtIdsFromMusicBrainzresponse(MusicBrainzResponse mockMusicBrainzResponse) {

        return mockMusicBrainzResponse.getReleaseGroups()
            .stream()
                .map(ReleaseGroup::getId)
                .collect(Collectors.toList());

    }

    public Optional<String> getDiscogsIdentifierFromMusicBrainzresponse(MusicBrainzResponse mocMusicBrainzResponse) {

        return mocMusicBrainzResponse
                .getRelations()
                .stream()
                .filter(s -> s.getType().equalsIgnoreCase(props.getProperty(MUSICBRAINZ_DISCOGS_IDENTIFIER)))
                .filter(s -> s.getUrl() != null)
                .map(s -> UriBuilderHelper.getDiscogsIdFromUrl(s.getUrl().getResource()))
                .findFirst();

    }

}
