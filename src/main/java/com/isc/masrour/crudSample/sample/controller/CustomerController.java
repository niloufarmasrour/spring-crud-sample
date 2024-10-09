package com.isc.masrour.crudSample.sample.controller;


import com.isc.masrour.crudSample.sample.dto.CustomerDto;
import com.isc.masrour.crudSample.sample.entity.Customer;
import com.isc.masrour.crudSample.sample.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @SecurityRequirement(name = "BasicAuth")
    @PostMapping()
    public ResponseEntity<Long> insertCustomer( @RequestBody CustomerDto customer){
        return ResponseEntity.ok(customerService.insertCustomer(customer));
    }

    @SecurityRequirement(name = "BasicAuth")
    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customer){
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @SecurityRequirement(name = "BasicAuth")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer( @PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

//    @SecurityRequirement(name = "BasicAuth")
//    @GetMapping(path = "/slice")
//    public ResponseEntity<Page<CustomerDto>> getAll(Pageable pageable){
//        return ResponseEntity.ok(customerService.getAllCustomers(pageable));
//    }



    @SecurityRequirement(name = "BasicAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCustomer( @PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(id);
    }

}
