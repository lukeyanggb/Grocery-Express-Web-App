package com.CS6310.Team045.services;

import com.CS6310.Team045.model.*;
import com.CS6310.Team045.repository.*;
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

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private DroneRepository droneRespository;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PilotRepository pilotRepository;

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
    public Optional<Customer> findbyName(String account){
        Optional<Customer> opt = customerRepository.findByAccount(account);
        return opt;
    }

    // create a customer
    public void make_customer(Customer customer){
        Optional<Customer> opt = customerRepository.findByAccount(customer.getAccount());
        if(opt.isPresent()){
            //throw new IllegalArgumentException();
        }
        customerRepository.save(customer);
    }

/*

    //create an order
    public void start_order(Order order){
        String store = order.getStore().getName();
        String orderId = order.getId();
        String droneId = order.getDesignatedDrone().getId();
        if(storeRepository.findStoreByName(store).isPresent()){
            if(orderRepository.findOrderByStoreAndId(store,orderId).isEmpty()){
                if(droneRespository.findDronesByStoreAndAndId(store,droneId).isPresent()){
                    orderRepository.save(order);
                }
                //throw new IllegalArgumentException();

            }
            //throw new IllegalArgumentException();
        }
        //throw  new IllegalArgumentException();

    }

    //show all orders at a given store
    public List<Order> display_orders(String store){
        if(storeRepository.findStoreByName(store).isPresent()){
            return orderRepository.findOrderByStore(store);
        }
        throw new IllegalArgumentException();

    }

    //add an item to the desigen order
    public void request_item(String storeName, String orderId, String item, int quantity, int unitPirce){
        Optional<Order> order= orderRepository.findOrderByStoreAndId(storeName,orderId);
        if(storeRepository.findStoreByName(storeName).isPresent()){
            if(order.isPresent()){
                Optional<Item> itemopt = itemRepository.getItemByStoreAndName(storeName, item);
                if(itemopt.isPresent()){
                    if(itemLineRepository.findItemLineByOrderAndAnd(item,orderId).isEmpty()){
                        //check drone carry new item
                        int lineweight = quantity*itemopt.get().getWeight();
                        int linecost = unitPirce* quantity;
                        Drone drone = order.get().getDesignatedDrone();
                        Customer customer = order.get().getRequestedBy();
                        if(linecost + customer.getOutstandingOrders() <= customer.getCredits()){
                            if(lineweight + drone.getCurrentLoad() <= drone.getCapacity()){

                                ItemLine itemLine = new ItemLine(itemopt.get(), unitPirce,quantity,order.get());
                                customer.addOutstandingOrders(linecost);
                                drone.addCurrentLoad(lineweight);
                                customerRepository.save(customer);
                                droneRespository.save(drone);
                                itemLineRepository.save(itemLine);
                            }
                            //throw new IllegalArgumentException();
                        }
                        //throw new IllegalArgumentException();

                    }
                    //throw new IllegalArgumentException();

                }
                //throw new IllegalArgumentException();
            }
            //throw new IllegalArgumentException();
        }
        //throw new IllegalArgumentException();




    }

    // show all items under each order
    //public Map<String, ArrayList<>> display_orders(String storeName) {
    //}


    //purchase order
    public void purchase(String store, String order){
        Optional<Store> storeopt = storeRepository.findById(store);
        Optional<Order> orderopt = orderRepository.findOrderByStoreAndId(store,order);
        if(storeopt.isPresent()){
            if(orderopt.isPresent()){
                Store s = storeopt.get();
                Customer customer = orderopt.get().getRequestedBy();
                Drone drone = orderopt.get().getDesignatedDrone();
                Pilot pilot = drone.getControlledBy();
                int cost = orderopt.get().orderCost();
                int weight = orderopt.get().orderWeight();
                customer.pay(cost);
                drone.deductCurrentLoad(weight);
                s.addRevenue(cost);
                drone.deductFuel();
                pilot.addExp();
                customerRepository.save(customer);
                droneRespository.save(drone);
                storeRepository.save(s);
                pilotRepository.save(pilot);


            }
            //throw new IllegalArgumentException();
        }
        //throw new IllegalArgumentException();
    }


    //cancel order
    public void cancel_order(String store, String orderId){
        Optional<Order> order = orderRepository.findOrderByStoreAndId(store,orderId);
        if(storeRepository.findById(store).isPresent()){
            if(order.isPresent()){
                orderRepository.delete(order.get());
            }
            //throw new IllegalArgumentException();
        }
        //throw new IllegalArgumentException();

    }


*/

}
