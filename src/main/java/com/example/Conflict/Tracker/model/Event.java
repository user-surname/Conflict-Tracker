package com.example.Conflict.Tracker.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private LocalDateTime eventDate = LocalDateTime.now();
    private String location;
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conflict_id")
    private Conflict conflict;

    public Event() {
    }

    public Event(LocalDateTime eventDate, String location, String description) {
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Conflict getConflict() {
        return conflict;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

