
import java.util.*;

public class Inventory {
    private Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            System.out.println("❌ Product ID already exists!");
        } else {
            products.put(product.getId(), product);
            System.out.println("✅ Product added successfully!");
        }
    }

    public void deleteProduct(String id) {
        if (products.remove(id) != null) {
            System.out.println("🗑️ Product deleted successfully!");
        } else {
            System.out.println("⚠️ Product not found!");
        }
    }

    public void restockProduct(String id, int amount) {
        Product product = products.get(id);
        if (product != null) {
            product.restock(amount);
            System.out.println("🔄 Product restocked successfully!");
        } else {
            System.out.println("⚠️ Product not found!");
        }
    }

    public void viewInventory() {
        if (products.isEmpty()) {
            System.out.println("📦 Inventory is empty!");
        } else {
            System.out.println("\n--- 🏷️ Product List ---");
            for (Product p : products.values()) {
                System.out.println(p);
                if (p.needsRestock()) {
                    System.out.println("⚠️ Low Stock Alert! Restock soon!");
                }
            }
        }
    }

    public void searchProduct(String name) {
        boolean found = false;
        for (Product p : products.values()) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.println("🔍 Found: " + p);
                found = true;
            }
        }
        if (!found) System.out.println("❌ Product not found!");
    }
}
