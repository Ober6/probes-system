package org.ober6.probes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigurationService {
    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
