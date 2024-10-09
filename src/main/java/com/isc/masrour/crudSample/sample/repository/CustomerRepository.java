package com.isc.masrour.crudSample.sample.repository;

import com.isc.masrour.crudSample.sample.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer , Long> , JpaSpecificationExecutor<Customer> {

    Optional<Customer> findById(Long id);
    Optional<Customer> findByUsername(String username);

    Page<Customer> findAll(Specification<Customer> spec, Pageable pageable);
//    Page<CustomerDto> findAllPage(Pageable pageable);

}
