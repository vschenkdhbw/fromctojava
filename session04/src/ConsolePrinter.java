/**
 * ConsolePrinter
 *
 * Implementiert SensorDataHandler.
 * Gibt jedes empfangene SensorReading formatiert auf System.out aus.
 *
 * Design by Contract:
 *   handle(): Precondition — reading darf null sein (wird defensiv behandelt).
 *             Postcondition — Reading wurde auf System.out ausgegeben.
 *   close():  Postcondition — Abschlusszeile mit Anzahl ausgegeben.
 *             Keine Ressourcen zu schließen (Konsole bleibt offen).
 */
public class ConsolePrinter implements SensorDataHandler {

    private int count = 0;

    @Override
    public void handle(SensorReading reading) {
        if (reading == null) {
            System.err.println("ConsolePrinter: null-Reading empfangen — übersprungen");
            return;
        }
        reading.print();
        count++;
    }

    @Override
    public void close() {
        System.out.println("── ConsolePrinter: " + count + " Readings ausgegeben ──");
    }
}
