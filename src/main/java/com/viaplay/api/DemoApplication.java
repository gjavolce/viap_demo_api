package com.viaplay.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

// Uncoment the annotation below in order to enable caching
// Additionally, update the port:url of Redis in application.properties
// @EnableCaching

@SpringBootApplication
@EnableCircuitBreaker
@EnableAsync
@ComponentScan("com.viaplay")
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {

	    log.info("Main class initialization");

	    SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(50);
		executor.setMaxPoolSize(50);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Viaplay-");
		executor.initialize();
		return executor;
	}
}
