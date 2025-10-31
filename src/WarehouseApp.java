import java.util.Scanner;

public class WarehouseApp {
    private static final Inventory inventory = new Inventory();
    private static final Scanner sc = new Scanner(System.in);

    public static void showMenu() {
        System.out.println("\n===== 🏭 Warehouse Inventory Tracker =====");
        System.out.println("1️⃣ Add Product");
        System.out.println("2️⃣ Delete Product");
        System.out.println("3️⃣ Restock Product");
        System.out.println("4️⃣ View Inventory");
        System.out.println("5️⃣ Search Product");
        System.out.println("6️⃣ Exit");
        System.out.print("👉 Choose option: ");
    }

    public static void mainMenu() {
        int choice;
        do {
            showMenu();
            choice = sc.nextInt();
            sc.nextLine(); // clear input buffer

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> deleteProduct();
                case 3 -> restockProduct();
                case 4 -> inventory.viewInventory();
                case 5 -> searchProduct();
                case 6 -> System.out.println("👋 Exiting system...");
                default -> System.out.println("❌ Invalid option!");
            }
        } while (choice != 6);
    }

    private static void addProduct() {
        System.out.print("Enter Product ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        inventory.addProduct(new Product(id, name, qty, price));
    }

    private static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        String id = sc.nextLine();
        inventory.deleteProduct(id);
    }

    private static void restockProduct() {
        System.out.print("Enter Product ID to restock: ");
        String id = sc.nextLine();
        System.out.print("Enter quantity to add: ");
        int qty = sc.nextInt();
        inventory.restockProduct(id, qty);
    }

    private static void searchProduct() {
        System.out.print("Enter Product Name to search: ");
        String name = sc.nextLine();
        inventory.searchProduct(name);
    }
            }
