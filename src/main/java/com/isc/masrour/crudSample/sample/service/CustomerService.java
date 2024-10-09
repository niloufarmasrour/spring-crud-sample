package com.isc.masrour.crudSample.sample.service;


import com.isc.masrour.crudSample.sample.dto.CustomerDto;
import com.isc.masrour.crudSample.sample.entity.Customer;
import com.isc.masrour.crudSample.sample.exception.CustomNotFoundException;
import com.isc.masrour.crudSample.sample.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@Transactional
public class CustomerService {

//    @Value("${customer.not.found}")
    private String customerNotFound;


    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerByName(String customerName) {
        return customerRepository.findByUsername(customerName)
                .orElseThrow(() -> {
                    log.error("Customer with Name {} not found", customerName);
                    throw new CustomNotFoundException(String.format(customerNotFound, customerName));
                });
    }

    public Long insertCustomer(CustomerDto dto) {
        Customer entity = new Customer();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        Customer customer = customerRepository.save(entity);
        log.info("Customer with customerId{} is created.", customer.getId());
        return customer.getId();
    }


//    public Page<CustomerDto> getAllCustomers(Pageable pageable){
//       return customerRepository.findAllPage(pageable);
//    }

    public CustomerDto getCustomerById(Long id) {
        Customer entity = customerRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Customer with ID {} not found", id);
                    throw new CustomNotFoundException(String.format(customerNotFound, id));
                });
        CustomerDto dto = new CustomerDto();
        dto.setUsername(entity.getUsername());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setAge(entity.getAge());
        return dto;
    }

    public Long updateCustomer(Long id, CustomerDto dto) {
        Customer loadedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Account with ID {} not found", dto.getId());
                    throw new CustomNotFoundException(String.format(customerNotFound, dto.getId()));
                });
        loadedCustomer.setUsername(dto.getUsername());
        loadedCustomer.setLastName(dto.getLastName());
        loadedCustomer.setEmail(dto.getEmail());
        loadedCustomer.setPassword(dto.getPassword());
        loadedCustomer.setRole(dto.getRole());
        loadedCustomer.setAge(dto.getAge());
        Customer updated = customerRepository.save(loadedCustomer);
        return updated.getId();
    }

    public void deleteCustomer(Long id) {
        log.info("Customer with Id {} deleted", id);
        customerRepository.deleteById(id);
    }

    public Page<Customer> searchCustomer(CustomerDto customerDto, Pageable pageable) {
        Specification<Customer> spec = CustomerSpecifications.searchCustomer(customerDto);
        return customerRepository.findAll(spec, pageable);
    }
}
