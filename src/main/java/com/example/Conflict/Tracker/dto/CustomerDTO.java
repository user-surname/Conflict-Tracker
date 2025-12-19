package com.example.Conflict.Tracker.dto;

import java.util.List;

public record CustomerDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        List<ConflictDTO> incidencies
) {
}
