package com.CS6310.Team045.services;

import com.CS6310.Team045.model.Customer;
import com.CS6310.Team045.model.ItemLine;
import com.CS6310.Team045.model.Order;
import com.CS6310.Team045.repository.CustomerRepository;
import com.CS6310.Team045.repository.ItemLineRepository;
import com.CS6310.Team045.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class CustomerServiceImpl {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemLineRepository itemLineRepository;

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    //display all customer
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    //get a customer by id
    public Optional<Customer> findById(long id){
        return customerRepository.findById(id);
    }

    // create a customer
    public void make_customer(Customer customer){
        customerRepository.save(customer);
    }



    //create an order
    public void start_order(Order order){
        orderRepository.save(order);

    }

    //show all orders at a given store
    public List<Order> display_orders(String store){
        return orderRepository.getOrderByStore(store);
    }

    //add an item to the desigen order
    public void request_item(String storeName, String orderId, String item, int quantity, int unitPirce){
        Optional<Order> order= orderRepository.getOrderByStoreAndId(storeName,orderId);
        if(order.isPresent()){
            ItemLine itemLine = new ItemLine(item, unitPirce,quantity,order.get());
            itemLineRepository.save(itemLine);
        }



    }

    // soho all items under each order
    //public Map<String, ArrayList<>> display_orders(String storeName) {
    //}


    //purchase order
    //cancel order
    public void cancel_order(String store, String orderId){
        Optional<Order> order = orderRepository.getOrderByStoreAndId(store,orderId);
        if(order.isPresent()){
            orderRepository.delete(order.get());
        }
    }


}
