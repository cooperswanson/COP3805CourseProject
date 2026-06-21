import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL =
            "jdbc:mysql://localhost:3306/smartstock";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    public Connection connect() {

        try {

            Connection conn =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD);

            InventoryLogger.logInfo(
                    "Database connection established.");

            return conn;

        } catch (SQLException e) {

            InventoryLogger.logError(
                    "Database connection failed: "
                            + e.getMessage());

            return null;
        }
    }

    public void insertProduct(Product product) {

        String sql =
                "INSERT INTO products " +
                "(product_id, product_name, quantity, price) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, product.getProductId());
            stmt.setString(2, product.getProductName());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());

            stmt.executeUpdate();

            InventoryLogger.logInfo(
                    "Product saved to database: "
                            + product.getProductName());

        } catch (SQLException e) {

            InventoryLogger.logError(
                    "Insert failed: "
                            + e.getMessage());
        }
    }

    public void displayProductsFromDatabase() {

        String sql = "SELECT * FROM products";

        try (Connection conn = connect();
             PreparedStatement stmt =
                     conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println(
                    "\n=== DATABASE INVENTORY REPORT ===");

            while (rs.next()) {

                System.out.println(
                        "ID: " + rs.getInt("product_id")
                        + ", Name: " +
                        rs.getString("product_name")
                        + ", Quantity: " +
                        rs.getInt("quantity")
                        + ", Price: $" +
                        rs.getDouble("price"));
            }

        } catch (SQLException e) {

            InventoryLogger.logError(
                    "Query failed: "
                            + e.getMessage());
        }
    }
}