package com.isc.masrour.crudSample.sample;


import com.isc.masrour.crudSample.sample.entity.Customer;
import com.isc.masrour.crudSample.sample.repository.CustomerRepository;
import com.isc.masrour.crudSample.sample.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;


    @Test
    void getCustomer() {
        List<Customer> mockAccounts = new ArrayList<>();
        mockAccounts.add(Customer.builder().id(1L).username("Niloo").lastName("Masrour").password("123").email("nilou@gmail.com").age(30).role("Admin").build());
        mockAccounts.add(Customer.builder().id(2L).username("Ali").lastName("Hasani").password("123").email("Ali@gmail.com").age(50).role("admin").build());
        Mockito.when(customerRepository.findAll()).thenReturn(mockAccounts);
        Customer result = customerService.getCustomerByName("Niloo");
        Assertions.assertEquals(mockAccounts, result);
    }
}


//    @Test
//    void insert() {
//        CustomerDto customer = Customer.builder().id(1L).username("Niloo").lastName("Masrour").password("123").email("nilou@gmail.com").age(30).role("Admin").build();
//        Mockito.when(customerRepository.save(any(CustomerDto.class))).thenReturn(customer);
//        Long insertAccount = customerService.insertCustomer(customer);
//        Assertions.assertNotNull(insertAccount);
//    }
//}
