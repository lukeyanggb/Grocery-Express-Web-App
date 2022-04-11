package com.CS6310.Team045.services;

import com.CS6310.Team045.exception.BaseException;
import com.CS6310.Team045.model.*;
import com.CS6310.Team045.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

   //display_stores
    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }
   //make store
    public Store saveStore(Store store){
        Optional<Store> optional = storeRepository.findById(store.getName());
        if(optional.isPresent()){
            throw new RuntimeException("ERROR:store_identifier_already_exists");
        }else{
            return this.storeRepository.save(store);
        }

    }

    public Store getStoreByName(String name){
        Optional<Store> store = storeRepository.findById(name);
        if(store.isPresent()){
            return store.get();
        }else{
            throw new RuntimeException("ERROR:store_identifier_does_not_exist");
        }
    }
    //display items

}
