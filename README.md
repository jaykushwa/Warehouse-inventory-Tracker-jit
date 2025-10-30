# Warehouse Inventory Tracker (Event-Based)

**Language:** Java  
**Package:** `com.jit.warehouse`

## Project Overview
This is a small, interviewer-friendly Java application that simulates an event-driven warehouse inventory tracker.
It demonstrates the **Observer pattern** by triggering restock alerts whenever product quantity falls below a predefined threshold.
The project also includes a simple file-based persistence (bonus) so inventory is saved between runs.

## Features
- Maintain products (id, name, quantity, reorder threshold)
- Add products dynamically
- Receive shipments (increase stock)
- Fulfill orders (decrease stock)
- Automatically trigger restock alerts (Observer pattern)
- Simple file persistence to `inventory.txt`

## Files / Classes
- `Product.java` — POJO for product data and simple serialization helpers.
- `StockObserver.java` — Observer interface to receive low-stock notifications.
- `AlertService.java` — Implements `StockObserver`, prints alerts to console.
- `Warehouse.java` — Core inventory manager (add, receiveShipment, fulfillOrder). Uses `FileStorage`.
- `FileStorage.java` — Saves/loads inventory to a text file (`inventory.txt`).
- `Main.java` — Example usage & driver program.

## How to compile & run (Command Line)
1. Open a terminal and navigate to the `src` folder:
   ```bash
   cd WarehouseInventory/src
   ```
2. Compile:
   ```bash
   javac com/jit/warehouse/*.java
   ```
3. Run:
   ```bash
   java com.jit.warehouse.Main
   ```
4. After running, a file `inventory.txt` will be created in the folder where you ran the program (project root). It stores products in the format:
   ```
   productId|name|quantity|threshold
   ```

## Example Output
```
Added: Product[id=P101, name=Laptop, qty=0, threshold=5]
Added: Product[id=P102, name=Wireless Mouse, qty=20, threshold=5]
Received shipment: 10 units for Laptop. New qty=10
Order fulfilled: 6 units for Laptop. Remaining=4
[ALERT] Restock needed for Laptop (ID: P101) - only 4 left.
Order fulfilled: 3 units for Wireless Mouse. Remaining=17
Invalid Product ID: P999
Insufficient stock for Laptop. Available=4

Current Inventory:
Product[id=P101, name=Laptop, qty=4, threshold=5]
Product[id=P102, name=Wireless Mouse, qty=17, threshold=5]

Data persisted to file: inventory.txt (project root)
```

## What to explain in interview
- Why Observer pattern? -> decouples alerting logic from inventory logic.
- How file persistence works (simple, human-readable format).
- Thread-safety: `Warehouse` methods are synchronized to avoid race conditions (basic protection).
- Possible improvements: use JSON, add concurrency tests, multiple warehouses, REST API, unit tests.

## Notes
- No external libraries used — pure Java.
- Keep this project in your GitHub and share the repo link during interviews.

Good luck in your campus drive! 🚀
