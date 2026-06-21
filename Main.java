import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        InventoryManager inventoryManager = new InventoryManager();
        SalesManager salesManager = new SalesManager();

        InventoryLogger.logInfo("Program started.");

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println(" SmartStock Inventory System");
            System.out.println("=================================");
            System.out.println("1. Add Product");
            System.out.println("2. Search Product");
            System.out.println("3. Process Sale");
            System.out.println("4. Inventory Report");
            System.out.println("5. Sales Report");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    // Add Product

                    System.out.print("Enter Product ID: ");
                    int id = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter Product Name: ");
                    String name = input.nextLine();

                    System.out.print("Enter Quantity: ");
                    int quantity = input.nextInt();

                    System.out.print("Enter Price: ");
                    double price = input.nextDouble();

                    Product product =
                            new Product(id, name, quantity, price);

                    inventoryManager.addProduct(product);

                    InventoryLogger.logInfo(
                            "Product added: " + name);

                    System.out.println("Product added successfully.");
                    break;

                case 2:
                    // Search Product

                    System.out.print("Enter Product ID: ");
                    int searchId = input.nextInt();

                    Product found =
                            inventoryManager.findProduct(searchId);

                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Product not found.");
                    }

                    break;

                case 3:
                    // Process Sale

                    System.out.print("Enter Product ID: ");
                    int saleId = input.nextInt();

                    Product saleProduct =
                            inventoryManager.findProduct(saleId);

                    if (saleProduct == null) {

                        System.out.println(
                                "Product not found.");

                        InventoryLogger.logWarning(
                                "Attempted sale of invalid product ID: "
                                        + saleId);

                        break;
                    }

                    System.out.print("Enter Quantity Sold: ");
                    int qtySold = input.nextInt();

                    if (qtySold > saleProduct.getQuantity()) {

                        System.out.println(
                                "Insufficient inventory.");

                        InventoryLogger.logWarning(
                                "Insufficient inventory for product ID: "
                                        + saleId);

                        break;
                    }

                    saleProduct.updateQuantity(-qtySold);

                    double total =
                            qtySold * saleProduct.getPrice();

                    SalesTransaction sale =
                            new SalesTransaction(
                                    saleProduct.getProductId(),
                                    saleProduct.getProductName(),
                                    qtySold,
                                    total);

                    salesManager.recordSale(sale);

                    System.out.println(
                            "Sale completed.");
                    System.out.printf(
                            "Invoice Total: $%.2f%n",
                            total);

                    break;

                case 4:
                    // Inventory Report

                    System.out.println(
                            "\n=== INVENTORY REPORT ===");

                    inventoryManager.displayProducts();

                    break;

                case 5:
                    // Sales Report

                    salesManager.printSalesReport();

                    break;

                case 6:

                    InventoryLogger.logInfo(
                            "Program terminated.");

                    System.out.println(
                            "Thank you for using SmartStock.");

                    break;

                default:
                    System.out.println(
                            "Invalid menu option.");
            }

        } while (choice != 6);

        input.close();
    }
}