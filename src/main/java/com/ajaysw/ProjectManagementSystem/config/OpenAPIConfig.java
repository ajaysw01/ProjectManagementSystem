package com.ajaysw.ProjectManagementSystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ajay Wankhade
 */
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Project Management API")
                        .version("1.0")
                        .description("API for managing Project management")
                        .contact(new Contact()
                                .name("Ajay Wankhade")
                                .email("ajaysw45@gmail.com")));
    }
}