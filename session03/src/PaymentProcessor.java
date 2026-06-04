/**
 * PaymentProcessor
 *
 * Vertrag für alle Zahlungsarten.
 *
 * Unterschied zu SensorDataHandler: process() gibt einen boolean zurück —
 * Erfolg oder Misserfolg der Transaktion. Der Aufrufer (processAll) kann
 * darauf reagieren, ohne zu wissen welche Zahlungsart verwendet wird.
 *
 * Ein Interface ist ein Vertrag — keine Implementierung, keine Felder,
 * nur Signaturen. Völlig unterschiedliche Klassen (Kreditkarte, PayPal,
 * Überweisung) können denselben Vertrag unterschreiben.
 *
 * In C: eine Funktion kennt alle Zahlungsarten und muss angepasst werden
 * wenn eine neue hinzukommt. In Java: jede Zahlungsart kennt nur sich selbst.
 */
public interface PaymentProcessor {

    /**
     * Verarbeitet eine einzelne Bestellung.
     *
     * @param order  die zu verarbeitende Bestellung — darf nicht null sein
     * @return true wenn die Zahlung erfolgreich war, false sonst
     */
    boolean process(Order order);
}
