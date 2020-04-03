package com.example.demo.comm;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource(value= "classpath:other.properties")
@ConfigurationProperties(prefix = "other")
public class OtherProperties {
    private String title;
    private String blog;
}
