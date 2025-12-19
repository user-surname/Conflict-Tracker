package com.example.Conflict.Tracker.repository;

import com.example.Conflict.Tracker.model.Conflict;
import com.example.Conflict.Tracker.model.ConflictStatus;
import com.example.Conflict.Tracker.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConflictRepository extends CrudRepository<Conflict, Long> {



}

