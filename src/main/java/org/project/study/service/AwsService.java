package org.project.study.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.project.study.config.AwsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class AwsService {

    @Bean
    public AmazonS3 amazonS3Client(AwsProperties awsProperties) {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey())))
                .withRegion(Regions.fromName(awsProperties.getRegion())).build();
    }

}

