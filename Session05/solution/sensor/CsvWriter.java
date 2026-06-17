import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CsvWriter
 *
 * Implementiert SensorDataHandler.
 * Schreibt jedes empfangene SensorReading als CSV-Zeile in eine Datei.
 *
 * CSV-Format: seq,stationId,temperatureC,humidityPct
 * Beispiel:   1,S1,19.3,64.2
 *
 * Design by Contract:
 *   Konstruktor:
 *     Postcondition — Datei ist geöffnet, writer ist bereit.
 *     Wirft IOException wenn die Datei nicht geöffnet werden kann.
 *
 *   handle(reading):
 *     Precondition  — close() wurde noch nicht aufgerufen (writer != null).
 *     Postcondition — Eine CSV-Zeile wurde in den Puffer geschrieben.
 *                     Bei reading == null: keine Zeile, kein Absturz.
 *                     Bei writer == null:  keine Zeile, kein Absturz.
 *
 *   close():
 *     Postcondition — Puffer ist auf die Festplatte geschrieben,
 *                     Datei ist geschlossen, writer == null.
 *     Invariante    — Darf mehrfach aufgerufen werden (null-Guard).
 *
 * Wichtig: handle() kann keine IOException weiterreichen, weil
 * SensorDataHandler.handle() keine throws-Klausel deklariert.
 * IOException wird daher intern behandelt (catch in handle()).
 */
public class CsvWriter implements SensorDataHandler {

    private BufferedWriter writer;

    /**
     * Öffnet die CSV-Datei zum Schreiben.
     * Überschreibt eine vorhandene Datei (kein append).
     *
     * @param filename  Pfad zur Ausgabedatei
     * @throws IOException wenn die Datei nicht geöffnet werden kann
     */
    public CsvWriter(String filename) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(filename));
    }

    /**
     * Schreibt eine CSV-Zeile für das übergebene Reading.
     * Bei reading == null oder nach close(): stilles Ignorieren.
     */
    @Override
    public void handle(SensorReading reading) {
        if (reading == null) return;
        if (writer == null) return;   // close() wurde bereits aufgerufen
        try {
            writer.write(reading.getSeq() + ","
                       + reading.getStationId() + ","
                       + reading.getTemperatureC() + ","
                       + reading.getHumidityPct());
            writer.newLine();          // plattformunabhängig: \r\n auf Windows, \n auf Unix
        } catch (IOException e) {
            System.err.println("CsvWriter: Fehler beim Schreiben — " + e.getMessage());
        }
    }

    /**
     * Leert den Puffer, schreibt alle Daten auf die Festplatte
     * und schließt die Datei.
     * Darf mehrfach aufgerufen werden.
     */
    @Override
    public void close() {
        if (writer == null) return;
        try {
            writer.close();            // flush() + Datei schließen
        } catch (IOException e) {
            System.err.println("CsvWriter: Fehler beim Schließen — " + e.getMessage());
        } finally {
            writer = null;             // Guard: verhindert Doppel-close und NPE in handle()
        }
    }
}
