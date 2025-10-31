public class Product {
    private String id;
    private String name;
    private int quantity;
    private double price;

    public Product(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    public void restock(int amount) {
        this.quantity += amount;
    }

    public boolean reduceStock(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
            return true;
        }
        return false;
    }

    public boolean needsRestock() {
        return quantity < 5;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Qty: %d | Price: ₹%.2f", id, name, quantity, price);
    }
}
