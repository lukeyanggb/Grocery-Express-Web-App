package com.CS6310.Team045.controller;

import com.CS6310.Team045.model.Store;
import com.CS6310.Team045.services.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cs6300/team045")
public class StoreController {
    @Autowired
    private StoreServiceImpl storeService;

    //get all stores
    @GetMapping(value = "/stores")
    public List<Store> findAll(){
        return storeService.getAllStores();
    }

    //make store
    @PostMapping(value = "/createstore")
    public void makeStore(@RequestBody Store store){
        try{
            storeService.makeStore(store);
            System.out.println("OK, change_completed");
        }catch (Exception e){
            System.out.println("Error:store_identifier_already_exists");
        }
    }

}
