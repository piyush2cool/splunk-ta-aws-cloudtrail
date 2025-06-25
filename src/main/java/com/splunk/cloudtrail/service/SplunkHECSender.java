package com.splunk.cloudtrail.service;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SplunkHECSender {
    private static final Logger logger = LoggerFactory.getLogger(SplunkHECSender.class);
    private final String hecUrl;
    private final String token;

    public SplunkHECSender(String hecUrl, String token) {
        this.hecUrl = hecUrl;
        this.token = token;
    }

    public void send(List<String> events) throws Exception {
        logger.info("Sending {} events to Splunk HEC at {}", events.size(), hecUrl);
        try (var client = HttpClients.createDefault()) {
            for (String event : events) {
                HttpPost post = new HttpPost(hecUrl);
                post.setHeader("Authorization", "Splunk " + token);
                post.setHeader("Content-Type", "application/json");
                post.setEntity(new StringEntity("{\"event\":" + event + "}"));
                client.execute(post).close();
            }
        }
        logger.info("Successfully sent all events to Splunk");
    }
}
