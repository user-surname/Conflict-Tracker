package com.example.Conflict.Tracker.mapper;

import com.example.Conflict.Tracker.dto.FactionDTO;
import com.example.Conflict.Tracker.model.Faction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FactionMapper {
    FactionDTO toDTO(Faction faction);
    List<FactionDTO> toDTO(List<Faction> faction);
    Faction toEntity(FactionDTO factionDTO);
}
