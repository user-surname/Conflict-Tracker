package com.example.Conflict.Tracker.mapper;

import com.example.Conflict.Tracker.dto.CustomerDTO;
import com.example.Conflict.Tracker.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);
    List<CustomerDTO> toDTO(List<Customer> customers);
    Customer toEntity(CustomerDTO customerDTO);
}
