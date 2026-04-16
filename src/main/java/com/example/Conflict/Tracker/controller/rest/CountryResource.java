package com.example.Conflict.Tracker.controller.rest;

import com.example.Conflict.Tracker.dto.CountryDTO;
import com.example.Conflict.Tracker.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(CountryResource.COUNTRIES)
public class CountryResource {

    public static final String COUNTRIES = "/countries";

    @Autowired
    private CountryService service;

    @GetMapping
    public List<CountryDTO> getAllCountries() {
        return service.getAllCountries();
    }

    @GetMapping("/{id}")
    public CountryDTO getCountry(@PathVariable("id") Long id) {
        return service.getCountry(id);
    }

    @PostMapping
    public CountryDTO createCountry(@RequestBody CountryDTO countryDTO) {
        return service.addCountry(countryDTO);
    }

    @PutMapping("/{id}")
    public CountryDTO updateCountry(@PathVariable("id") Long id,
                                    @RequestBody CountryDTO countryDTO) {
        return service.updateCountry(id, countryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") Long id) {
        service.deleteCountry(id);
    }
}
