package com.example.Conflict.Tracker;

import com.example.Conflict.Tracker.service.ConflictService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConflictTrackerApplication {
    private static final Logger log = LoggerFactory.getLogger(ConflictTrackerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ConflictTrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ConflictService conflictService) {
        return (args) -> {
            // save a few customers

        };
    }

}

