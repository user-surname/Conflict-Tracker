package com.example.Conflict.Tracker.repository;

import com.example.Conflict.Tracker.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Event findById(long id);

}
