package com.example.Conflict.Tracker.repository;

import com.example.Conflict.Tracker.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
    Customer findById(long id);
    List<Customer> findByFirstName(String firstName);

}
