package com.CS6310.Team045.services;

import com.CS6310.Team045.model.*;
import com.CS6310.Team045.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl {
    @Autowired
    private StoreRepository storeRepository;

   //display_stores
    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }
   //make store


    //display items

}
