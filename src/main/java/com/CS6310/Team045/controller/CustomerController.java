package com.CS6310.Team045.controller;

import com.CS6310.Team045.model.Customer;
import com.CS6310.Team045.model.Drone;
import com.CS6310.Team045.model.Order;
import com.CS6310.Team045.model.Store;
import com.CS6310.Team045.services.CustomerService;
import com.CS6310.Team045.services.CustomerServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("user/cs6310/team045")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping(value = "/user_homepage")
    public ModelAndView hello(Model model) {
//        http://localhost:8080/admin/cs6310/team045/admin_homepage
//        String name = "jiangbei";
//        model.addAttribute("name", name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_homepage.html");
        return modelAndView;
    }

    @GetMapping(value = "/start_order_form")
    public ModelAndView start_order_form() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("start_order.html");
        modelAndView.addObject("order", new Order());
        return modelAndView;
    }

    @PostMapping(value = "/start_order")
    public ModelAndView start_order(@ModelAttribute Order order){
        String errMsg;
        String orderId = null;
        try{
//            String name = store.getName();
//            Integer revenue = store.getRevenue();
            String store = order.getStorestoreName();
            String id = order.getId();
            String designatedDrone = "11";
            String requestedBy = "11";

            orderId = customerService.start_order(store, id, designatedDrone, requestedBy);
            errMsg = "OK, change_completed";
        }catch (Exception e){
            errMsg = e.getMessage();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("start_order_msg.html");
        modelAndView.addObject("Message", errMsg);
        modelAndView.addObject("orderId", orderId);
        return modelAndView;
    }


//    @RequestMapping(value = "/start_order")
//    public void startOrder(HttpServletRequest request) throws Exception {
//        //        http://localhost:8080/cs6310/team045/start_order?storeName=kroger&orderId=purchaseA&droneId=1&customer=aapple2
//        try {
//            String store = request.getParameter("storeName");
//            String id = request.getParameter("orderId");
//            String designatedDrone = request.getParameter("droneId");
//            String requestedBy = request.getParameter("customer");
//
//
//            customerService.start_order(store, id, designatedDrone, requestedBy);
//            System.out.println("OK, change_completed");
//
//        }
//    catch (Exception e){
//        System.out.println(e.getMessage());
////            System.out.println("Error:store_identifier_already_exists");
//    }
//    }



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
//http://localhost:8080/cs6310/team045/purchase_order?storeName=target&orderId=purchaseA
    @PostMapping(value = "/purchase_order")
    public void purchaseOrder(HttpServletRequest request)throws Exception{
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
