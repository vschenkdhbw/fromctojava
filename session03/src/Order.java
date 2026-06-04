/**
 * Order
 *
 * Einfaches Datenmodell für eine Bestellung.
 * Wird in Übung 3 (Payment-Processor) verwendet.
 *
 * Absichtlich minimal gehalten — der Fokus liegt auf dem
 * Interface-Konzept, nicht auf dem Domänenmodell.
 */
public class Order {

    private String orderId;
    private double amount;
    private String description;

    public Order(String orderId, double amount, String description) {
        this.orderId     = orderId;
        this.amount      = amount > 0 ? amount : 0.0;
        this.description = description != null ? description : "";
    }

    public String getOrderId()     { return orderId; }
    public double getAmount()      { return amount; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "Order[" + orderId + ", " + amount + " EUR, \"" + description + "\"]";
    }
}
