package com.CS6310.Team045.controller;

import com.CS6310.Team045.model.Store;
import com.CS6310.Team045.services.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreController {
    @Autowired
    private StoreServiceImpl storeService;

    //get all stores
    @GetMapping("/stores")
    List<Store> findAll(){
        return storeService.getAllStores();
    }

    //make store
    //@PostMapping("/stores")
    //Store makeStore(@RequestBody Store store){
     //   return storeService.saveStore(store);
    //}
}
