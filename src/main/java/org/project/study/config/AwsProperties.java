package org.project.study.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.aws")
@Data
public class AwsProperties {
    private String accessKey;

    private String secretKey;

    private String region;

    private String bucket;

    private String url;
}
