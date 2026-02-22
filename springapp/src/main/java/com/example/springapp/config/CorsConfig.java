package com.example.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**") // allow all endpoints
                        .allowedOrigins("http://localhost:3000") 
                        // 👉 change if your React runs on different port

                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")

                        .allowedHeaders("*")

                        .allowCredentials(true)

                        .maxAge(3600);
            }
        };
    }
}