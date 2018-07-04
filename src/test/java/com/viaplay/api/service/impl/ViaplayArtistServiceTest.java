package com.viaplay.api.service.impl;

import com.google.gson.Gson;
import com.viaplay.api.exceptions.ViaplayValidationException;
import com.viaplay.api.integrations.CoverArtArchiveService;
import com.viaplay.api.integrations.DiscogsService;
import com.viaplay.api.integrations.MusicBrainzAlbumService;
import com.viaplay.api.models.coverart.CoverArtArchiveResponse;
import com.viaplay.api.models.discogs.DiscogsResponse;
import com.viaplay.api.models.musicbrainz.MusicBrainzResponse;
import com.viaplay.api.models.viaplay.ViaplayArtistResponse;
import com.viaplay.api.service.ViaplayArtistService;
import com.viaplay.api.utils.ModelGeneratorHelperTest;
import com.viaplay.api.utils.TestPropertiesCacheTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ViaplayArtistServiceTest {

    @Autowired
    private MockMvc mockMvc;

    private ModelGeneratorHelperTest modelHelper;

    @Autowired
    private ViaplayArtistService viaplayService;

    @MockBean
    private MusicBrainzAlbumService musicBrainzService;

    @MockBean
    private CoverArtArchiveService coverArtArchiveService;

    @MockBean
    private DiscogsService discogsService;

    private static Gson gson;

    private static TestPropertiesCacheTest props;

    private final static String SAMPLE_UUID = "1946a82a-f927-40c2-8235-38d64f50d043";

    @Before
    public void init() {

        gson = new Gson();

        props = TestPropertiesCacheTest.getInstance();

        modelHelper = new ModelGeneratorHelperTest();

    }

    @Test
    public void checkMainSuccessFlow() throws Exception {

        // setup

        MusicBrainzResponse mockMusicBrainzResponse = modelHelper.generateMusicBrainzResponse();

        List<String> coverArtAlbumIds = modelHelper.getCoverArtIdsFromMusicBrainzresponse(mockMusicBrainzResponse);

        List<CoverArtArchiveResponse> coverArtArchiveResponseList = modelHelper
                .generateListOfCoverArtArchiveResponses(coverArtAlbumIds.size());

        Map<String, CoverArtArchiveResponse> coverMap = modelHelper
                .generateMapOfAlbumIdsAndCoverArtResponse(coverArtAlbumIds, coverArtArchiveResponseList);

        DiscogsResponse mockedDiscogsResponse = modelHelper.generateDiscogsResponse();

        Optional<String> discogsId = modelHelper.getDiscogsIdentifierFromMusicBrainzresponse(mockMusicBrainzResponse);

        // mock

        when(musicBrainzService.getMusicBrainzAlbum(SAMPLE_UUID)).thenReturn(mockMusicBrainzResponse);

        when(discogsService.getDiscogsData(discogsId.get())).thenReturn(Optional.of(mockedDiscogsResponse));

        when(coverArtArchiveService.getAllCoverArtsForAlbumsInList(coverArtAlbumIds)).thenReturn(coverMap);

        // try

        ViaplayArtistResponse response = viaplayService.getAlbumData(SAMPLE_UUID);

        // assert

        Assert.assertEquals(mockMusicBrainzResponse.getId(), response.getMbid());

        for(int i = 0; i < 5; i++) {

            Assert.assertEquals(coverArtArchiveResponseList.get(i).getImages().get(0).getImage(), response.getAlbums().get(i).getImage());

            Assert.assertEquals(coverArtAlbumIds.get(i), response.getAlbums().get(i).getId());

            Assert.assertEquals(mockMusicBrainzResponse.getReleaseGroups().get(i).getTitle(), response.getAlbums().get(i).getTitle());

        }

        Assert.assertEquals(mockedDiscogsResponse.getProfile(), response.getDescription());

    }

    @Test
    public void checkWhenDiscogsReturnsNull() throws Exception {

        // setup

        MusicBrainzResponse mockMusicBrainzResponse = modelHelper.generateMusicBrainzResponse();

        List<String> coverArtAlbumIds = modelHelper.getCoverArtIdsFromMusicBrainzresponse(mockMusicBrainzResponse);

        List<CoverArtArchiveResponse> coverArtArchiveResponseList = modelHelper
                .generateListOfCoverArtArchiveResponses(coverArtAlbumIds.size());

        Map<String, CoverArtArchiveResponse> coverMap = modelHelper
                .generateMapOfAlbumIdsAndCoverArtResponse(coverArtAlbumIds, coverArtArchiveResponseList);

        DiscogsResponse mockedDiscogsResponse = modelHelper.generateDiscogsResponse();

        Optional<String> discogsId = modelHelper.getDiscogsIdentifierFromMusicBrainzresponse(mockMusicBrainzResponse);

        // mock

        when(musicBrainzService.getMusicBrainzAlbum(SAMPLE_UUID)).thenReturn(mockMusicBrainzResponse);

        when(discogsService.getDiscogsData(discogsId.get())).thenReturn(Optional.empty());

        when(coverArtArchiveService.getAllCoverArtsForAlbumsInList(coverArtAlbumIds)).thenReturn(coverMap);

        // try

        ViaplayArtistResponse response = viaplayService.getAlbumData(SAMPLE_UUID);

        // assert

        Assert.assertEquals(mockMusicBrainzResponse.getId(), response.getMbid());

        for(int i = 0; i < 5; i++) {

            Assert.assertEquals(coverArtArchiveResponseList.get(i).getImages().get(0).getImage(), response.getAlbums().get(i).getImage());

            Assert.assertEquals(coverArtAlbumIds.get(i), response.getAlbums().get(i).getId());

            Assert.assertEquals(mockMusicBrainzResponse.getReleaseGroups().get(i).getTitle(), response.getAlbums().get(i).getTitle());

        }

        Assert.assertEquals(mockMusicBrainzResponse.getName(), response.getDescription());

    }

    @Test
    public void checkWhenCoverArtDoesntExist() throws Exception {

        // setup

        MusicBrainzResponse mockMusicBrainzResponse = modelHelper.generateMusicBrainzResponse();

        List<String> coverArtAlbumIds = modelHelper.getCoverArtIdsFromMusicBrainzresponse(mockMusicBrainzResponse);

        DiscogsResponse mockedDiscogsResponse = modelHelper.generateDiscogsResponse();

        Optional<String> discogsId = modelHelper.getDiscogsIdentifierFromMusicBrainzresponse(mockMusicBrainzResponse);

        // mock

        when(musicBrainzService.getMusicBrainzAlbum(SAMPLE_UUID)).thenReturn(mockMusicBrainzResponse);

        when(discogsService.getDiscogsData(discogsId.get())).thenReturn(Optional.of((mockedDiscogsResponse)));

        when(coverArtArchiveService.getAllCoverArtsForAlbumsInList(coverArtAlbumIds)).thenReturn(new HashMap<>());

        // try

        ViaplayArtistResponse response = viaplayService.getAlbumData(SAMPLE_UUID);

        // assert

        Assert.assertEquals(mockMusicBrainzResponse.getId(), response.getMbid());

        for(int i = 0; i < 5; i++) {

            Assert.assertEquals(null, response.getAlbums().get(i).getImage());

            Assert.assertEquals(coverArtAlbumIds.get(i),
                    response.getAlbums().get(i).getId());

            Assert.assertEquals(mockMusicBrainzResponse.getReleaseGroups().get(i).getTitle(),
                    response.getAlbums().get(i).getTitle());

        }

        Assert.assertEquals(mockedDiscogsResponse.getProfile(), response.getDescription());

    }

    @Test(expected = ViaplayValidationException.class)
    public void checkEmptyInputMbid() throws Exception {

        ViaplayArtistResponse response = viaplayService.getAlbumData(StringUtils.EMPTY);

    }

    @Test(expected = ViaplayValidationException.class)
    public void checkInvalidInputMbid() throws Exception {

        ViaplayArtistResponse response = viaplayService.getAlbumData("abc");

    }


}