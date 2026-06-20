import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
    InventoryLogger.logInfo(
        "Program started."
    );
        Scanner input = new Scanner(System.in);
        InventoryManager inventoryManager = new InventoryManager();

        int choice;

        do {
            System.out.println("\n=== SmartStock Inventory Control System ===");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter Product Name: ");
                    String name = input.nextLine();

                    System.out.print("Enter Quantity: ");
                    int quantity = input.nextInt();

                    System.out.print("Enter Price: ");
                    double price = input.nextDouble();

                    Product product = new Product(id, name, quantity, price);
                    inventoryManager.addProduct(product);

                    System.out.println("Product added successfully.");
                    InventoryLogger.logInfo(
                        "User entered product information for: " + name
                    );
                    break;

                case 2:
                    inventoryManager.displayProducts();
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    int searchId = input.nextInt();

                    Product found = inventoryManager.findProduct(searchId);

                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    InventoryLogger.logInfo(
                        "Program terminated."
                    );
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        input.close();
    }
}