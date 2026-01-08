package com.example.Conflict.Tracker.controller.rest;

import com.example.Conflict.Tracker.dto.ConflictDTO;
import com.example.Conflict.Tracker.service.ConflictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ConflictResource.CONFLICTS)
public class ConflictResource {

    public static final String CONFLICTS = "/Conflicts";

    @Autowired
    private ConflictService service;

    @GetMapping
    public List<ConflictDTO> getAllConflicts() {

        return service.getAllConflicts();
    }

    @GetMapping("/{id}")
    public ConflictDTO getConflict(@PathVariable("id") Long id) {
        return service.getConflict(id);
    }

    @PostMapping
    public ConflictDTO createConflict(@RequestBody ConflictDTO conflictDTO) {
        return service.addConflic(conflictDTO);
    }

    @PutMapping("/{id}")
    public ConflictDTO updateConflict(@RequestBody ConflictDTO conflictDTO) {
        ConflictDTO dto = service.updateConflict(conflictDTO);
        return dto;
    }

    @DeleteMapping("/{id}")
    public void deleteConflict(@PathVariable("id") Long id) {
        service.deleteConflict(id);
    }









}
