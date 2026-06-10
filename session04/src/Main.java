import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Main
 *
 * Musterlösung Session 04.
 *
 * Demonstriert:
 *   - File I/O: FileWriter, BufferedWriter, flush/close
 *   - CsvWriter als SensorDataHandler (Filesystem-Handler)
 *   - Design by Contract: Precondition, Postcondition, Invariant
 *   - MultiHandler: Komposition von SensorDataHandlern
 *   - Timing: FileWriter vs. BufferedWriter bei großen Datenmengen
 *
 * Aus Session 03 übernommen (unverändert):
 *   - processAll(List<SensorReading>, SensorDataHandler)
 *   - ConsolePrinter, InMemoryStore
 *   - SensorReading, SensorDataHandler, Describable
 *
 * Nicht mehr in main() enthalten (in eigenen Dateien / auskommentiert):
 *   - PaymentProcessor / Order / CreditCardProcessor  (Session 03, Übung 3)
 *   - Collections / Iterator / List<Describable>      (Session 03, zur Referenz
 *                                                      in den auskommentierten
 *                                                      Blöcken unten)
 */
public class Main {

    // ── processAll: unverändert seit Session 02 ───────────────────────────────
    //
    // Diese Methode hat sich in vier Sessions nicht verändert.
    // Sie kennt weder ConsolePrinter, InMemoryStore, CsvWriter noch MultiHandler —
    // nur den SensorDataHandler-Vertrag.
    // Das ist der Kern des Interface-Prinzips.

    public static void processAll(List<SensorReading> readings,
                                  SensorDataHandler handler) {
        for (SensorReading r : readings) {
            handler.handle(r);
        }
        handler.close();
    }

    // ── main ──────────────────────────────────────────────────────────────────

    public static void main(String[] args) throws Exception {

        // ── Testdaten ────────────────────────────────────────────────────────
        List<SensorReading> data = new ArrayList<>();
        data.add(new SensorReading(1, "S1", 19.3,   64.2));
        data.add(new SensorReading(2, "S1", 22.1,   61.0));
        data.add(new SensorReading(3, "S1", -500.0, 58.0));  // ungültig → -273.15
        data.add(null);                                        // defensiver Testfall


        // ════════════════════════════════════════════════════════════════════
        // 1. ConsolePrinter — aus Session 02, unverändert
        // ════════════════════════════════════════════════════════════════════
        System.out.println("═══ ConsolePrinter ═══");
        processAll(data, new ConsolePrinter());
        System.out.println();


        // ════════════════════════════════════════════════════════════════════
        // 2. CsvWriter — neu in Session 04
        //
        // CsvWriter ist der erste Handler der wirklich etwas tut wenn
        // close() aufgerufen wird: Puffer leeren + Datei schließen.
        // processAll ruft close() garantiert auf — deshalb sitzt close()
        // seit Session 02 im Interface.
        // ════════════════════════════════════════════════════════════════════
        System.out.println("═══ CsvWriter ═══");
        processAll(data, new CsvWriter("sensor_data.csv"));
        System.out.println("sensor_data.csv geschrieben.");
        System.out.println();

        // Ergebnis in sensor_data.csv:
        //   1,S1,19.3,64.2
        //   2,S1,22.1,61.0
        //   3,S1,-273.15,58.0   ← Setter hat -500.0 korrigiert
        //   (null-Reading wurde übersprungen)


        // ════════════════════════════════════════════════════════════════════
        // 3. MultiHandler — neu in Session 04
        //
        // processAll erwartet genau einen SensorDataHandler.
        // MultiHandler implements SensorDataHandler — also passt er hinein.
        // processAll kennt MultiHandler nicht. Es sieht nur SensorDataHandler.
        // Dasselbe Prinzip wie List<Describable>: der Aufrufer kennt
        // nur den Vertrag, nicht die konkreten Typen dahinter.
        // ════════════════════════════════════════════════════════════════════
        System.out.println("═══ MultiHandler ═══");
        MultiHandler multi = new MultiHandler();
        multi.add(new ConsolePrinter());
        multi.add(new CsvWriter("sensor_data_multi.csv"));

        processAll(data, multi);   // eine Zeile — beide Handler laufen
        System.out.println("sensor_data_multi.csv geschrieben.");
        System.out.println();

        // processAll hat sich nicht geändert.
        // multi.handle() delegiert an ConsolePrinter.handle() + CsvWriter.handle().
        // multi.close() delegiert an ConsolePrinter.close() + CsvWriter.close().
        // Es ist alles handle() — und alles close().


        // ════════════════════════════════════════════════════════════════════
        // 4. Timing-Experiment: FileWriter vs. BufferedWriter
        //
        // Aufgabe für Studierende (Teil 2.4):
        // Schreibt timingTest() selbst. Ihr braucht:
        //   - System.currentTimeMillis() → gibt einen long zurück
        //   - Eine for-Schleife mit 50.000 Iterationen
        //   - Einmal roher FileWriter, einmal BufferedWriter
        //   - Differenz berechnen und ausgeben
        // ════════════════════════════════════════════════════════════════════
        System.out.println("═══ Timing-Experiment ═══");
        timingTest();
        System.out.println();


        // ════════════════════════════════════════════════════════════════════
        // Aus Session 03 — auskommentiert, zur Referenz
        // ════════════════════════════════════════════════════════════════════

        // // PaymentProcessor (Übung 3, Session 03):
        // List<Order> orders = new ArrayList<>();
        // orders.add(new Order("ORD-001", 49.99, "Sensorkabel"));
        // orders.add(new Order("ORD-002", 129.00, "Arduino Mega"));
        // processAll(orders, new CreditCardProcessor());

        // // Collections und Iteratoren (Session 03):
        // List<Integer> zahlen = new ArrayList<>();
        // zahlen.add(10); zahlen.add(20); zahlen.add(30);
        // Iterator<Integer> it = zahlen.iterator();
        // while (it.hasNext()) { System.out.println(it.next()); }

        // // List<Describable> (Session 03, C.5):
        // List<Describable> things = new ArrayList<>();
        // things.add(new SensorReading(1, "S1", 19.3, 64.2));
        // things.add(new Station("Nord", "Freiburg"));
        // things.add(new Sensor("TEMP-01", "°C"));
        // for (Describable d : things) { System.out.println(d.describe()); }
    }


    // ── timingTest ────────────────────────────────────────────────────────────
    //
    // Musterlösung für Teil 2.4.
    // Studierende schreiben diese Methode selbst — nur System.currentTimeMillis()
    // wird als Hinweis gegeben. Der Rest (for-Schleife, Differenz, Ausgabe)
    // folgt aus dem was sie bereits wissen.

    public static void timingTest() throws Exception {
        int n = 50_000;

        // Version 1: roher FileWriter — ein Systemaufruf pro write()
        long start1 = System.currentTimeMillis();
        Writer fw = new FileWriter("timing_raw.csv");
        for (int i = 0; i < n; i++) {
            fw.write(i + ",S1,19.3,64.2\n");
        }
        fw.close();
        long time1 = System.currentTimeMillis() - start1;

        // Version 2: BufferedWriter — Daten gesammelt, gebündelt geschrieben
        long start2 = System.currentTimeMillis();
        BufferedWriter bw = new BufferedWriter(new FileWriter("timing_buffered.csv"));
        for (int i = 0; i < n; i++) {
            bw.write(i + ",S1,19.3,64.2");
            bw.newLine();
        }
        bw.close();
        long time2 = System.currentTimeMillis() - start2;

        System.out.println("Roher FileWriter:  " + time1 + " ms");
        System.out.println("BufferedWriter:    " + time2 + " ms");
        System.out.println("Faktor:            " + (time1 / Math.max(1, time2)) + "x");
        System.out.println("(Beide Dateien enthalten identische Daten — nur die Zeit unterscheidet sich)");
    }
}
