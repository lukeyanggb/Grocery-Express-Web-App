package com.CS6310.Team045.services;

import com.CS6310.Team045.exception.BaseException;
import com.CS6310.Team045.model.*;
import com.CS6310.Team045.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private DroneRepository droneRepository;


    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PilotRepository pilotRepository;

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    //display all customer
    @Cacheable(value = "Customer")
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    //get a customer by id
    @Cacheable(value = "Customer", key = "#id")
    public Optional<Customer> findById(long id){
        return customerRepository.findById(id);
    }

    @Cacheable(value = "Customer", key = "#account")
    public Optional<Customer> findbyName(String account){
        Optional<Customer> opt = customerRepository.findByAccount(account);
        return opt;
    }

    // create a customer
    public void make_customer(Customer customer){
        Optional<Customer> opt = customerRepository.findByAccount(customer.getAccount());
        if(opt.isPresent()){
            throw new IllegalArgumentException();
        } else {
            customerRepository.save(customer);
        }
    }

    @Cacheable(value="Store", key="#store")
    public Optional<Store> findStore(String store){
        return storeRepository.findStoreByName(store);
    }

    //create an order
    public void start_order(String storeName, String orderId, String droneId, String customerAccount) throws Exception {

//        System.out.println(storeName);
//        System.out.println(orderId);
//        System.out.println(droneId);
//        System.out.println(customerAccount);


        Optional<Store> store = findStore(storeName);
        if (store.isEmpty()) {
            throw new Exception("ERROR:store_identifier_does_not_exist");
        }

        Optional<Order> orderOpt = orderRepository.findOrderByStore_nameAndId(storeName,orderId);
        if (orderOpt.isPresent()) {
            throw new Exception("ERROR:order_identifier_already_exists");
        }

        Optional<Drone> optDrone = droneRepository.findByStore_nameAndId(storeName, droneId);

        if (optDrone.isEmpty()) {
            throw new Exception("ERROR:drone_identifier_does_not_exist");
        }

        Optional<Customer> customerOptional = customerRepository.findByAccount(customerAccount);
//        System.out.println(customerOptional);

        if(customerOptional.isEmpty()){
            throw new Exception("ERROR:customer_identifier_does_not_exist");
        }

        Order order = new Order(orderId,optDrone.get(), customerOptional.get(),store.get());
        orderRepository.save(order);

    }


    //show all orders at a given store
    @Cacheable("Order")
    public List<Order> display_orders(String store) throws Exception {
        if(storeRepository.findStoreByName(store).isPresent()){
            return orderRepository.findOrderByStore_name(store);
        }
        throw new Exception("ERROR:store_identifier_does_not_exist");

    }

    //add an item to the desigen order
    public void request_item(String storeName, String orderId, String itemName, Integer quantity, Integer unitPirce) throws Exception {

//        Optional<Store> store = findStore(storeName);
//        if (store.isEmpty()) {
//            throw new Exception("ERROR:store_identifier_does_not_exist");
//        }
//
//        Optional<Order> orderOpt = orderRepository.findOrderByStore_nameAndId(storeName,orderId);
//        if (orderOpt.isEmpty()) {
//            throw new Exception("ERROR:order_identifier_does_not_exist");
//        }
//
//
//        Optional<Item> optItem = itemRepository.findByStore_nameAndName(storeName, itemName);
//
//        if (optItem.isEmpty()) {
//            throw new Exception("ERROR:item_identifier_does_not_exist");
//        }
//
////        Optional<Drone> optDrone = droneRepository.findByStore_nameAndId(storeName, droneId);
////
////        if (optDrone.isEmpty()) {
////            throw new Exception("ERROR:drone_identifier_does_not_exist");
////        }
//
//        int lineweight = quantity*optItem.get().getWeight();
//        int linecost = unitPirce* quantity;
//        Drone drone = orderOpt.get().getDesignatedDrone();
//        Customer customer = orderOpt.get().getRequestedBy();
//        if(linecost + customer.getOutstandingOrders() <= customer.getCredits()){
//            if(lineweight + drone.getCurrentLoad() <= drone.getCapacity()){
//
//                ItemLine itemLine = new ItemLine(itemName, unitPirce,quantity,orderOpt.get());
//                customer.addOutstandingOrders(linecost);
//                drone.addCurrentLoad(lineweight);
//                customerRepository.save(customer);
//                droneRepository.save(drone);
//                itemLineRepository.save(itemLine);
//            }
//            throw new Exception("ERROR:item_already_ordered");

    }

    // show all items under each order
    //public Map<String, ArrayList<>> display_orders(String storeName) {
    //}


    //purchase order
    public void purchase(String store, String order){
//        Optional<Store> storeopt = storeRepository.findById(store);
//        Optional<Order> orderopt = orderRepository.findOrderByStore_nameAndId(store,order);
//        if(storeopt.isPresent()){
//            if(orderopt.isPresent()){
//                Store s = storeopt.get();
//                Customer customer = orderopt.get().getRequestedBy();
//                Drone drone = orderopt.get().getDesignatedDrone();
//                Pilot pilot = drone.getControlledBy();
//                int cost = orderopt.get().orderCost();
//                int weight = OrderWeight(orderopt.get());
//                customer.pay(cost);
//                drone.deductCurrentLoad(weight);
//                s.addRevenue(cost);
//                drone.deductFuel();
//                pilot.addExp();
//                customerRepository.save(customer);
//                droneRepository.save(drone);
//                storeRepository.save(s);
//                pilotRepository.save(pilot);
//
//
//            }
//            throw new IllegalArgumentException();
//        }
//        throw new IllegalArgumentException();
    }


    //cancel order
    public void cancel_order(String store, String orderId){
//        Optional<Order> order = orderRepository.findOrderByStore_nameAndId(store,orderId);
//        if(storeRepository.findById(store).isPresent()){
//            if(order.isPresent()){
//                orderRepository.delete(order.get());
//            }
//            throw new IllegalArgumentException();
//        }
//        throw new IllegalArgumentException();

    }

    public int OrderWeight(Order order){
        int weight =0;
        for(ItemLine l:order.getItems()){
            Optional<Item> i = itemRepository.findByStore_nameAndName(l.getItem(),order.getStore().getName());
            if(i.isPresent()){
                weight += l.getQuantity()*i.get().getWeight();
            }

        }
        return weight;
    }




}
