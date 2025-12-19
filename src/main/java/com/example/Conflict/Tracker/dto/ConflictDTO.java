package com.example.Conflict.Tracker.dto;

import com.example.Conflict.Tracker.model.ConflictStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ConflictDTO(
        Long id,
        String name,
        LocalDateTime startDate,
        ConflictStatus status,
        String description
) {}

