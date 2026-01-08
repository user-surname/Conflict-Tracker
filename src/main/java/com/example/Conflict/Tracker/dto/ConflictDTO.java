package com.example.Conflict.Tracker.dto;

import com.example.Conflict.Tracker.model.Country;

import java.time.LocalDateTime;
import java.util.List;

public record ConflictDTO(
        Long id,
        String name,
        String status,
        LocalDateTime startDate,
        List<CountryDTO> countries,
        List<FactionDTO> factions,
        List<EventDTO> events
) {}
