package com.viaplay.api.integrations;

import com.viaplay.api.models.coverart.CoverArtArchiveResponse;

import java.util.List;
import java.util.Map;

public interface CoverArtArchiveService {
    Map<String, CoverArtArchiveResponse> getAllCoverArtsForAlbumsInList(List<String> coverArtIdList);
}
