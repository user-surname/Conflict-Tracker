package com.example.Conflict.Tracker.service;

import com.example.Conflict.Tracker.dto.EventDTO;
import com.example.Conflict.Tracker.mapper.EventMapper;
import com.example.Conflict.Tracker.model.Event;
import com.example.Conflict.Tracker.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public EventDTO addEvent(EventDTO eventDTO) {
        Event event = eventRepository.save(eventMapper.toEntity(eventDTO));
        return eventMapper.toDTO(event);
    }

    @Transactional
    public EventDTO getEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event with id " + id + " not found"));
        return eventMapper.toDTO(event);
    }

    @Transactional
    public List<EventDTO> getAllEvents() {
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);
        return eventMapper.toDTO(events);
    }

    @Transactional
    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));

        event.setEventDate(eventDTO.eventDate());
        event.setLocation(eventDTO.location());
        event.setDescription(eventDTO.description());

        Event saved = eventRepository.save(event);
        return eventMapper.toDTO(saved);
    }

    @Transactional
    public void deleteEvent(Long id) {
        eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));
        eventRepository.deleteById(id);
    }
}
