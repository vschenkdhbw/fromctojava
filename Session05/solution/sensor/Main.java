import java.util.ArrayList;
import java.util.List;

/**
 * Main — Musterlösung Session 05
 *
 * Demonstriert:
 *   - Opener:  Vererbung als Antwort auf Copy-Paste-Problem (Animal/Dog/Cat — eigene Dateien)
 *   - Teil 2:  Shape-Hierarchie mit purer Vererbung
 *   - Teil 3:  Template Method Pattern — describe() in Basisklasse gezogen
 *   - Teil 4:  AbstractSensorHandler, SummaryPrinter, FilterPrinter
 *   - Teil 5:  Dreierfrage (extends / implements / Komposition) — im Code ablesbar
 *
 * processAll() ist unverändert seit Session 02.
 * Alle neuen Handler (SummaryPrinter, FilterPrinter) passen hinein —
 * weil sie SensorDataHandler implementieren (via AbstractSensorHandler).
 */
public class Main {

    // ── processAll: unverändert seit Session 02 ───────────────────────────────
    public static void processAll(List<SensorReading> readings,
                                  SensorDataHandler handler) {
        for (SensorReading r : readings) {
            handler.handle(r);
        }
        handler.close();
    }

    public static void main(String[] args) throws Exception {

        // ════════════════════════════════════════════════════════════════════
        // TEIL 2 + 3: Shape-Hierarchie
        // ════════════════════════════════════════════════════════════════════
        System.out.println("═══ Shape-Hierarchie (Teil 2 + 3) ═══");

        Shape circle    = new Circle("Blau", 2.0);
        Shape rectangle = new Rectangle("Rot", 4.0, 3.0);
        Shape triangle  = new Triangle("Grün", 3.0, 4.0, 5.0);

        // describe() kommt von Shape — wurde in Teil 3 dorthin gezogen
        // area() kommt von der jeweiligen Unterklasse — Template Method Pattern
        circle.describe();       // Farbe: Blau | Fläche: 12.57
        rectangle.describe();    // Farbe: Rot  | Fläche: 12.00
        triangle.describe();     // Farbe: Grün | Fläche: 6.00

        System.out.println();

        // Polymorphismus: List<Shape> mit gemischten Objekten
        List<Shape> shapes = new ArrayList<>();
        shapes.add(circle);
        shapes.add(rectangle);
        shapes.add(triangle);

        System.out.println("Alle Shapes:");
        for (Shape s : shapes) {
            s.describe();   // welche area() aufgerufen wird, entscheidet der Objekttyp
        }

        System.out.println();


        // ════════════════════════════════════════════════════════════════════
        // TEIL 4: AbstractSensorHandler — neue Handler
        // ════════════════════════════════════════════════════════════════════
        List<SensorReading> data = new ArrayList<>();
        data.add(new SensorReading(1, "S1", 19.3,   64.2));
        data.add(new SensorReading(2, "S1", 22.1,   61.0));
        data.add(new SensorReading(3, "S1", -500.0, 58.0));  // ungültig → -273.15
        data.add(new SensorReading(4, "S1", 35.7,   55.0));
        data.add(null);                                        // defensiver Testfall

        // SummaryPrinter: gibt jedes Reading aus + Min/Max beim close()
        System.out.println("═══ SummaryPrinter ═══");
        processAll(data, new SummaryPrinter());
        System.out.println();

        // FilterPrinter: nur Readings über Schwellenwert
        System.out.println("═══ FilterPrinter (Schwellenwert: 20.0°C) ═══");
        processAll(data, new FilterPrinter(20.0));
        System.out.println();


        // ════════════════════════════════════════════════════════════════════
        // Aus Session 04 — weiterhin funktionsfähig, unverändert
        // ════════════════════════════════════════════════════════════════════
        System.out.println("═══ ConsolePrinter (Session 02, unverändert) ═══");
        processAll(data, new ConsolePrinter());
        System.out.println();

        System.out.println("═══ MultiHandler (Session 04, unverändert) ═══");
        MultiHandler multi = new MultiHandler();
        multi.add(new ConsolePrinter());
        multi.add(new CsvWriter("sensor_data_s05.csv"));
        processAll(data, multi);
        System.out.println();


        // ════════════════════════════════════════════════════════════════════
        // TEIL 5: Dreierfrage — im Code ablesbar
        //
        // IST EIN  (extends):
        //   SummaryPrinter  extends  AbstractSensorHandler
        //   Circle          extends  Shape
        //
        // KANN EIN  (implements):
        //   AbstractSensorHandler  implements  SensorDataHandler
        //   SensorReading          implements  Describable
        //
        // HAT EIN  (Komposition):
        //   MultiHandler   hat  List<SensorDataHandler>
        //   CsvWriter      hat  BufferedWriter
        //   FilterPrinter  hat  double threshold
        // ════════════════════════════════════════════════════════════════════
        System.out.println("═══ Dreierfrage — Nachweis ═══");
        System.out.println("SummaryPrinter IST EIN AbstractSensorHandler IST EIN SensorDataHandler");
        System.out.println("→ processAll akzeptiert SummaryPrinter ohne Änderung.");
        SensorDataHandler handler = new SummaryPrinter();   // IST EIN — funktioniert
        processAll(data, handler);
    }
}
