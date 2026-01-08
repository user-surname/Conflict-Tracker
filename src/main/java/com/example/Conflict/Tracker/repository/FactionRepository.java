package com.example.Conflict.Tracker.repository;

import com.example.Conflict.Tracker.model.Faction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionRepository extends CrudRepository<Faction, Long> {
    Faction findById(long id);

}
