package com.viaplay.api.controller.v1;

import com.google.gson.Gson;
import com.viaplay.api.exceptions.ViaplayMusicBrainzIdNotFoundException;
import com.viaplay.api.exceptions.ViaplayMusicBrainzInvalidIdException;
import com.viaplay.api.exceptions.ViaplayMusicBrainzServiceUnavailableException;
import com.viaplay.api.models.viaplay.ViaplayArtistResponse;
import com.viaplay.api.service.ViaplayArtistService;
import com.viaplay.api.utils.CustomStringManufacturerTest;
import com.viaplay.api.utils.TestPropertiesCacheTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.viaplay.api.utils.ViaplayApiConstants.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ViaplayArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ViaplayArtistService service;

    private static PodamFactory factory;

    private static Gson gson;

    private static TestPropertiesCacheTest props;

    private final static String SAMPLE_UUID = "1946a82a-f927-40c2-8235-38d64f50d043";

    @Before
    public void init() {

        factory = new PodamFactoryImpl();
        factory.getStrategy().addOrReplaceTypeManufacturer(String.class, new CustomStringManufacturerTest());

        gson = new Gson();

        props = TestPropertiesCacheTest.getInstance();

    }


    @Test
        public void shouldReturnDefaultResponse() throws Exception {

            ViaplayArtistResponse mockResponse = factory.manufacturePojo(ViaplayArtistResponse.class);

            when(service.getAlbumData(mockResponse.getMbid())).thenReturn(mockResponse);

            this.mockMvc.perform(get("/v1/artists/" + mockResponse.getMbid()))
                    .andDo(print()).andExpect(status().isOk())
                            .andExpect(content().string(gson.toJson(mockResponse, ViaplayArtistResponse.class)));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {

        when(service.getAlbumData(SAMPLE_UUID))
                .thenThrow(new ViaplayMusicBrainzIdNotFoundException(props.getProperty(MUSICBRAINZ_API_URL_ERROR_VALUE_NOT_FOUND)));

        this.mockMvc.perform(get("/v1/artists/" + SAMPLE_UUID))
                .andDo(print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().string(containsString(props.getProperty(MUSICBRAINZ_API_URL_ERROR_VALUE_NOT_FOUND))));
    }

    @Test
    public void shouldReturnInvalid() throws Exception {

        when(service.getAlbumData(SAMPLE_UUID))
                .thenThrow(new ViaplayMusicBrainzInvalidIdException(props.getProperty(MUSICBRAINZ_API_URL_ERROR_VALUE_INVALID)));

        this.mockMvc.perform(get("/v1/artists/" + SAMPLE_UUID))
                .andDo(print()).andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string(containsString(props.getProperty(MUSICBRAINZ_API_URL_ERROR_VALUE_INVALID))));
    }

    @Test
    public void shouldReturnServiceUnavailable() throws Exception {

        when(service.getAlbumData(SAMPLE_UUID))
                .thenThrow(new ViaplayMusicBrainzServiceUnavailableException(props.getProperty(MUSICBRAINZ_ERROR_UNPARSABLE_RESPONSE)));

        this.mockMvc.perform(get("/v1/artists/" + SAMPLE_UUID))
                .andDo(print()).andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
                .andExpect(content().string(containsString(props.getProperty(MUSICBRAINZ_ERROR_UNPARSABLE_RESPONSE))));
    }

}