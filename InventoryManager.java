import java.util.ArrayList;

public class InventoryManager {

    private ArrayList<Product> products;
    
    private DatabaseManager databaseManager;

    public InventoryManager() {
        products = new ArrayList<>();
        databaseManager = new DatabaseManager();

        InventoryLogger.logInfo(
                "Inventory Manager initialized.");
    }

    public void addProduct(Product product) {

        products.add(product);

        databaseManager.insertProduct(product);

        InventoryLogger.logInfo(
                "Product added: "
                        + product.getProductName());
    }

    public Product findProduct(int productId) {

        for (Product product : products) {

            if (product.getProductId() == productId) {

                InventoryLogger.logInfo(
                    "Product search successful for ID: "
                    + productId
                );

                return product;
            }
        }

        InventoryLogger.logWarning(
            "Product not found. ID: " + productId
        );

        return null;
    }

    public void displayProducts() {

        InventoryLogger.logInfo(
            "Displaying all products."
        );

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }
    }
}