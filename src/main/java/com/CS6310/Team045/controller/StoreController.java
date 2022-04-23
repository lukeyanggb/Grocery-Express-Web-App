package com.CS6310.Team045.controller;

import com.CS6310.Team045.model.Drone;
import com.CS6310.Team045.model.Item;
import com.CS6310.Team045.model.Pilot;
import com.CS6310.Team045.model.Store;
import com.CS6310.Team045.services.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("admin/cs6310/team045")
public class StoreController {
    @Autowired
    private StoreServiceImpl storeService;



    @GetMapping(value = "/admin_homepage")
    public ModelAndView hello(Model model) {
//        http://localhost:8080/admin/cs6310/team045/admin_homepage
//        String name = "jiangbei";
//        model.addAttribute("name", name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_homepage.html");
        return modelAndView;
    }
    @GetMapping(value = "/make_store_pre")
    public ModelAndView ms() {
//        http://localhost:8080/admin/cs6310/team045/admin_homepage
//        String name = "jiangbei";
//        model.addAttribute("name", name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("make_store.html");
        modelAndView.addObject("store", new Store());

        return modelAndView;
    }

    //make store
    @PostMapping(value = "/make_store")
    public String make_store(@ModelAttribute Store store){
//        http://localhost:8080/cs6310/team045/make_store?name=krogrr&revenue=33000
        String errMsg;
        try{
//            System.out.println(request);
//            String name = request.getParameter("name");
            String name = store.getName();
            System.out.println(name);
            Integer revenue = store.getRevenue();
//            Integer revenue = Integer.parseInt(request.getParameter("revenue"));
            System.out.println(revenue);
//            Integer revenue = Integer.parseInt(revenue_str);
//            System.out.println(name);
//            System.out.println(revenue);

//            Store store = new Store(name,revenue);
//                        System.out.println(store);

            storeService.makeStore(name,revenue);
//            System.out.println("OK, change_completed");
            errMsg = "OK, change_completed";
        }catch (Exception e){
//            System.out.println(e.getMessage());
//            System.out.println("Error:store_identifier_already_exists");
            errMsg = e.getMessage();
        }
        return errMsg;

    }


//    @GetMapping(value = "/hello")
//    public ModelAndView hello(Model model) {
//        String name = "jiangbei";
//        model.addAttribute("name", name);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("hello.html");
//        return modelAndView;
//    }
//

    //get all stores
    @GetMapping(value = "/stores")
    public List<Store> findAllStores(){
        return storeService.getAllStores();
    }

//    //make store
//    @PostMapping(value = "/make_store")
//    public void make_store(HttpServletRequest request){
////        http://localhost:8080/cs6310/team045/make_store?name=krogrr&revenue=33000
//        try{
////            System.out.println(request);
//            String name = request.getParameter("name");
//            System.out.println(name);
//            Integer revenue = Integer.parseInt(request.getParameter("revenue"));
//            System.out.println(revenue);
////            Integer revenue = Integer.parseInt(revenue_str);
//            System.out.println(name);
//            System.out.println(revenue);
//
////            Store store = new Store(name,revenue);
////                        System.out.println(store);
//
//            storeService.makeStore(name,revenue);
//            System.out.println("OK, change_completed");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
////            System.out.println("Error:store_identifier_already_exists");
//        }
//    }

    //add item
    @PostMapping(value = "/sell_item")
    public void sell_item(HttpServletRequest request){
//        System.out.println(item.getName());
//        System.out.println(item.getWeight());
//        System.out.println(item.getSname());

        try{
            String name = request.getParameter("name");
            String weight = request.getParameter("weight");
            String store = request.getParameter("store");
            storeService.addItem(name,weight,store);
            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(value = "/items")
    public List<Item> findAllItems(HttpServletRequest request){
//        http://localhost:8080/cs6310/team045/display_items?name=kroger
        String storeName = request.getParameter("name");

//        System.out.println(storeName);

        try{
            return storeService.display_items(storeName);
//            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }



    //make pilot
    @PostMapping(value = "/make_pilot")
    public void make_pilot(@RequestBody Pilot pilot){
        try{

            storeService.makePilot(pilot);
            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
//            System.out.println("Error:store_identifier_already_exists");
        }
    }

    //display pilots
    @GetMapping(value = "/pilots")
    public List<Pilot> findAllPilots(){
        return storeService.display_pilots();
    }

    //make drone
    @PostMapping(value = "/make_drone")
    public void make_drone(HttpServletRequest request){
        String storeName = request.getParameter("name");
        String droneId = request.getParameter("droneId");
        String capacity = request.getParameter("capacity");
        String fuel = request.getParameter("fuel");
//        System.out.println(drone);
        try{
            storeService.make_drone(storeName, droneId,capacity,fuel);
            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
//            System.out.println("Error:store_identifier_already_exists");
        }
    }

    //display drones
    @RequestMapping(value = "/drones")
    public List<Drone> findAllDrones(HttpServletRequest request){
//        http://localhost:8080/cs6310/team045/display_items?name=kroger
        String storeName = request.getParameter("name");

//        System.out.println(storeName);

        try{
            return storeService.display_drones(storeName);
//            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //fly drone
    @PostMapping(value = "/fly_drones")
    public void fly_drone(HttpServletRequest request){
//        http://localhost:8080/cs6310/team045/fly_drones?storeName=kroger&pilotAccount=ffig8&droneId=1
        //http://localhost:8080/cs6310/team045/fly_drones?storeName=target&pilotAccount=ffig8&droneId=1


        try{
            String storeName = request.getParameter("storeName");
            String pilotAccount = request.getParameter("pilotAccount");
            String droneId = request.getParameter("droneId");
            //System.out.println(droneId);

            storeService.flyDrone(storeName, pilotAccount, droneId);
            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
//            System.out.println("Error:store_identifier_already_exists");
        }
    }


}
