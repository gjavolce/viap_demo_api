/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viaplay.api.controller.v1;

import com.viaplay.api.exceptions.*;
import com.viaplay.api.service.ViaplayArtistService;
import com.viaplay.api.utils.PropertiesCache;
import com.weddini.throttling.Throttling;
import com.weddini.throttling.ThrottlingType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.viaplay.api.utils.ViaplayApiConstants.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author expert
 */
@Slf4j
@RestController
public class ViaplayArtistController {

    private static PropertiesCache props = PropertiesCache.getInstance();

    @Autowired
    @Qualifier("viaplayArtistService")
    ViaplayArtistService albumService;

    @GetMapping("/v1/artists/{mbid}")
    @Throttling(limit = 1, timeUnit = TimeUnit.SECONDS, type = ThrottlingType.RemoteAddr)
    public ResponseEntity getArtistDetails(Model model, @PathVariable("mbid") String mbid) {

        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", "application/json");

        try {

            log.info("Artist data requested for MBID: {}. ", mbid);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(this.albumService.getAlbumData(mbid));

        } catch (ViaplayValidationException | ViaplayMusicBrainzInvalidIdException e) {

            log.warn("Validation exception for MBID: {}, original message: {}", mbid, e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .headers(headers)
                    .body(Map.of(props.getProperty(VIAPLAY_SERVICE_ERROR_LABEL), e.getMessage()));

        } catch (ViaplayMusicBrainzIdNotFoundException e) {

            log.warn("MBID Not Found: {}, original message: {}", mbid, e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .headers(headers)
                    .body(Map.of(props.getProperty(VIAPLAY_SERVICE_ERROR_LABEL), e.getMessage()));
        } catch (ViaplayServiceException |
                ViaplayMusicBrainzUnparsableResultException |
                ViaplayMusicBrainzServiceUnavailableException e) {

            log.error("Viaplay Service Unavailable: {}, original message: {}", mbid, e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .headers(headers)
                    .body(Map.of(props.getProperty(VIAPLAY_SERVICE_ERROR_LABEL), e.getMessage()));
        }


    }

}
