package com.jit.warehouse;

public class Main {
    public static void main(String[] args) {
        // File persistence in the project root (relative)
        String dataFile = "inventory.txt";
        FileStorage storage = new FileStorage(dataFile);
        StockObserver alertService = new AlertService();
        Warehouse warehouse = new Warehouse(alertService, storage);

        // If no products yet, add sample product(s)
        if (warehouse.getAllProducts().isEmpty()) {
            Product laptop = new Product("P101", "Laptop", 0, 5);
            Product mouse = new Product("P102", "Wireless Mouse", 20, 5);
            warehouse.addProduct(laptop);
            warehouse.addProduct(mouse);
        }

        // Example workflow (similar to assignment description)
        warehouse.receiveShipment("P101", 10); // Laptop now 10
        warehouse.fulfillOrder("P101", 6);     // Laptop now 4 -> should trigger alert
        warehouse.fulfillOrder("P102", 3);     // Mouse now 17

        // Demonstrate invalid operations
        warehouse.fulfillOrder("P999", 1);     // invalid id
        warehouse.fulfillOrder("P101", 10);    // insufficient stock

        System.out.println("\nCurrent Inventory:");
        warehouse.getAllProducts().values().forEach(System.out::println);

        System.out.println("\nData persisted to file: inventory.txt (project root)");
    }
}
