package com.csu.edu.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("s3")
@Data
public class S3Properties {
    private String bucketName;
    private String accessKey;
    private String secretKey;
    private String endpoint;
}
