package com.example.Conflict.Tracker.service;

import com.example.Conflict.Tracker.dto.ConflictDTO;
import com.example.Conflict.Tracker.dto.CountryDTO;
import com.example.Conflict.Tracker.dto.EventDTO;
import com.example.Conflict.Tracker.dto.FactionDTO;
import com.example.Conflict.Tracker.mapper.ConflictMapper;
import com.example.Conflict.Tracker.model.Conflict;
import com.example.Conflict.Tracker.model.Country;
import com.example.Conflict.Tracker.model.Event;
import com.example.Conflict.Tracker.model.Faction;
import com.example.Conflict.Tracker.repository.ConflictRepository;
import com.example.Conflict.Tracker.repository.CountryRepository;
import com.example.Conflict.Tracker.repository.EventRepository;
import com.example.Conflict.Tracker.repository.FactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConflictService {

    @Autowired
    private ConflictMapper conflictMapper;

    @Autowired
    private ConflictRepository conflictRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private FactionRepository factionRepository;

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public ConflictDTO addConflic(ConflictDTO conflictDTO) {

        Conflict conflict = conflictRepository.save(conflictMapper.toEntity(conflictDTO));
        return conflictMapper.toDTO(conflict);
    }

    @Transactional
    public ConflictDTO getConflict(Long id) {

        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conflict with id " + id + " not found"));
        return conflictMapper.toDTO(conflict);

       // Conflict conflict = conflictRepository.findById(id).orElseThrow(() -> new RuntimeException());
       /*
        Optional<Conflict> optionalConflict = conflictRepository.findById(id);
        if(optionalConflict.isPresent()) return conflictMapper.toDTO(optionalConflict.get());
        else return null;
        */

    }

    @Transactional
    public List<ConflictDTO> getAllConflicts() {

        List<Conflict> conflicts = new ArrayList<>();
        conflictRepository.findAll().forEach(conflicts::add);
        return conflictMapper.toDTOList(conflicts);
    }

    @Transactional
    public ConflictDTO updateConflict(Long id,ConflictDTO conflictDTO) {
        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conflict not found with id " + id));

        conflict.setName(conflictDTO.name());
        conflict.setDescription(conflictDTO.description());
        conflict.setStatus(conflictDTO.status());
        if (conflictDTO.startDate() != null) {
            conflict.setStartDate(conflictDTO.startDate());
        }

        List<Country> countries = mapCountries(conflictDTO.countries());
        List<Faction> factions = mapFactions(conflictDTO.factions(), conflict);
        List<Event> events = mapEvents(conflictDTO.events(), conflict);

        conflict.setCountries(countries);
        syncFactions(conflict, factions);
        syncEvents(conflict, events);

        Conflict saved = conflictRepository.save(conflict);

        return conflictMapper.toDTO(saved);
    }

    @Transactional
    public void deleteConflict(Long id) {
        conflictRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conflict not found with id " + id)
        );
        conflictRepository.deleteById(id);
    }

    private List<Country> mapCountries(List<CountryDTO> countryDTOS) {
        if (countryDTOS == null) {
            return new ArrayList<>();
        }

        List<Country> countries = new ArrayList<>();
        for (CountryDTO countryDTO : countryDTOS) {
            Country country;
            if (countryDTO.id() != null) {
                country = countryRepository.findById(countryDTO.id())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found with id " + countryDTO.id()));
            } else {
                country = new Country(countryDTO.name(), countryDTO.code());
                country = countryRepository.save(country);
            }
            country.setName(countryDTO.name());
            country.setCode(countryDTO.code());
            countries.add(country);
        }
        return countries;
    }

    private List<Faction> mapFactions(List<FactionDTO> factionDTOS, Conflict conflict) {
        if (factionDTOS == null) {
            return new ArrayList<>();
        }

        List<Faction> factions = new ArrayList<>();
        for (FactionDTO factionDTO : factionDTOS) {
            Faction faction;
            if (factionDTO.id() != null) {
                faction = factionRepository.findById(factionDTO.id())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Faction not found with id " + factionDTO.id()));
            } else {
                faction = new Faction();
            }
            faction.setName(factionDTO.name());
            faction.setConflict(conflict);
            factions.add(faction);
        }
        return factions;
    }

    private List<Event> mapEvents(List<EventDTO> eventDTOS, Conflict conflict) {
        if (eventDTOS == null) {
            return new ArrayList<>();
        }

        List<Event> events = new ArrayList<>();
        for (EventDTO eventDTO : eventDTOS) {
            Event event;
            if (eventDTO.id() != null) {
                event = eventRepository.findById(eventDTO.id())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id " + eventDTO.id()));
            } else {
                event = new Event();
            }
            event.setEventDate(eventDTO.eventDate());
            event.setLocation(eventDTO.location());
            event.setDescription(eventDTO.description());
            event.setConflict(conflict);
            events.add(event);
        }
        return events;
    }

    private void syncFactions(Conflict conflict, List<Faction> factions) {
        if (conflict.getFactions() == null) {
            conflict.setFactions(new ArrayList<>());
        }
        conflict.getFactions().clear();
        conflict.getFactions().addAll(factions);
    }

    private void syncEvents(Conflict conflict, List<Event> events) {
        if (conflict.getEvents() == null) {
            conflict.setEvents(new ArrayList<>());
        }
        conflict.getEvents().clear();
        conflict.getEvents().addAll(events);
    }

}
