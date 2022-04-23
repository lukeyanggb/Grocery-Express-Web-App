package com.CS6310.Team045.services;

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
    public void makeStore(String name, Integer revenue) throws Exception {
//        System.out.println(name);
//        System.out.println(revenue);
//        System.out.println(storeRepository.findStoreByName(name));
        Optional<Store> opt = storeRepository.findStoreByName(name);
//        System.out.println(opt);
        if(opt.isPresent()){
            throw new Exception("Error:store_identifier_already_exists");
        } else {
            Store store = new Store(name, revenue);
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
    public void addItem(String name, String weight, String store) throws Exception {
        //String storeName = item.getStore().getName();
//        System.out.println(storeName);
 //       String itemName = item.getName();
//        System.out.println(itemName);
        Optional<Store> optionalStore = findStore(store);
//        System.out.println(store);
        Optional<Item> optItem = itemRepository.findByStore_nameAndName(store, name);
//        System.out.println(optItem);
        if(store.isEmpty()){
            throw new Exception("ERROR:store_identifier_does_not_exist");
        } else {
            if(optItem.isPresent()){
                throw new Exception("ERROR:item_identifier_already_exists");
            } else {
                Item item = new Item(name, Integer.parseInt(weight), optionalStore.get());
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
    public void make_drone(String storeName, String droneId,String capacity, String fuel) throws Exception {

        Optional<Store> store = findStore(storeName);
        Optional<Drone> optDrone= droneRepository.findByStore_nameAndId(storeName, droneId);

//        System.out.println(storeName);
//        System.out.println(droneID);
//        System.out.println(store);
//        System.out.println(optDrone);

        if(store.isEmpty()){
            throw new Exception("ERROR:store_identifier_does_not_exist");
        }
        if(optDrone.isPresent()){
            throw new Exception("ERROR:drone_identifier_already_exists");
        }

        Drone drone = new Drone(droneId, Integer.parseInt(capacity),Integer.parseInt(fuel), store.get());
        droneRepository.save(drone);
        System.out.println("finished");
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
        //System.out.println(optDrone);
        //System.out.println(accoutOpt);
        Pilot pilot = accoutOpt.get();
        //System.out.println(accoutOpt.get().getAccount());
        pilot.setControl(optDrone.get());
        pilotRepository.save(pilot);

//        System.out.println(optDrone);
//        System.out.println(accoutOpt);
    }

}
