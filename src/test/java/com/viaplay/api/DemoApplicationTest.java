package com.viaplay.api;


import com.viaplay.api.controller.v1.HealthCheckController;
import com.viaplay.api.controller.v1.ViaplayArtistController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

    @Test
    public void contextLoads() throws Exception {
    }

    @Autowired
    private ViaplayArtistController musicController;

    @Autowired
    private HealthCheckController healthCheckController;

    @Test
    public void contexLoadsWithController() throws Exception {
        assertThat(musicController).isNotNull();
        assertThat(healthCheckController).isNotNull();

    }

}