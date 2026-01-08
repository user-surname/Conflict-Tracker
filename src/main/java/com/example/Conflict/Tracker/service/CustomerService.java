package com.example.Conflict.Tracker.service;

import com.example.Conflict.Tracker.dto.CustomerDTO;
import com.example.Conflict.Tracker.dto.ConflictDTO;
import com.example.Conflict.Tracker.mapper.CustomerMapper;
import com.example.Conflict.Tracker.model.Customer;
import com.example.Conflict.Tracker.model.Conflict;
import com.example.Conflict.Tracker.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customerMapper.toDTO(customers);
        //return customers.stream().map(this::convertToCustomerDTO).toList();
        //return (List<Customer>)customerRepository.findAll();
    }

    public List<CustomerDTO> getCustomersByLastName(String lastName) {
        return customerMapper.toDTO(customerRepository.findByLastName(lastName));
        //return customerRepository.findByLastName(lastName).stream().map(this::convertToCustomerDTO).toList();
    }

    public CustomerDTO getCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
        return customerMapper.toDTO(customer);
    }

    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer c = customerRepository.save(customerMapper.toEntity(customerDTO));
        return customerMapper.toDTO(c);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));

        // Una possible estratègia. Els camps dels que no fem set quedaran igual que a
        // l'original.
        // També podriem verificar si el dto té totes les dades i si n'hi ha de nulls
        // mantenir la entity
        customer.setEmail(customerDTO.email());
        //customer.setIncidencies(customerDTO.???); no podem de moment modificat incidències
        customer.setFirstName(customerDTO.firstName());
        customer.setLastName(customerDTO.lastName());

        Customer saved = customerRepository.save(customer);

        return customerMapper.toDTO(saved);
    }

    public void deleteCustomer(Long id) {
        Customer c = customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found with id " + id)
        );
        customerRepository.deleteById(id);
    }

}

