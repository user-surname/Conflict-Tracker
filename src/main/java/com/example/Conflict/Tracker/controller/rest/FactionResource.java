package com.example.Conflict.Tracker.controller.rest;

import com.example.Conflict.Tracker.dto.FactionDTO;
import com.example.Conflict.Tracker.service.FactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(FactionResource.FACTIONS)
public class FactionResource {

    public static final String FACTIONS = "/factions";

    @Autowired
    private FactionService service;

    @GetMapping
    public List<FactionDTO> getAllFactions() {
        return service.getAllFactions();
    }

    @GetMapping("/{id}")
    public FactionDTO getFaction(@PathVariable("id") Long id) {
        return service.getFaction(id);
    }

    @PostMapping
    public FactionDTO createFaction(@RequestBody FactionDTO factionDTO) {
        return service.addFaction(factionDTO);
    }

    @PutMapping("/{id}")
    public FactionDTO updateFaction(@PathVariable("id") Long id,
                                    @RequestBody FactionDTO factionDTO) {
        return service.updateFaction(id, factionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFaction(@PathVariable("id") Long id) {
        service.deleteFaction(id);
    }
}
