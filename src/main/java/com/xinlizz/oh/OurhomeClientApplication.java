package com.xinlizz.oh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Component
@EnableTransactionManagement
public class OurhomeClientApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(OurhomeClientApplication.class);
        return super.configure(builder);
    }

    public static void main(String[] args) {
        SpringApplication.run(OurhomeClientApplication.class, args);
    }
}
