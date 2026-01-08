package com.example.Conflict.Tracker.dto;

import java.time.LocalDateTime;

public record EventDTO (
        Long id,
        LocalDateTime eventDate,
        String location,
        String description
) {
}
