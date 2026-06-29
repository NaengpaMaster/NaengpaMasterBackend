package com.naengpa.naengpamasterbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NaengpaMasterBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaengpaMasterBackendApplication.class, args);
    }

}
