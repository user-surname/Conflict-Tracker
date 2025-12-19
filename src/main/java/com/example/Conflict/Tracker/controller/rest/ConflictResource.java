package com.example.Conflict.Tracker.controller.rest;


import com.example.Conflict.Tracker.dto.ConflictDTO;
import com.example.Conflict.Tracker.model.ConflictStatus;
import com.example.Conflict.Tracker.service.ConflictService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conflicts")
public class ConflictResource {

    private final ConflictService service;

    public ConflictResource(ConflictService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConflictDTO> getConflicts(
            @RequestParam(required = false) ConflictStatus status
    ) {
        if (status == null) return service.getAll();
        return service.getByStatus(status);
    }

    @GetMapping("/{id}")
    public ConflictDTO getConflict(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ConflictDTO create(@RequestBody ConflictDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ConflictDTO update(@PathVariable Long id, @RequestBody ConflictDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
