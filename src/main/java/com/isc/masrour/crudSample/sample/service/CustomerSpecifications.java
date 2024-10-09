package com.isc.masrour.crudSample.sample.service;

import com.isc.masrour.crudSample.sample.dto.CustomerDto;
import com.isc.masrour.crudSample.sample.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class CustomerSpecifications {

    //this class is used for search via dynamic query


    public static Specification<Customer> hasCustomerName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("customer").get("name"), name);
    }


    public static Specification<Customer> searchCustomer(CustomerDto customerDto) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(customerDto.getUsername())) {
                predicates.add(criteriaBuilder.like(root.get("username"), "%" + customerDto.getUsername() + "%"));
            }

            if (customerDto.getLastName() != null && !customerDto.getUsername().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + customerDto.getLastName() + "%"));
            }

            if (StringUtils.hasText(customerDto.getEmail())) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + customerDto.getEmail() + "%"));
            }
            if (customerDto.getAge() != 0) {
                predicates.add(criteriaBuilder.like(root.get("age"), "%" + customerDto.getAge() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
