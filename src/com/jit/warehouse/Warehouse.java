package com.jit.warehouse;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<String, Product> inventory;
    private StockObserver observer;
    private FileStorage storage;

    public Warehouse(StockObserver observer, FileStorage storage) {
        this.observer = observer;
        this.storage = storage;
        this.inventory = storage != null ? storage.load() : new HashMap<>();
    }

    // Add product dynamically
    public synchronized void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null");
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " already exists.");
            return;
        }
        inventory.put(product.getProductId(), product);
        persist();
        System.out.println("Added: " + product);
    }

    // Receive shipment (increase stock)
    public synchronized void receiveShipment(String productId, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }
        Product p = inventory.get(productId);
        if (p == null) {
            System.out.println("Invalid Product ID: " + productId);
            return;
        }
        p.setQuantity(p.getQuantity() + quantity);
        persist();
        System.out.println("Received shipment: " + quantity + " units for " + p.getName() + ". New qty=" + p.getQuantity());
    }

    // Fulfill customer order (decrease stock)
    public synchronized void fulfillOrder(String productId, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }
        Product p = inventory.get(productId);
        if (p == null) {
            System.out.println("Invalid Product ID: " + productId);
            return;
        }
        if (p.getQuantity() < quantity) {
            System.out.println("Insufficient stock for " + p.getName() + ". Available=" + p.getQuantity());
            return;
        }
        p.setQuantity(p.getQuantity() - quantity);
        System.out.println("Order fulfilled: " + quantity + " units for " + p.getName() + ". Remaining=" + p.getQuantity());
        if (p.getQuantity() < p.getReorderThreshold() && observer != null) {
            observer.onLowStock(p);
        }
        persist();
    }

    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    public Map<String, Product> getAllProducts() {
        return Collections.unmodifiableMap(inventory);
    }

    private void persist() {
        if (storage != null) storage.save(inventory);
    }
}
