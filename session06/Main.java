import java.io.*;
import java.util.*;

/**
 * Main — Session 06
 *
 * Zeigt die Session 06 Ergebnisse in Aktion:
 *   1. CsvWriterBroken-Experiment (aus dem Opener) — kommentiert als Erinnerung
 *   2. ExceptionDemo (Teil 2.1) — in eigener Datei
 *   3. DateiDemo (Teil 2.2) — in eigener Datei
 *   4. Bereinigter CsvWriter mit processAll
 *
 * Zum Ausführen:
 *   rm -f *.class && javac *.java && java Main
 */
public class Main {

    public static void processAll(List<SensorReading> readings, SensorDataHandler handler) {
        for (SensorReading r : readings) {
            handler.handle(r);
        }
        handler.close();
    }

    public static void main(String[] args) {
        List<SensorReading> data = new ArrayList<>();
        data.add(new SensorReading(1, "S1", 19.3, 64.2));
        data.add(new SensorReading(2, "S1", 22.1, 61.0));
        data.add(new SensorReading(3, "S1", -500.0, 58.0));  // ungültig — wird auf -273.15 gesetzt
        data.add(null);                                        // null — wird von AbstractSensorHandler abgefangen

        // ── ConsolePrinter (unverändert seit Session 05) ──────────────────
        System.out.println("=== ConsolePrinter ===");
        processAll(data, new ConsolePrinter());
        System.out.println();

        // ── CsvWriter (bereinigt in Session 06) ───────────────────────────
        // CsvWriter wirft jetzt throws IOException statt throws Exception.
        // Der Aufrufer muss das explizit behandeln.
        System.out.println("=== CsvWriter ===");
        try {
            processAll(data, new CsvWriter("/tmp/sensor_data.csv"));
            System.out.println("CSV geschrieben: /tmp/sensor_data.csv");
        } catch (IOException e) {
            // IOException vom Konstruktor: Datei konnte nicht geöffnet werden.
            // Das ist kein Programmierfehler — externer Fehler, legitim zu fangen.
            System.err.println("CSV konnte nicht geöffnet werden: " + e.getMessage());
        }

        // ── Ungültiger Pfad — IOException sichtbar machen ─────────────────
        System.out.println();
        System.out.println("=== CsvWriter mit ungültigem Pfad ===");
        try {
            processAll(data, new CsvWriter("/nicht/vorhanden/sensor_data.csv"));
        } catch (IOException e) {
            // Kein stilles Versagen — IOException ist sichtbar.
            // Vergleich mit Opener: dort hat der leere catch alles versteckt.
            System.err.println("Erwarteter Fehler: " + e.getMessage());
        }
    }
}
