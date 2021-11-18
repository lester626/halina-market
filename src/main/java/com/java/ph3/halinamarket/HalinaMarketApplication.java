package com.java.ph3.halinamarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.java.ph3.halinamarket.models")
public class HalinaMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(HalinaMarketApplication.class, args);
    }

}