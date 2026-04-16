package com.example.Conflict.Tracker.service;

import com.example.Conflict.Tracker.dto.FactionDTO;
import com.example.Conflict.Tracker.mapper.FactionMapper;
import com.example.Conflict.Tracker.model.Faction;
import com.example.Conflict.Tracker.repository.FactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactionService {

    @Autowired
    private FactionMapper factionMapper;

    @Autowired
    private FactionRepository factionRepository;

    @Transactional
    public FactionDTO addFaction(FactionDTO factionDTO) {
        Faction faction = factionRepository.save(factionMapper.toEntity(factionDTO));
        return factionMapper.toDTO(faction);
    }

    @Transactional
    public FactionDTO getFaction(Long id) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction with id " + id + " not found"));
        return factionMapper.toDTO(faction);
    }

    @Transactional
    public List<FactionDTO> getAllFactions() {
        List<Faction> factions = new ArrayList<>();
        factionRepository.findAll().forEach(factions::add);
        return factionMapper.toDTO(factions);
    }

    @Transactional
    public FactionDTO updateFaction(Long id, FactionDTO factionDTO) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found with id " + id));

        faction.setName(factionDTO.name());

        Faction saved = factionRepository.save(faction);
        return factionMapper.toDTO(saved);
    }

    @Transactional
    public void deleteFaction(Long id) {
        factionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found with id " + id));
        factionRepository.deleteById(id);
    }
}
