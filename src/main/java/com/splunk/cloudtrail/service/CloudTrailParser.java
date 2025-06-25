package com.splunk.cloudtrail.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CloudTrailParser {
    private static final Logger logger = LoggerFactory.getLogger(CloudTrailParser.class);

    public List<String> parse(String rawJson) throws Exception {
        logger.debug("Parsing raw CloudTrail JSON");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(rawJson);
        JsonNode records = root.path("Records");

        List<String> parsed = new ArrayList<>();
        for (JsonNode record : records) {
            parsed.add(record.toString());
        }

        logger.info("Parsed {} CloudTrail events", parsed.size());
        return parsed;
    }
}