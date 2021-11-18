package com.java.ph3.halinamarket.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@Data
public class DBConfigurations {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Profile("inventory")
    @Bean
    public String devDbConnection() {
        String message = "DB Connection for Inventory";
        printDetails(message);
        return message;
    }

    public void printDetails(String message) {
        System.out.println(message);
        System.out.println(driverClassName);
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
    }
}
