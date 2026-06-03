import java.util.ArrayList;
import java.util.List;

/**
 * Main
 *
 * Musterlösung Session 02.
 * Demonstriert: Kapselung, Getter/Setter, Interfaces, processAll.
 */
public class Main {

    /**
     * Verarbeitet eine Liste von SensorReadings mit einem beliebigen Handler.
     *
     * Diese Methode kennt weder ConsolePrinter noch InMemoryStore —
     * nur den SensorDataHandler-Vertrag. Das ist der Punkt:
     * eine Komponente soll von einem Vertrag abhängen,
     * nicht von einer konkreten Implementierung.
     */
    public static void processAll(List<SensorReading> readings,
                                  SensorDataHandler handler) {
        for (SensorReading r : readings) {
            handler.handle(r);
        }
        handler.close();
    }
    /* 
     * eine Illustration wie man das Interface-Konzept
     * in diesem Fall sinnvoll nutzen könnte:
     * 1. um die gleichen Daten mehrfach zu verarbeiten (console + memory)
     * 2. um zur Laufzeit zu entscheiden, welche Verarbeitung man haben möchte
     *
     *
        // Pattern 1: Direktes Aufrufen der Handler-Methoden
        processAll(data, new ConsolePrinter());
        processAll(data, new InMemoryStore());
        
        // Pattern 2: at startup
        String mode = args[0]; // "console" or "memory"
        SensorDataHandler handler = mode.equals("console")
            ? new ConsolePrinter()
            : new InMemoryStore();
        processAll(data, handler);
    
    **************************************************************/
    public static void main(String[] args) {
        
        Describable d1 = new SensorReading(1, "S1", 19.3, 64.2);
        Describable d2 = new Station("Nord", "Freiburg");
        System.out.println("DESCRIBE d1: " + d1.describe());
        System.out.println("DESCRIBE d2: " + d2.describe());

        // ── Testdaten ─────────────────────────────────────────────────────
        List<SensorReading> data = new ArrayList<>();
        data.add(new SensorReading(1, "S1", 19.3,   64.2));
        data.add(new SensorReading(2, "S1", 22.1,   61.0));
        data.add(new SensorReading(3, "S1", -500.0, 58.0));  // ungültige Temperatur
        data.add(new SensorReading(4, "S1", 18.7,   150.0)); // ungültige Luftfeuchte
        data.add(null);                                        // null — defensiv behandelt

        // ── ConsolePrinter ────────────────────────────────────────────────
        System.out.println("=== ConsolePrinter ===");
        processAll(data, new ConsolePrinter());

        System.out.println();

        // ── InMemoryStore ─────────────────────────────────────────────────
        System.out.println("=== InMemoryStore ===");
        processAll(data, new InMemoryStore());

        System.out.println();

        // ── Kapselung: Setter-Validierung ─────────────────────────────────
        System.out.println("=== Setter-Validierung ===");
        SensorReading r = new SensorReading(5, "S2", 20.0, 55.0);
        
        System.out.println("Vor Setter:  " + r.describe());

        r.setTemperatureC(-999.0);  // wird abgefangen
        r.temperatureC = -999.0; // ← diese Zeile hinzufügen
        System.out.println("Nach Setter: " + r.describe());

        // Das hier kompiliert nicht — Beweis dass private funktioniert:
        // r.temperatureC = -999.0;  // ← Compilerfehler: temperatureC has private access
    }
}