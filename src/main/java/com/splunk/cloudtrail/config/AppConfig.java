package com.splunk.cloudtrail.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;

@Configuration
@Getter
public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Value("${aws.region}")
    private String region;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${splunk.hec.url}")
    private String splunkHecUrl;

    @Value("${splunk.hec.token}")
    private String splunkHecToken;

    @Value("${poll.interval.seconds}")
    private int pollInterval;

    public AppConfig() {
        logger.info("Loaded configuration properties");
    }
}
