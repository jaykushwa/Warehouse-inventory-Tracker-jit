package com.jit.warehouse;

public class AlertService implements StockObserver {
    @Override
    public void onLowStock(Product product) {
        System.out.println("[ALERT] Restock needed for " + product.getName()
                + " (ID: " + product.getProductId() + ") - only " + product.getQuantity() + " left.");
    }
}
