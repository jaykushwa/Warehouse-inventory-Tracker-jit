package com.jit.warehouse;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Simple text-file persistence: each line -> productId|name|quantity|threshold
public class FileStorage {
    private File file;

    public FileStorage(String filePath) {
        this.file = new File(filePath);
    }

    public void save(Map<String, Product> inventory) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Product p : inventory.values()) {
                bw.write(p.toSaveString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to save inventory: " + e.getMessage());
        }
    }

    public Map<String, Product> load() {
        Map<String, Product> map = new HashMap<>();
        if (!file.exists()) return map;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Product p = Product.fromString(line);
                if (p != null) map.put(p.getProductId(), p);
            }
        } catch (IOException e) {
            System.err.println("Failed to load inventory: " + e.getMessage());
        }
        return map;
    }
}
