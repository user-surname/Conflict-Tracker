package com.example.Conflict.Tracker.mapper;

import com.example.Conflict.Tracker.dto.CountryDTO;
import com.example.Conflict.Tracker.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDTO toDTO(Country country);
    List<CountryDTO> toDTO(List<Country> country);
    Country toEntity(CountryDTO countryDTO);
}
