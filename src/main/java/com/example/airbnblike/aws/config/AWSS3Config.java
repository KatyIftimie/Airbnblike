package com.example.airbnblike.aws.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3Config {

    @Value("${aws.access_key_id}") private String accessKeyId;
    @Value("${aws.secret_access_key}") private String secretAccessKey;

    @Bean
    public AmazonS3 getAmazonS3Client() {
        final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(String.valueOf(Region.EU_Paris))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
