package com.splunk.cloudtrail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SplunkCloudTrailApplication {
    private static final Logger logger = LoggerFactory.getLogger(SplunkCloudTrailApplication.class);

    public static void main(String[] args) {
        logger.info("Starting SplunkCloudTrailApplication");
        SpringApplication.run(SplunkCloudTrailApplication.class, args);
    }
}
