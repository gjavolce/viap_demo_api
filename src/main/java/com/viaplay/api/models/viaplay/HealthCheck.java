package com.viaplay.api.models.viaplay;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthCheck {

    private String musicBrainzService;

    private String coverArtArchiveService;

    private String discogsService;

}
