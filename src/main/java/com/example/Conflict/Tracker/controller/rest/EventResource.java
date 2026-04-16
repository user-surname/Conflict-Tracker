package com.example.Conflict.Tracker.controller.rest;

import com.example.Conflict.Tracker.dto.EventDTO;
import com.example.Conflict.Tracker.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(EventResource.EVENTS)
public class EventResource {

    public static final String EVENTS = "/events";

    @Autowired
    private EventService service;

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return service.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDTO getEvent(@PathVariable("id") Long id) {
        return service.getEvent(id);
    }

    @PostMapping
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
        return service.addEvent(eventDTO);
    }

    @PutMapping("/{id}")
    public EventDTO updateEvent(@PathVariable("id") Long id,
                                @RequestBody EventDTO eventDTO) {
        return service.updateEvent(id, eventDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable("id") Long id) {
        service.deleteEvent(id);
    }
}
