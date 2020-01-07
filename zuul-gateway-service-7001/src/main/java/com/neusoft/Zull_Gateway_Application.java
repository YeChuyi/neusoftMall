package com.neusoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy//启动路由配置
@EnableEurekaClient//启动eureka服务发现与注册
public class Zull_Gateway_Application {
    public static void main(String[] args) {
        SpringApplication.run(Zull_Gateway_Application.class,args);
    }
}
