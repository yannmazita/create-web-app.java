package com.example.create_web_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.create_web_app.users.util.UserUtils;

@SpringBootApplication
public class Application {

    @Autowired
    private UserUtils userUtils;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner startupRunner() {
        return (args) -> {
            userUtils.createSuperUser();
            userUtils.createFakeUsers();
        };
    }
}
