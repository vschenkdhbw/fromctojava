import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// Testharness fuer Aufgabe B1 — NICHT veraendern.
// Aufruf:  javac *.java && java MockTest
public class MockTest {

    public static void main(String[] args) {
        // Eingabe: eine ueber, eine unter, null (mitten drin!), eine ueber der Schwelle 20.0
        List<SensorReading> daten = new ArrayList<>();
        daten.add(new SensorReading(1, "S1", 22.1, 61.0)); // ueber 20  -> ausgeben
        daten.add(new SensorReading(2, "S2", 19.3, 64.2)); // unter 20  -> ignorieren
        daten.add(null);                                   // null      -> ignorieren
        daten.add(new SensorReading(3, "S3", 25.0, 50.0)); // ueber 20  -> ausgeben

        ThresholdPrinter printer = new ThresholdPrinter(20.0);

        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        boolean absturz = false;
        String ausgabe = "";
        try {
            System.setOut(new PrintStream(buffer, true));
            for (SensorReading r : daten) {
                printer.handle(r);   // muss auch null aushalten
            }
            printer.close();
        } catch (Exception e) {
            absturz = true;
        } finally {
            System.setOut(original);
            ausgabe = buffer.toString();
        }

        System.out.println("----- Ausgabe deines ThresholdPrinter -----");
        System.out.print(ausgabe);
        if (!ausgabe.endsWith("\n")) System.out.println();
        System.out.println("-------------------------------------------");

        int bestanden = 0, gesamt = 0;
        gesamt++; if (!absturz)                 { bestanden++; ok("Test 1: kein Absturz (auch bei null)"); }            else fail("Test 1: Absturz! handle(null) wirft eine Exception");
        gesamt++; if (ausgabe.contains("S1"))   { bestanden++; ok("Test 2: Reading ueber Schwelle (S1) ausgegeben"); } else fail("Test 2: S1 fehlt in der Ausgabe");
        gesamt++; if (ausgabe.contains("S3"))   { bestanden++; ok("Test 3: Reading ueber Schwelle (S3) ausgegeben"); } else fail("Test 3: S3 fehlt in der Ausgabe");
        gesamt++; if (!ausgabe.contains("S2"))  { bestanden++; ok("Test 4: Reading unter Schwelle (S2) NICHT ausgegeben"); } else fail("Test 4: S2 wurde faelschlich ausgegeben");
        gesamt++; if (ausgabe.contains("2 Reading")) { bestanden++; ok("Test 5: close()-Zusammenfassung nennt Anzahl 2"); } else fail("Test 5: '2 Reading...' fehlt in der close()-Ausgabe");

        System.out.println();
        if (bestanden == gesamt) System.out.println(">>> ALLE TESTS BESTANDEN (" + bestanden + "/" + gesamt + ")");
        else                     System.out.println(">>> " + bestanden + "/" + gesamt + " Tests bestanden - weiter so.");
    }

    static void ok(String s)   { System.out.println("  [OK]   " + s); }
    static void fail(String s) { System.out.println("  [FAIL] " + s); }
}
