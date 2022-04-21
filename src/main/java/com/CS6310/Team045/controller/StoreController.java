package com.CS6310.Team045.controller;

import com.CS6310.Team045.model.Drone;
import com.CS6310.Team045.model.Item;
import com.CS6310.Team045.model.Pilot;
import com.CS6310.Team045.model.Store;
import com.CS6310.Team045.services.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("user/cs6300/team045")
public class StoreController {
    @Autowired
    private StoreServiceImpl storeService;

    //get all stores
    @GetMapping(value = "/stores")
    public List<Store> findAllStores(){
        return storeService.getAllStores();
    }

    //make store
    @PostMapping(value = "/make_store")
    public void make_store(@RequestBody Store store){
        try{
            storeService.makeStore(store);
            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
//            System.out.println("Error:store_identifier_already_exists");
        }
    }

    //add item
    @PostMapping(value = "/sell_item")
    public void sell_item(@RequestBody Item item){
//        System.out.println(item.getName());
//        System.out.println(item.getWeight());
//        System.out.println(item.getSname());

        try{
            storeService.addItem(item);
            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(value = "/display_items")
    public List<Item> findAllItems(HttpServletRequest request){
//        http://localhost:8080/cs6300/team045/display_items?name=kroger
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
    public void make_drone(@RequestBody Drone drone){
//        System.out.println(drone);
        try{
            storeService.make_drone(drone);
            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
//            System.out.println("Error:store_identifier_already_exists");
        }
    }

    //display drones
    @RequestMapping(value = "/display_drones")
    public List<Drone> findAllDrones(HttpServletRequest request){
//        http://localhost:8080/cs6300/team045/display_items?name=kroger
        String droneName = request.getParameter("name");

//        System.out.println(storeName);

        try{
            return storeService.display_drones(droneName);
//            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //fly drone
    @RequestMapping(value = "/fly_drones")
    public void fly_drone(HttpServletRequest request){
//        http://localhost:8080/cs6300/team045/fly_drones?storeName=kroger&pilotAccount=ffig8&droneId=1
        String storeName = request.getParameter("storeName");
        String pilotAccount = request.getParameter("pilotAccount");
        String droneId = request.getParameter("droneId");

        try{
            storeService.flyDrone(storeName, pilotAccount, droneId);
            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println(e.getMessage());
//            System.out.println("Error:store_identifier_already_exists");
        }
    }


}
