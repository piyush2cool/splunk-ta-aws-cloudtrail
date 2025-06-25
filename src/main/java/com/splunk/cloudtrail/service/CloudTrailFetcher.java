package com.splunk.cloudtrail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class CloudTrailFetcher {
    private static final Logger logger = LoggerFactory.getLogger(CloudTrailFetcher.class);
    private final S3Client s3Client;
    private final String bucketName;

    public CloudTrailFetcher(S3Client s3Client, String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public List<String> fetchLatestLogs() throws IOException {
        logger.info("Fetching CloudTrail logs from bucket: {}", bucketName);
        List<String> logs = new ArrayList<>();
        ListObjectsV2Response res = s3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build());

        for (S3Object obj : res.contents()) {
            logger.debug("Downloading file: {}", obj.key());
            File tempFile = File.createTempFile("cloudtrail", ".json");
            s3Client.getObject(GetObjectRequest.builder()
                            .bucket(bucketName)
                            .key(obj.key())
                            .build(),
                    ResponseTransformer.toFile(tempFile));
            logs.add(Files.readString(tempFile.toPath()));
        }

        logger.info("Fetched {} files from S3", logs.size());
        return logs;
    }
}