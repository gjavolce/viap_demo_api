package com.viaplay.api.integrations;

import com.viaplay.api.models.discogs.DiscogsResponse;

import java.util.Optional;

public interface DiscogsService {

    public Optional<DiscogsResponse> getDiscogsData(String id);

}
