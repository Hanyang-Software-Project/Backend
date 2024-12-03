package com.ziggs.ziggs_backend.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {

    @Value("${amazon.access-key}")
    private String accessKeyId;

    @Value("${amazon.secret-key}")
    private String secretAccessKey;

    @Value("${amazon.region}")
    private String region;

    @Bean(name = "amazonS3Client")
    public AmazonS3 amazonS3() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))  // Use the region from application.yml
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
