package com.CS6310.Team045.services;

import com.CS6310.Team045.exception.BaseException;
import com.CS6310.Team045.model.*;
import com.CS6310.Team045.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PilotRepository pilotRepository;

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private ItemLineRepository itemLineRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StoreRepository storeRepository;
    //create store
    public void makeStore(Store store){
        String name = store.getName();
        Optional<Store> opt = findStore(name);
        if(opt.isPresent()){
            throw new IllegalArgumentException();
        }
        storeRepository.save(store);
    }
    public Optional<Store> findStore(String store){
        return storeRepository.findById(store);
    }


    // show all stores
    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }


    //add item to the store catalog
    public Item addItem(Item item){
        String storeName = item.getStore().getName();
        String itemName = item.getName();
        Optional<Store> opt = findStore(storeName);
        Optional<Item> optItem = Optional.of(itemRepository.getById(itemName));
        if(opt.isEmpty()){
            throw new IllegalArgumentException();
        }
        if(optItem.isPresent()){
           throw new IllegalArgumentException();
        }
        return itemRepository.save(item);}

    //show all item at a store
    public List<Item> display_items(String storeName){

        Optional<Store> opt = findStore(storeName);
        if(opt.isEmpty()){
            throw new IllegalArgumentException();
        }
        return itemRepository.getItemByStore(storeName);
    }


    // create a pilot
    public void make_pilot(Pilot pilot){
        String account = pilot.getAccount();;
        String licenseId = pilot.getLicenseID();
        Optional<Pilot> accoutOpt = pilotRepository.findPilotByAccount(account);
        Optional<Pilot> liceseIdOpt = pilotRepository.findPilotByLicenseID(licenseId);
        if(accoutOpt.isPresent()){
            throw new IllegalArgumentException();
        }
        if(liceseIdOpt.isPresent()){
            throw new IllegalArgumentException();
        }

        pilotRepository.save(pilot);
    }
    //display pilots
    public List<Pilot> display_pilots(){
        return pilotRepository.findAll();

    }


    // create a drone
    public void make_drone(Drone drone){
        Store store = drone.getStore();
        String droneID = drone.getId();
        if(findStore(store.getName()).isEmpty()){
            throw new IllegalArgumentException();
        }
        if(droneRepository.findDronesByStoreAndAndId(store.getName(),droneID).isPresent()){
            throw new IllegalArgumentException();
        }
        droneRepository.save(drone);
    }

    //show all drones in a store
    public List<Drone> display_drones(String store){
        Optional<Store> opt = findStore(store);
        if(opt.isEmpty()){
            throw new IllegalArgumentException();
        }
        return droneRepository.findDroneByStore(store);
    }


    //fly a drone
    public void flyDrone(Drone drone, Pilot pilot) {
        drone.assign(pilot);
        pilot.assign(drone);
    }


}
