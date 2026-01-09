package com.example.Conflict.Tracker;

import com.example.Conflict.Tracker.model.Conflict;
import com.example.Conflict.Tracker.model.Status;
import com.example.Conflict.Tracker.repository.ConflictRepository;
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
    public CommandLineRunner demo(ConflictService conflictService, ConflictRepository conflictRepository) {
        return (args) -> {
            // save a few conflicts
            conflictRepository.save(new Conflict("Conflicto 1", "Descripcion 1", Status.ACTIVE));
            conflictRepository.save(new Conflict("Conflicto 2", "Descripcion 2", Status.ACTIVE));
            conflictRepository.save(new Conflict("Conflicto 3", "Descripcion 3", Status.FROZEN));
            conflictRepository.save(new Conflict("Conflicto 4", "Descripcion 4", Status.ENDED));


        };
    }

}

