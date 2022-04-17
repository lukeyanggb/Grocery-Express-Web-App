package com.CS6310.Team045.controller;

import com.CS6310.Team045.model.Customer;
import com.CS6310.Team045.services.CustomerService;
import com.CS6310.Team045.services.CustomerServiceImpl;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cs6300/team045")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping(value ="/customers")
    public List<Customer> display_customers(){
        return customerService.getCustomers();
    }

    @PostMapping(value = "/createcustomer")
    public void make_customers(@RequestBody Customer customer){
        customerService.make_customer(customer);
    }

  



}
