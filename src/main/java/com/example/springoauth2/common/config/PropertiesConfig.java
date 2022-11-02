package com.example.springoauth2.common.config;

import com.example.springoauth2.common.props.AppProperties;
import com.example.springoauth2.common.props.CorsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {AppProperties.class, CorsProperties.class})
public class PropertiesConfig {
}
