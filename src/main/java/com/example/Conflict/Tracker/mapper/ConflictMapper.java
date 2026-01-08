package com.example.Conflict.Tracker.mapper;

import com.example.Conflict.Tracker.dto.ConflictDTO;
import com.example.Conflict.Tracker.model.Conflict;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConflictMapper {
    ConflictDTO toDTO(Conflict conflict);
    List<ConflictDTO> toDTOList(List<Conflict> conflicts);
    Conflict toEntity(ConflictDTO conflictDTO);
}
