/**
 * CreditCardProcessor
 *
 * Implementiert PaymentProcessor für Kreditkartenzahlungen.
 *
 * Musterlösung für Übung 3.3.
 * Studierende schreiben diese Klasse selbst — diese Datei
 * dient als Referenz nach der Session.
 */
public class CreditCardProcessor implements PaymentProcessor {

    /**
     * Verarbeitet eine Kreditkartenzahlung.
     * Gibt true zurück wenn die Zahlung erfolgreich war.
     * Bei order == null: Fehlermeldung auf System.err, return false.
     */
    @Override
    public boolean process(Order order) {
        if (order == null) {
            System.err.println("CreditCardProcessor: null-Order empfangen — übersprungen");
            return false;
        }
        // In der Realität: Karte validieren, Betrag abbuchen, Transaktion loggen.
        // Hier: Simulation.
        System.out.println("Kreditkarte: " + order.getAmount()
            + " EUR abgebucht für " + order.getDescription()
            + " (Order " + order.getOrderId() + ")");
        return true;
    }
}
