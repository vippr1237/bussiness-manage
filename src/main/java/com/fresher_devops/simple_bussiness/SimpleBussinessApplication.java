package com.fresher_devops.simple_bussiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SimpleBussinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBussinessApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Simple Bussiness API";
    }
}
