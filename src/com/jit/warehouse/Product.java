package com.jit.warehouse;

import java.util.Objects;

public class Product {
    private String productId;
    private String name;
    private int quantity;
    private int reorderThreshold;

    public Product(String productId, String name, int quantity, int reorderThreshold) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.reorderThreshold = reorderThreshold;
    }

    // Factory to parse from a saved line: id|name|quantity|threshold
    public static Product fromString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 4) return null;
        String id = parts[0];
        String name = parts[1];
        int qty = Integer.parseInt(parts[2]);
        int thr = Integer.parseInt(parts[3]);
        return new Product(id, name, qty, thr);
    }

    public String toSaveString() {
        return productId + "|" + name + "|" + quantity + "|" + reorderThreshold;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public int getReorderThreshold() { return reorderThreshold; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return String.format("Product[id=%s, name=%s, qty=%d, threshold=%d]",
                productId, name, quantity, reorderThreshold);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
