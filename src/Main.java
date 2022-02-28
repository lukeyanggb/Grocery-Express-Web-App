import edu.gatech.cs6310.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Grocery Express Delivery Service!");
        //test
        double a = 6.444;
        System.out.println("this is "+a);
        //
        DeliveryService simulator = new DeliveryService();
        simulator.commandLoop();
    }
}
