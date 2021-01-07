package com.revature.pokepipeline.utility;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration @EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://ec2-18-216-220-245.us-east-2.compute.amazonaws.com", "localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "UPDATE", "OPTIONS", "DELETE")
                .allowedHeaders("Content-Type", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "accept", "X-Requested-With", "Authorization")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true).maxAge(3600);
    }
}
