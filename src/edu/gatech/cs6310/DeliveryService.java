package edu.gatech.cs6310;
import java.util.*;

public class DeliveryService {

    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";
        // create TreeMaps, Set;
        Set<String> storeNames = new HashSet<>();
        TreeMap<String, Store> stores = new TreeMap<>();
        Set<String> licenceIDs = new HashSet<>();
        Set<String> pilotAccounts = new HashSet<>();
        TreeMap<String, Pilot> pilots = new TreeMap<>();
        Set<String> customerAccounts = new HashSet<>();
        TreeMap<String, Customer> customers = new TreeMap<>();

        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);

                if (tokens[0].equals("make_store")) {
                    if (storeNames.contains(tokens[1])){
                        System.out.println("ERROR:store_identifier_already_exists");
                    } else {
                        Store store = new Store(tokens[1], Integer.parseInt(tokens[2]));
                        // add to hashset and treemap;
                        storeNames.add(tokens[1]);
                        stores.put(tokens[1], store);
                        System.out.println("OK:change_completed");
                    }

                } else if (tokens[0].equals("display_stores")) {
                    Set<String> keys = stores.keySet();
                    for (String key: keys) {
                        Store store = stores.get(key);
                        System.out.println("name:" + store.getName() + ",revenue:" + store.getRevenue());
                    }
                    System.out.println("OK:display_completed");

                } else if (tokens[0].equals("sell_item")) {
                    if (!storeNames.contains(tokens[1])){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    } else {
                        Store store = stores.get(tokens[1]);
                        // String name, int weight, String store
                        Item item = new Item(tokens[2], Integer.parseInt(tokens[3]), tokens[1]);
                        store.addItem(item.getName(), item);
                    }
                    // The display_items command displays the information about all the items that are available for
                    //request/purchase at a specific store.
                } else if (tokens[0].equals("display_items")) {
                    if (storeNames.contains(tokens[1])){
                        Store store = stores.get(tokens[1]);
                        store.displayItems();
                        System.out.println("OK:display_completed");
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }

                    // The make_pilot command creates a pilot who could fly a drone later to support grocery deliveries.
                } else if (tokens[0].equals("make_pilot")) {
                    if (pilotAccounts.contains(tokens[1])){
                        System.out.println("ERROR:pilot_identifier_already_exists");
                    } else if (licenceIDs.contains(tokens[6])){
                        System.out.println("ERROR:pilot_license_already_exists");
                    } else {
                        Pilot pilot = new Pilot(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5],
                                tokens[6], Integer.parseInt(tokens[7]));
                        pilots.put(tokens[1], pilot);
                        pilotAccounts.add(tokens[1]);
                        licenceIDs.add(tokens[6]);
                        System.out.println("OK:change_completed");
                    }
                    // display the information about all the pilots who’ve been introduced in the system.
                } else if (tokens[0].equals("display_pilots")) {
                    Set<String> keys = pilots.keySet();
                    for (String key: keys) {
                        Pilot pilot = pilots.get(key);
                        System.out.println("name:"+pilot.getFirstName()+"_"+pilot.getLastName()+
                                ",phone:"+pilot.getPhoneNumber()+",taxID:"+pilot.getTaxID()+
                                ",licenseID:"+pilot.getLicenseID()+",experience:"+pilot.getExperience());
                    }
                    System.out.println("OK:display_completed");
                    // create a drone that can be used to deliver groceries to the appropriate customer
                    // when an order has been purchased
                } else if (tokens[0].equals("make_drone")) {
                    if (storeNames.contains(tokens[1])){
                        Store store = stores.get(tokens[1]);
                        // String store, String id, int capacity, int tripsBeforeRefueling
                        Drone drone = new Drone(tokens[1], tokens[2], Integer.parseInt(tokens[3]),
                                Integer.parseInt(tokens[4]));
                        store.addDrone(drone.getId(), drone);
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }
                    // displays the information about all the drones that can be used to
                    // deliver grocery orders for a specific store.
                } else if (tokens[0].equals("display_drones")) {
                    if (storeNames.contains(tokens[1])) {
                        Store store = stores.get(tokens[1]);
                        store.displayDrones();
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }
                    // assign the given pilot to take control of the given drone
                } else if (tokens[0].equals("fly_drone")) {
                    if (storeNames.contains(tokens[1])) {
                        if (pilotAccounts.contains(tokens[3])){
                            Store store = stores.get(tokens[1]);
                            Pilot pilot = pilots.get(tokens[3]);
                            store.flyDrone(tokens[2], pilot);
                        } else {
                            System.out.println("ERROR:pilot_identifier_does_not_exist");
                        }
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }
                    // create a customer who can start orders, request items and
                    // eventually purchase (or cancel) those orders
                } else if (tokens[0].equals("make_customer")) {
                    if (customerAccounts.contains(tokens[1])){
                        System.out.println("ERROR:customer_identifier_already_exists");
                    } else {
                        Customer customer = new Customer(tokens[1], tokens[2], tokens[3], tokens[4],
                                Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                        customerAccounts.add(tokens[1]);
                        customers.put(tokens[1], customer);
                        System.out.println("OK:change_completed");
                    }
                    // displays all the customers who have been introduced in the system
                } else if (tokens[0].equals("display_customers")) {
                    Set<String> keys = customers.keySet();
                    for (String key: keys) {
                        Customer customer = customers.get(key);
                        System.out.println("name:"+customer.getFirstName()+"_"+customer.getLastName()+
                                ",phone:"+customer.getPhoneNumber()+",rating:"+customer.getRating()+
                                ",credit:"+customer.getCredits());
                    }
                    System.out.println("OK:display_completed");
                    // create the initial “stub” for an order at a given store for a given customer
                } else if (tokens[0].equals("start_order")) {
                    if (storeNames.contains(tokens[1])){
                        Store store = stores.get(tokens[1]);
                        if (store.hasOrder(tokens[2])){
                            System.out.println("ERROR:order_identifier_already_exists");
                        } else if (!store.hasDrone(tokens[3])){
                            System.out.println("ERROR:drone_identifier_does_not_exist");
                        } else if (!customerAccounts.contains(tokens[4])) {
                            System.out.println("ERROR:customer_identifier_does_not_exist");
                        } else {
                            Customer customer = customers.get(tokens[4]);
                            // String id, String droneID, Customer customer
                            store.createOrder(tokens[2], tokens[3], customer);
                        }
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }
                    // display information about all the orders at a given store.
                } else if (tokens[0].equals("display_orders")) {
                    if (storeNames.contains(tokens[1])){
                        Store store = stores.get(tokens[1]);
                        store.displayOrders();
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }
                    // add an item to the designated order
                } else if (tokens[0].equals("request_item")) {
                    // store:tokens[1],order:tokens[2],item:tokens[3],quantity:tokens[4],unit_price:tokens[5]
                    if (storeNames.contains(tokens[1])){
                        Store store = stores.get(tokens[1]);
                        // order:tokens[2],item:tokens[3],quantity:tokens[4],unit_price:tokens[5]
                        store.requestItem(tokens[2], tokens[3], Integer.parseInt(tokens[4]),
                                Integer.parseInt(tokens[5]));
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }
                    //complete the purchase of the order and the delivery of the
                    //groceries to the appropriate customer
                } else if (tokens[0].equals("purchase_order")) {
                    if (storeNames.contains(tokens[1])){
                        Store store = stores.get(tokens[1]);
                        store.purchaseOrder(tokens[2]);
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }
                    // remove the order from the system without otherwise changing the system’ state
                } else if (tokens[0].equals("cancel_order")) {
                    if (storeNames.contains(tokens[1])){
                        Store store = stores.get(tokens[1]);
                        store.cancelOrder(tokens[2]);
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }

                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }
        System.out.println("simulation terminated");
        commandLineInput.close();
    }
}
