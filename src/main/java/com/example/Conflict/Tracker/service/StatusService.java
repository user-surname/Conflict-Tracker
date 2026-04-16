package com.example.Conflict.Tracker.service;

import com.example.Conflict.Tracker.model.Status;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class StatusService {

    @Transactional
    public List<Status> getAllStatuses() {
        return Arrays.stream(Status.values()).toList();
    }

    @Transactional
    public Status getStatus(String name) {
        try {
            return Status.valueOf(name.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new RuntimeException("Status " + name + " not found");
        }
    }
}
