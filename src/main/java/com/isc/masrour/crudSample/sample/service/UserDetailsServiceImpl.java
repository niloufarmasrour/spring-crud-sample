package com.isc.masrour.crudSample.sample.service;

import com.isc.masrour.crudSample.sample.entity.Customer;
import com.isc.masrour.crudSample.sample.repository.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CustomerRepository customerRepository;

    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customerOptional = customerRepository.findByUsername(username);
        if (customerOptional.isEmpty()) {
            throw new UsernameNotFoundException("Username %s does not exist".formatted(username));
        }
        Customer customer = customerOptional.get();

        return new User(customer.getUsername(), customer.getPassword(), getAuthorities(customer));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Customer customer) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + customer.getRole()));
    }
}