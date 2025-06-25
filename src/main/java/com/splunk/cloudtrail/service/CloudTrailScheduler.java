package com.splunk.cloudtrail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CloudTrailScheduler {
    private static final Logger logger = LoggerFactory.getLogger(CloudTrailScheduler.class);

    @Autowired private CloudTrailFetcher fetcher;
    @Autowired private CloudTrailParser parser;
    @Autowired private SplunkHECSender sender;

    @Scheduled(fixedDelayString = "${poll.interval.seconds}000")
    public void pollAndSendLogs() {
        logger.info("Scheduled task started: Fetch → Parse → Send CloudTrail logs");
        try {
            List<String> rawLogs = fetcher.fetchLatestLogs();
            for (String raw : rawLogs) {
                List<String> events = parser.parse(raw);
                sender.send(events);
            }
            logger.info("Completed scheduled ingestion cycle");
        } catch (Exception e) {
            logger.error("Error during scheduled CloudTrail ingestion", e);
        }
    }
}