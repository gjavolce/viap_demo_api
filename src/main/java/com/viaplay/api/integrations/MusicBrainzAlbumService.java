package com.viaplay.api.integrations;

import com.viaplay.api.exceptions.ViaplayMusicBrainzIdNotFoundException;
import com.viaplay.api.exceptions.ViaplayMusicBrainzInvalidIdException;
import com.viaplay.api.exceptions.ViaplayMusicBrainzServiceUnavailableException;
import com.viaplay.api.exceptions.ViaplayMusicBrainzUnparsableResultException;
import com.viaplay.api.models.musicbrainz.MusicBrainzResponse;

public interface MusicBrainzAlbumService {


    public MusicBrainzResponse getMusicBrainzAlbum(String mbid) throws
            ViaplayMusicBrainzIdNotFoundException,
            ViaplayMusicBrainzServiceUnavailableException,
            ViaplayMusicBrainzInvalidIdException,
            ViaplayMusicBrainzUnparsableResultException;
}
