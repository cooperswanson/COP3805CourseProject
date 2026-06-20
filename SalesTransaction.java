public class SalesTransaction {

    private int productId;
    private String productName;
    private int quantitySold;
    private double totalAmount;

    public SalesTransaction(int productId,
                            String productName,
                            int quantitySold,
                            double totalAmount) {

        this.productId = productId;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {

        return "Product ID: " + productId +
               ", Product: " + productName +
               ", Quantity Sold: " + quantitySold +
               ", Total: $" + totalAmount;
    }
}