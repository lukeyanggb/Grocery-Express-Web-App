package edu.gatech.cs6310;
import java.util.*;

public class DeliveryService {

    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";
        // create TreeMaps, Set;
        Set<String> storeNames = new HashSet<String>();
        TreeMap<String, Store> stores = new TreeMap<String, Store>();
        Set<String> licenceIDs = new HashSet<String>();
        Set<String> pilotAccounts = new HashSet<String>();
        TreeMap<String, Pilot> pilots = new TreeMap<String, Pilot>();

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
                        Store store = new Store(tokens[1], Double.parseDouble(tokens[2]));
                        // add to hashset and treemap;
                        storeNames.add(tokens[1]);
                        stores.put(tokens[1], store);
                        System.out.println("OK:change_completed");
                    }

                } else if (tokens[0].equals("display_stores")) {
                    Set<String> keys = stores.keySet();
                    for (String key: keys) {
                        Store store = stores.get(key);
                        System.out.println("name:" + store.getName() + "revenue:" + store.getRevenue());
                    }
                    System.out.println("OK:display_completed");

                } else if (tokens[0].equals("sell_item")) {
                    System.out.println("store: " + tokens[1] + ", item: " + tokens[2] + ", weight: " + tokens[3]);
                    if (!storeNames.contains(tokens[1])){
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    } else {
                        Store store = stores.get(tokens[0]);
                        Item item = new Item(tokens[2], Double.parseDouble(tokens[3]), tokens[1]);
                        store.addItem(item.getName(), item);
                    }
                // The display_items command displays the information about all the items that are available for
                //request/purchase at a specific store.
                } else if (tokens[0].equals("display_items")) {
                    System.out.println("store: " + tokens[1]);
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

                } else if (tokens[0].equals("display_pilots")) {
                    System.out.println("no parameters needed");

                } else if (tokens[0].equals("make_drone")) {
                    System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", capacity: " + tokens[3] + ", fuel: " + tokens[4]);

                } else if (tokens[0].equals("display_drones")) {
                    System.out.println("store: " + tokens[1]);

                } else if (tokens[0].equals("fly_drone")) {
                    System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", pilot: " + tokens[3]);

                } else if (tokens[0].equals("make_customer")) {
                    System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
                    System.out.println(", phone: " + tokens[4] + ", rating: " + tokens[5] + ", credit: " + tokens[6]);

                } else if (tokens[0].equals("display_customers")) {
                    System.out.println("no parameters needed");

                } else if (tokens[0].equals("start_order")) {
                    System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", drone: " + tokens[3] + ", customer: " + tokens[4]);

                } else if (tokens[0].equals("display_orders")) {
                    System.out.println("store: " + tokens[1]);

                } else if (tokens[0].equals("request_item")) {
                    System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", item: " + tokens[3] + ", quantity: " + tokens[4] + ", unit_price: " + tokens[5]);

                } else if (tokens[0].equals("purchase_order")) {
                    System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);

                } else if (tokens[0].equals("cancel_order")) {
                    System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);

                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;

                } else {
                    System.out.println("command " + tokens[0] + " NOT acknowledged");
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
