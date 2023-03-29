package com.gdsc.canigraduate.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(
            @Value("${springdoc.title}") String title,
            @Value("${springdoc.version}") String version,
            @Value("${springdoc.description}") String description) {
        Info info = new Info()
                .title(title)
                .version(version)
                .description(description);

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
