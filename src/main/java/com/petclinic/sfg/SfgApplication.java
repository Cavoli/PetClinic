package com.petclinic.sfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class SfgApplication extends WebMvcAutoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SfgApplication.class, args);
    }

}
