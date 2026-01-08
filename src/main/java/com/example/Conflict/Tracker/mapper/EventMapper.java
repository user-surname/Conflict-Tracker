package com.example.Conflict.Tracker.mapper;

import com.example.Conflict.Tracker.dto.EventDTO;
import com.example.Conflict.Tracker.model.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDTO(Event event);
    List<EventDTO> toDTO(List<Event> event);
    Event toEntity(EventDTO eventDTO);
}
