package com.example.Conflict.Tracker.controller.rest;

import com.example.Conflict.Tracker.model.Status;
import com.example.Conflict.Tracker.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(StatusResource.STATUSES)
public class StatusResource {

    public static final String STATUSES = "/statuses";

    @Autowired
    private StatusService service;

    @GetMapping
    public List<Status> getAllStatuses() {
        return service.getAllStatuses();
    }

    @GetMapping("/{name}")
    public Status getStatus(@PathVariable("name") String name) {
        return service.getStatus(name);
    }
}
