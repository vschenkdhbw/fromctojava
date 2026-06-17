import java.io.*;

/**
 * CsvWriter — Session 04, bereinigt in Session 06
 *
 * Schreibt SensorReadings als CSV-Zeilen in eine Datei.
 * Erbt null-Check und Zähler von AbstractSensorHandler.
 *
 * Session 06 — was sich geändert hat:
 *
 *   1. throws Exception → throws IOException im Konstruktor
 *      throws Exception sagt: "irgendetwas kann schiefgehen".
 *      throws IOException sagt: "genau dieser externe Fehler ist möglich — und warum."
 *      Der Aufrufer weiß jetzt was ihn erwartet. Das ist ehrliche Kapselung.
 *
 *   2. close() mit try/finally abgesichert
 *      super.close() muss immer laufen — auch wenn writer.close() eine IOException wirft.
 *      finally garantiert das.
 */
public class CsvWriter extends AbstractSensorHandler {

    private BufferedWriter writer;

    /**
     * Öffnet die CSV-Datei zum Schreiben.
     *
     * throws IOException — nicht throws Exception.
     * IOException ist der spezifische Fehler der hier auftreten kann:
     * Pfad existiert nicht, keine Schreibrechte, Festplatte voll.
     * Den Aufrufer mit throws Exception im Dunkeln zu lassen wäre
     * eine Verletzung des Vertrags.
     */
    public CsvWriter(String filename) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(filename));
    }

    /**
     * Schreibt eine CSV-Zeile: seq,stationId,temperatureC,humidityPct
     *
     * process() kann throws IOException nicht deklarieren —
     * die abstrakte Methode in AbstractSensorHandler hat keine checked Exception
     * in ihrer Signatur. IOException wird daher auf System.err geloggt.
     *
     * Precondition (garantiert durch AbstractSensorHandler.handle()):
     *   reading ist nicht null.
     */
    @Override
    protected void process(SensorReading reading) {
        try {
            writer.write(
                reading.getSeq()          + ","
                + reading.getStationId()  + ","
                + reading.getTemperatureC() + ","
                + reading.getHumidityPct()
            );
            writer.newLine();
        } catch (IOException e) {
            System.err.println("CsvWriter: Schreibfehler — " + e.getMessage());
        }
    }

    /**
     * Schließt den BufferedWriter und gibt die Zähler-Zusammenfassung aus.
     *
     * Warum log-and-continue statt rethrow?
     *
     *   Option A — auf System.err loggen, dann super.close() (gewählt):
     *     Der Fehler wird sichtbar. super.close() läuft garantiert.
     *     Das ist das Verhalten der Java-Standardbibliothek (z.B. InputStream.close()).
     *
     *   Option B — IOException weiterwerfen (throws IOException in Signatur):
     *     Problem: wenn bereits eine Exception in flight ist (z.B. aus process()),
     *     würde eine zweite Exception aus close() die erste still verschlucken.
     *     Dieses "exception masking" ist ein bekannter Java-Fallstrick.
     *     Deshalb: close()-Fehler loggen, nicht werfen.
     *
     *   Option C — leer fangen:
     *     Stilles Versagen — genau das was wir im Opener gezeigt haben. Niemals.
     */
    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.err.println("CsvWriter: Fehler beim Schließen — " + e.getMessage());
        } finally {
            super.close();   // läuft garantiert — egal ob writer.close() wirft oder nicht
        }
    }
}
