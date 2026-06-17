// ============================================================
// Aufgabe B1  —  DIESE Datei bearbeitest du.
// Ziel:  javac *.java && java MockTest   ->   "ALLE TESTS BESTANDEN"
// Die anderen Dateien (MockTest.java) NICHT veraendern.
// ============================================================

public class ThresholdPrinter implements SensorDataHandler {

    // TODO: Welche Felder brauchst du?
    //   - die Schwelle (kommt aus dem Konstruktor)
    //   - einen Zaehler fuer die ausgegebenen Readings

    public ThresholdPrinter(double schwelle) {
        // TODO: Schwelle speichern
    }

    @Override
    public void handle(SensorReading reading) {
        // TODO:
        //   reading == null              -> nichts tun (kein Druck, kein Absturz)
        //   temperatureC  >  schwelle    -> Zeile ausgeben UND mitzaehlen
        //   sonst                        -> nichts tun
        // Die ausgegebene Zeile MUSS reading.getStationId() enthalten.
    }

    @Override
    public void close() {
        // TODO: genau eine Zusammenfassungszeile ausgeben, Form:
        //   "ThresholdPrinter: <anzahl> Reading(s) ueber <schwelle> Grad C ausgegeben"
    }

    /* BEGRUENDUNG (2-3 Saetze) — hier hinein schreiben:
       Wie behandelst du den null-Fall, und warum ist diese Behandlung
       fuer einen Handler richtig?

       ...
    */
}
