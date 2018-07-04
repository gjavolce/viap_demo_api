/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viaplay.api.service;

import com.viaplay.api.exceptions.*;
import com.viaplay.api.models.viaplay.ViaplayArtistResponse;

/**
 *
 * @author expert
 */

public interface ViaplayArtistService {
    
    public ViaplayArtistResponse getAlbumData(String mbid) throws
            ViaplayValidationException,
            ViaplayMusicBrainzIdNotFoundException,
            ViaplayMusicBrainzServiceUnavailableException,
            ViaplayMusicBrainzInvalidIdException,
            ViaplayMusicBrainzUnparsableResultException,
            ViaplayServiceException;

}
