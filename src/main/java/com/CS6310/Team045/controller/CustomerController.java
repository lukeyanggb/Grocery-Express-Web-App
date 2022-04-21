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

import javax.servlet.http.HttpServletRequest;
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
    public void make_customers(@RequestBody Customer customer){
        try{
//        Customer customer = new Customer(account, firstName, lastName, phoneNumber, rating, credits);
        customerService.make_customer(customer);}
        catch (Exception e){
            System.out.println(e.getMessage());
//            System.out.println("Error:store_identifier_already_exists");
        }
    }

    @RequestMapping(value = "/start_order")
    public void startOrder(HttpServletRequest request) throws Exception {
        //        http://localhost:8080/cs6300/team045/start_order?storeName=kroger&orderId=purchaseA&droneId=1&customer=aapple2
        try {
            String store = request.getParameter("storeName");
            String id = request.getParameter("orderId");
            String designatedDrone = request.getParameter("droneId");
            String requestedBy = request.getParameter("customer");


            customerService.start_order(store, id, designatedDrone, requestedBy);
            System.out.println("OK, change_completed");

        }
    catch (Exception e){
        System.out.println(e.getMessage());
//            System.out.println("Error:store_identifier_already_exists");
    }
    }

    @RequestMapping(value ="/orders")
    public List<Order> display_orders(HttpServletRequest request){
        //        http://localhost:8080/cs6300/team045/start_order?storeName=kroger
        String storeName = request.getParameter("storeName");

//        System.out.println(storeName);

        try{
            return customerService.display_orders(storeName);
//            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    @PostMapping(value = "/request_item")
    public void requestItem(HttpServletRequest request) throws Exception {
        try {
            String storeName = request.getParameter("storeName");
            String orderId = request.getParameter("orderId");
            String item = request.getParameter("item");
            //System.out.println(request.getParameter("item"));
            //System.out.println(request.getParameter("unitPirce"));

            Integer quantity = Integer.parseInt(request.getParameter("quantity"));
            Integer unitPrice = Integer.parseInt(request.getParameter("unitPirce"));

            customerService.request_item(storeName,orderId,item,quantity,unitPrice);
            System.out.println("OK, change_completed");

        }
        catch (Exception e){
            System.out.println(e.getMessage());
//            System.out.println("Error:store_identifier_already_exists");
        }

    }

    @PostMapping(value = "/purchase_order")
    public void purchaseOrder(HttpServletRequest request){
        String storeName = request.getParameter("storeName");
        String orderId = request.getParameter("orderId");
        customerService.purchase(storeName,orderId);
    }
    @DeleteMapping(value = "/cancel_order")
    public void cancelOrder(HttpServletRequest request) throws Exception{
        String storeName = request.getParameter("storeName");
        String orderId = request.getParameter("orderId");
        customerService.cancel_order(storeName, orderId);
    }






}
