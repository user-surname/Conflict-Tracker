package com.example.Conflict.Tracker.service;

import com.example.Conflict.Tracker.dto.ConflictDTO;
import com.example.Conflict.Tracker.mapper.ConflictMapper;
import com.example.Conflict.Tracker.model.Conflict;
import com.example.Conflict.Tracker.repository.ConflictRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConflictService {

    @Autowired
    private ConflictMapper conflictMapper;

    @Autowired
    private ConflictRepository conflictRepository;

    @Transactional
    public ConflictDTO addConflic(ConflictDTO conflictDTO) {

        Conflict newConflict = new Conflict();
        newConflict = conflictMapper.toEntity(conflictDTO);

        conflictDTO = conflictMapper.toDTO(conflictRepository.save(newConflict));

        return conflictDTO;
    }

    @Transactional
    public ConflictDTO getConflict(Long id) {

       // Conflict conflict = conflictRepository.findById(id).orElseThrow(() -> new RuntimeException());
        Optional<Conflict> optionalConflict = conflictRepository.findById(id);
        if(optionalConflict.isPresent()) return conflictMapper.toDTO(optionalConflict.get());
        else return null;


    }

    @Transactional
    public List<ConflictDTO> getAllConflicts() {

        List<Conflict> conflicts = new ArrayList<>();
        conflictRepository.findAll().forEach(conflicts::add);
        return conflictMapper.toDTO(conflicts);
    }

    @Transactional
    public ConflictDTO updateConflict(ConflictDTO conflictDTO) {

        Conflict conflict = conflictMapper.toEntity(conflictDTO);
        conflict = conflictRepository.save(conflict);

        return conflictMapper.toDTO(conflict);
    }



}
