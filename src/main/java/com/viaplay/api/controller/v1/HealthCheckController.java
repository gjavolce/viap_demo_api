
package com.viaplay.api.controller.v1;

import com.viaplay.api.utils.PropertiesCache;
import com.viaplay.api.utils.ServicesHelper;
import com.weddini.throttling.Throttling;
import com.weddini.throttling.ThrottlingType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.viaplay.api.utils.ViaplayApiConstants.HEALTHCHECK_SERVICE_DOWN;
import static com.viaplay.api.utils.ViaplayApiConstants.HEALTHCHECK_SERVICE_UP;
import static com.viaplay.api.utils.ViaplayApiConstants.HEALTHCHECK_TIMEOUT_TIME;

/**
 *
 * Controller class that responsed with simple JSON object with
 * list of three services and their status ("UP" or "DOWN").
 *
 * @author Bojan Dimovski
 * @version 1.0
 *
 */
@RestController
@Slf4j
public class HealthCheckController {

    PropertiesCache props = PropertiesCache.getInstance();

    /**
     * Endpoint for getting the healthcheck status of the 3rd party services used.
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping("/healthcheck")
    @Throttling(limit = 1, timeUnit = TimeUnit.SECONDS, type = ThrottlingType.RemoteAddr)
    public ResponseEntity checkServicesAvailability() {

        Map<String, String> servicesMap = ServicesHelper.getInstance().servicesMap();

        log.info("Healthcheck data requested");

        Map<String, String> response = servicesMap
                .keySet()
                .stream()
                .parallel()
                .collect(Collectors
                        .toMap(Function.identity(),
                                a -> isAvailable(servicesMap.get(a)) ?
                                        props.getProperty(HEALTHCHECK_SERVICE_UP) :
                                        props.getProperty(HEALTHCHECK_SERVICE_DOWN)));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(response);
    }

    /**
     * Creates HTTP connection to the given URL and checks for established connection.
     * The connection timeout is set to 4000 miliseconds. Expected HttpStatus code is 200 (OK).
     *
     * @param urlString the full URI to be pinged
     * @return whether the server was able to establish an HTTP connection to the 3rd party service
     */
    private boolean isAvailable(String urlString) {
        try {

            log.info("Trying to reach out to: {}", urlString);

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Connection", "close");
            connection.setConnectTimeout(Integer.valueOf(props.getProperty(HEALTHCHECK_TIMEOUT_TIME)));
            connection.connect();
            return connection.getResponseCode() == HttpStatus.OK.value();

        } catch (IOException e) {

            log.error("Third Party Service Unavailable: {}, original message: {}", urlString, e.getMessage());

            return false;
        }
    }

}
