import java.util.ArrayList;

public class SalesManager {

    private ArrayList<SalesTransaction> sales;

    public SalesManager() {
        sales = new ArrayList<>();
    }

    public void recordSale(SalesTransaction sale) {

        sales.add(sale);

        InventoryLogger.logInfo(
            "Sale recorded for product."
        );
    }

    public void printSalesReport() {

        System.out.println("\n=== SALES REPORT ===");

        if (sales.isEmpty()) {
            System.out.println("No sales recorded.");
            return;
        }

        for (SalesTransaction sale : sales) {
            System.out.println(sale);
        }
    }
}