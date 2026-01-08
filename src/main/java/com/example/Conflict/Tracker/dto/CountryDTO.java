package com.example.Conflict.Tracker.dto;

import com.example.Conflict.Tracker.model.Conflict;
import com.example.Conflict.Tracker.model.Faction;

import java.time.LocalDateTime;
import java.util.List;

public record CountryDTO (
        Long id,
        String name,
        String code
){
}
