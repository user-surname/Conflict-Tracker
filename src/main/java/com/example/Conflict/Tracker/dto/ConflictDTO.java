package com.example.Conflict.Tracker.dto;

import com.example.Conflict.Tracker.model.Status;

import java.time.LocalDateTime;
import java.util.List;

public record ConflictDTO(
        Long id,
        String name,
        String description,
        Status status,
        LocalDateTime startDate,
        List<CountryDTO> countries,
        List<FactionDTO> factions,
        List<EventDTO> events
) {}
