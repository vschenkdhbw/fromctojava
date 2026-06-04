import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Main
 *
 * Musterlösung Session 03.
 *
 * Demonstriert:
 *   - Polymorphismus: processAll mit SensorDataHandler und PaymentProcessor
 *   - Collections: List<T> mit verschiedenen Typparametern
 *   - Expliziter Iterator: hasNext() / next()
 *   - For-each als Iterator-Kurzform
 *   - List<Describable>: heterogene Objekte, ein Vertrag
 */
public class Main {

    // ── processAll: SensorDataHandler ────────────────────────────────────────

    /**
     * Verarbeitet eine Liste von SensorReadings mit einem beliebigen Handler.
     *
     * Diese Methode kennt weder ConsolePrinter noch InMemoryStore —
     * nur den SensorDataHandler-Vertrag.
     */
    public static void processAll(List<SensorReading> readings,
                                  SensorDataHandler handler) {
        for (SensorReading r : readings) {
            handler.handle(r);
        }
        handler.close();
    }

    // ── processAll: PaymentProcessor ─────────────────────────────────────────

    /**
     * Verarbeitet eine Liste von Orders mit einem beliebigen PaymentProcessor.
     *
     * Dieselbe Struktur wie processAll für SensorReadings —
     * dasselbe Prinzip, andere Domäne.
     * Diese Methode kennt weder CreditCardProcessor noch PayPalProcessor.
     */
    public static void processAll(List<Order> orders,
                                  PaymentProcessor processor) {
        for (Order o : orders) {
            boolean success = processor.process(o);
            if (!success) {
                System.err.println("Zahlung fehlgeschlagen: " + o);
            }
        }
    }

    // ── main ──────────────────────────────────────────────────────────────────

    public static void main(String[] args) {

        // ── 1. SensorDataHandler: processAll wie in Session 02 ───────────────
        System.out.println("═══ SensorDataHandler ═══");

        List<SensorReading> sensorData = new ArrayList<>();
        sensorData.add(new SensorReading(1, "S1", 19.3,   64.2));
        sensorData.add(new SensorReading(2, "S1", 22.1,   61.0));
        sensorData.add(new SensorReading(3, "S1", -500.0, 58.0));  // ungültig
        sensorData.add(null);                                        // defensiv

        processAll(sensorData, new ConsolePrinter());
        System.out.println();
        processAll(sensorData, new InMemoryStore());
        System.out.println();

        // ── 2. PaymentProcessor: Übung 3 ─────────────────────────────────────
        System.out.println("═══ PaymentProcessor ═══");

        List<Order> orders = new ArrayList<>();
        orders.add(new Order("ORD-001", 49.99, "Sensorkabel"));
        orders.add(new Order("ORD-002", 129.00, "Arduino Mega"));
        orders.add(new Order("ORD-003", 9.95, "Breadboard"));

        processAll(orders, new CreditCardProcessor());
        System.out.println();

        // ── 3. Collections: verschiedene Typparameter ─────────────────────────
        System.out.println("═══ Collections: Typparameter ═══");

        List<Integer> zahlen    = new ArrayList<>();
        List<String>  namen     = new ArrayList<>();

        zahlen.add(10); zahlen.add(20); zahlen.add(30); zahlen.add(40); zahlen.add(50);
        namen.add("Freiburg"); namen.add("Müllheim"); namen.add("Basel");
        namen.add("Breisach"); namen.add("Staufen");

        System.out.println("Zahlen: " + zahlen.size() + " Einträge");
        System.out.println("Drittes Element: " + namen.get(2));

        // ── 4. Expliziter Iterator ────────────────────────────────────────────
        System.out.println("\n═══ Expliziter Iterator ═══");

        int summe = 0;
        Iterator<Integer> it = zahlen.iterator();
        while (it.hasNext()) {
            int wert = it.next();
            System.out.println("  " + wert);
            summe += wert;
        }
        System.out.println("Summe: " + summe);

        // ── 5. For-each: dieselbe Summe, weniger Syntax ───────────────────────
        System.out.println("\n═══ For-Each ═══");

        int summeForeach = 0;
        for (int wert : zahlen) {
            summeForeach += wert;
        }
        System.out.println("Summe (for-each): " + summeForeach);

        // ── 6. List<Describable>: verschiedene Klassen, ein Vertrag ──────────
        System.out.println("\n═══ List<Describable> ═══");

        List<Describable> things = new ArrayList<>();
        things.add(new SensorReading(1, "S1", 19.3, 64.2));
        things.add(new Station("Nord", "Freiburg"));
        things.add(new SensorReading(2, "S2", 22.1, 61.0));
        things.add(new Station("Süd", "Basel"));
        things.add(new Sensor("TEMP-01", "°C"));   // neue Klasse — for-each unverändert
        things.add(new Sensor("HUM-01",  "%rH"));

        for (Describable d : things) {
            System.out.println(d.describe());
        }
        // Die for-each-Schleife hat sich nicht geändert als Sensor hinzukam.
        // Sie kennt nur den Vertrag (Describable) — nicht die konkreten Klassen.
    }
}
