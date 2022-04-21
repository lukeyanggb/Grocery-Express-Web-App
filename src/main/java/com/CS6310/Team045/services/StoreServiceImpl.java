package com.CS6310.Team045.services;

import com.CS6310.Team045.exception.BaseException;
import com.CS6310.Team045.model.*;
import com.CS6310.Team045.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService{
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
    public void makeStore(Store store) throws Exception {
        String name = store.getName();
        Optional<Store> opt = storeRepository.findStoreByName(name);
        if(opt.isPresent()){
            throw new Exception("Error:store_identifier_already_exists");
        } else {
            storeRepository.save(store);
        }
    }

    @Cacheable(value = "Store", key = "#store")
    public Optional<Store> findStore(String store){
        return storeRepository.findStoreByName(store);
    }



    // show all stores
    @Cacheable("Store")
    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }



    //add item to the store catalog
    public void addItem(Item item) throws Exception {
        String storeName = item.getSname();
//        System.out.println(storeName);
        String itemName = item.getName();
//        System.out.println(itemName);
        Optional<Store> store = findStore(storeName);
//        System.out.println(store);
        Optional<Item> optItem = itemRepository.findByStore_nameAndName(storeName, itemName);
//        System.out.println(optItem);
        if(store.isEmpty()){
            throw new Exception("ERROR:store_identifier_does_not_exist");
        } else {
            if(optItem.isPresent()){
                throw new Exception("ERROR:item_identifier_already_exists");
            } else {
                item.setStore(store.get());
                 itemRepository.save(item);}
        }
    }


    //show all item at a store
    @Cacheable(value = "Item", key = "#storeName")
    public List<Item> display_items(String storeName) throws Exception {
//        System.out.println(storeName);

        Optional<Store> opt = findStore(storeName);
        if(opt.isEmpty()){
            throw new Exception("ERROR:store_identifier_does_not_exist");
        }
        return itemRepository.getItemByStore(opt.get());
    }


    // create a pilot
    public void makePilot(Pilot pilot) throws Exception {
        String account = pilot.getAccount();;
        String licenseId = pilot.getLicenseID();
        Optional<Pilot> accoutOpt = pilotRepository.findPilotByAccount(account);
        Optional<Pilot> liceseIdOpt = pilotRepository.findPilotByLicenseID(licenseId);
        if(accoutOpt.isPresent()){
            throw new Exception("ERROR:pilot_identifier_already_exists");
        }
        if(liceseIdOpt.isPresent()){
            throw new Exception("ERROR:pilot_license_already_exists");
        }

        pilotRepository.save(pilot);
    }


    //display pilots
    @Cacheable("Pilot")
    public List<Pilot> display_pilots(){
        return pilotRepository.findAll();

    }


    // create a drone
    public void make_drone(Drone drone) throws Exception {
        String storeName = drone.getSname();
        String droneID = drone.getId();
        Optional<Store> store = findStore(storeName);
        Optional<Drone> optDrone= droneRepository.findByStore_nameAndId(storeName, droneID);

//        System.out.println(storeName);
//        System.out.println(droneID);
//        System.out.println(store);
//        System.out.println(optDrone);

        if(store.isEmpty()){
            throw new Exception("ERROR:store_identifier_does_not_exist");
        }
        drone.setStore(store.get());
        if(optDrone.isPresent()){
            throw new Exception("ERROR:drone_identifier_already_exists");
        }
        droneRepository.save(drone);
//        System.out.println(droneRepository.findByStore_nameAndId(storeName, droneID));

//                System.out.println(drone);

    }

    //show all drones in a store
    @Cacheable(value = "Drone", key = "#store")
    public List<Drone> display_drones(String store) throws Exception {
        Optional<Store> opt = findStore(store);
        if(opt.isEmpty()){
            throw new Exception("ERROR:store_identifier_does_not_exist");
        }
        return droneRepository.findDroneByStore(opt.get());
    }


    //fly a drone
    public void flyDrone(String storeName, String pilotAccount, String droneId) throws Exception {
        Optional<Store> store = findStore(storeName);
        if (store.isEmpty()) {
            throw new Exception("ERROR:store_identifier_does_not_exist");
        }
        Optional<Drone> optDrone = droneRepository.findByStore_nameAndId(storeName, droneId);
        if (optDrone.isEmpty()) {
            throw new Exception("ERROR:drone_identifier_does_not_exist");
        }
        Optional<Pilot> accoutOpt = pilotRepository.findPilotByAccount(pilotAccount);
        if (accoutOpt.isEmpty()) {
            throw new Exception("ERROR:pilot_identifier_does_not_exist");
        }
        System.out.println(optDrone);
        System.out.println(accoutOpt);

        optDrone.get().assign(accoutOpt.get());
        droneRepository.save(optDrone.get());
        accoutOpt.get().assign(optDrone.get());
        pilotRepository.save(accoutOpt.get());
//        System.out.println(optDrone);
//        System.out.println(accoutOpt);
    }

}
