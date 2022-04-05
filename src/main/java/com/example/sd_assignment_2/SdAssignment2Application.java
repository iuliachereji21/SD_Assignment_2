package com.example.sd_assignment_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SdAssignment2Application {

    public static void main(String[] args) {
        SpringApplication.run(SdAssignment2Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE")
                        .allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }
}
