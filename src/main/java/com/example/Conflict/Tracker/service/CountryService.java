package com.example.Conflict.Tracker.service;

import com.example.Conflict.Tracker.dto.CountryDTO;
import com.example.Conflict.Tracker.mapper.CountryMapper;
import com.example.Conflict.Tracker.model.Country;
import com.example.Conflict.Tracker.repository.CountryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public CountryDTO addCountry(CountryDTO countryDTO) {
        Country country = countryRepository.save(countryMapper.toEntity(countryDTO));
        return countryMapper.toDTO(country);
    }

    @Transactional
    public CountryDTO getCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country with id " + id + " not found"));
        return countryMapper.toDTO(country);
    }

    @Transactional
    public List<CountryDTO> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().forEach(countries::add);
        return countryMapper.toDTO(countries);
    }

    @Transactional
    public CountryDTO updateCountry(Long id, CountryDTO countryDTO) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id " + id));

        country.setName(countryDTO.name());
        country.setCode(countryDTO.code());

        Country saved = countryRepository.save(country);
        return countryMapper.toDTO(saved);
    }

    @Transactional
    public void deleteCountry(Long id) {
        countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id " + id));
        countryRepository.deleteById(id);
    }
}
