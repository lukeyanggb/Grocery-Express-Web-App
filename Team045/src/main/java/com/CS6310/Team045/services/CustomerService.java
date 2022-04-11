package com.CS6310.Team045.services;

import com.CS6310.Team045.exception.BaseException;
import com.CS6310.Team045.model.Customer;
import com.CS6310.Team045.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    //return list of employees
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    //get by id
    public Optional<Customer> findById(long id){
        return customerRepository.findById(id);
    }


}
