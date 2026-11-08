package com.minidam.adam.service.s3;

import ch.qos.logback.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URI;
import java.time.Duration;

@Slf4j
@Service
public class S3Service {

    public String generatePresignedUrl(String bucketName, String keyName) {
        String presignedUrl = "";
        bucketName = StringUtil.isNullOrEmpty(bucketName) ? "test" : bucketName;
        S3Presigner s3Presigner = S3Presigner.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(URI.create("http://localhost:4566"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")
                ))
                .build();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .build();

        PutObjectPresignRequest putObjectPresignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10))
                    .putObjectRequest(putObjectRequest)
                    .build();

        PresignedPutObjectRequest presignedPutObjectRequest = s3Presigner.presignPutObject(putObjectPresignRequest);
         presignedUrl = presignedPutObjectRequest.url().toString();

        log.info("Presigned URL : {}", presignedUrl);

        return presignedUrl;
    }
}
