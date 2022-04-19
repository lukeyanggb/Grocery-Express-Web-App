package com.CS6310.Team045.controller;

import com.CS6310.Team045.model.Customer;
import com.CS6310.Team045.model.Drone;
import com.CS6310.Team045.model.Order;
import com.CS6310.Team045.model.Store;
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

    @PostMapping(value = "/create_customer")
    public void make_customers(String account, String firstName, String lastName, String phoneNumber, int rating,
                               int credits){
        Customer customer = new Customer(account, firstName, lastName, phoneNumber, rating, credits);
        customerService.make_customer(customer);
    }

    @PostMapping(value = "/start_order")
    public void startOrder(String store,String id, String designatedDrone, String requestedBy){

        customerService.start_order(store,id, designatedDrone, requestedBy);
    }

    @GetMapping(value ="/orders")
    public List<Order> display_orders(String store){

        return customerService.display_orders(store);
    }

    @PostMapping(value = "/request_item")
    public void requestItem(String storeName, String orderId, String item, int quantity, int unitPirce){
        customerService.request_item(storeName,orderId,item,quantity,unitPirce);
    }

    @PostMapping(value = "/purchase_order")
    public void purchaseOrder(String store,String order){
        customerService.purchase(store,order);
    }
    @DeleteMapping(value = "/cancel_order")
    public void cancelOrder(String store, String order){
        customerService.cancel_order(store, order);
    }






}
