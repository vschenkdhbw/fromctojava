/**
 * ConsolePrinter
 *
 * Implementiert SensorDataHandler.
 * Gibt jedes empfangene SensorReading formatiert auf System.out aus.
 * Zählt die verarbeiteten Readings und gibt die Anzahl auf close() aus.
 */
public class ConsolePrinter implements SensorDataHandler {

    private int count = 0;

    /**
     * Gibt das Reading auf der Konsole aus.
     * Bei reading == null: Fehlermeldung auf System.err, kein Absturz.
     *
     * Warum null prüfen? Weil das Interface den null-Fall nicht spezifiziert
     * und der Aufrufer (z.B. processAll) nicht garantiert, dass readings
     * niemals null enthält. Defensive Programmierung schützt hier vor einem
     * NullPointerException der schwer zu debuggen wäre.
     */
    @Override
    public void handle(SensorReading reading) {
        if (reading == null) {
            System.err.println("ConsolePrinter: null-Reading empfangen — übersprungen");
            return;
        }
        reading.print();
        count++;
    }

    /**
     * Gibt eine Abschlusszeile aus.
     * Darf mehrfach aufgerufen werden.
     */
    @Override
    public void close() {
        System.out.println("── ConsolePrinter: " + count + " Readings ausgegeben ──");
    }
}