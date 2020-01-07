package com.neusoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Provider_SystemManagement_Application {
    public static void main(String[] args) {
        SpringApplication.run(Provider_SystemManagement_Application.class,args);
    }
}
