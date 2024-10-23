package com.csu.edu.config;

import com.csu.edu.config.properties.S3Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

import static software.amazon.awssdk.regions.Region.EU_CENTRAL_1;

@Configuration
@EnableConfigurationProperties(S3Properties.class)
public class S3Configuration {
    @Qualifier("s3-com.csu.edu.config.properties.S3Properties")
    @Autowired
    private S3Properties properties;

    @Bean
    public S3Client s3Client() {
        AwsCredentials credentials = AwsBasicCredentials.create(properties.getAccessKey(), properties.getSecretKey());
        return S3Client.builder()
                .endpointOverride(URI.create(properties.getEndpoint()))
                .region(EU_CENTRAL_1)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .serviceConfiguration(
                        software.amazon.awssdk.services.s3.S3Configuration.builder()
                                .pathStyleAccessEnabled(true)
                                .build()
                )
                .build();
    }
}
