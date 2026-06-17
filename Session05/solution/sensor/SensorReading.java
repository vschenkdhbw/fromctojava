/**
 * SensorReading
 *
 * Repräsentiert eine einzelne Sensornachricht aus dem MQTT-System.
 *
 * Invarianten (gelten nach der Konstruktion und nach jedem Setter-Aufruf):
 *   temperatureC >= -273.15  (absoluter Nullpunkt)
 *   humidityPct  >= 0.0 und <= 100.0
 *   stationId    != null  (leerer String ist erlaubt, null nicht)
 */
public class SensorReading implements Describable {

    // ── Felder ────────────────────────────────────────────────────────────
    private int    seq;
    private String stationId;
    private double temperatureC;
    private double humidityPct;

    // ── Konstruktor ───────────────────────────────────────────────────────

    /**
     * Erzeugt ein neues SensorReading.
     * Ungültige Werte werden auf den nächsten gültigen Grenzwert gesetzt;
     * eine Fehlermeldung erscheint auf System.err.
     */
    public SensorReading(int seq, String stationId,
                         double temperatureC, double humidityPct) {
        this.seq          = seq;
        this.stationId    = stationId != null ? stationId : "UNKNOWN";
        this.temperatureC = validateTemperature(temperatureC);
        this.humidityPct  = validateHumidity(humidityPct);
    }

    // ── Getter ────────────────────────────────────────────────────────────

    public int    getSeq()          { return seq; }
    public String getStationId()    { return stationId; }
    public double getTemperatureC() { return temperatureC; }
    public double getHumidityPct()  { return humidityPct; }

    // ── Setter ────────────────────────────────────────────────────────────
    // seq und stationId haben keinen Setter:
    //   seq       — Sequenznummer ist unveränderlich nach der Konstruktion
    //   stationId — Station ändert sich im Betrieb nicht

    public void setTemperatureC(double temperatureC) {
        this.temperatureC = validateTemperature(temperatureC);
    }

    public void setHumidityPct(double humidityPct) {
        this.humidityPct = validateHumidity(humidityPct);
    }

    // ── Methoden ──────────────────────────────────────────────────────────

    /**
     * Gibt das Reading in einem lesbaren Format auf System.out aus.
     */
    public void print() {
        System.out.println(describe());
    }

    /**
     * Implementiert Describable.
     * Gibt eine einzeilige lesbare Beschreibung zurück.
     */
    @Override
    public String describe() {
        return stationId
            + " | seq=" + seq
            + " | " + temperatureC + "°C"
            + " | " + humidityPct + "%";
    }

    // ── Private Hilfsmethoden ─────────────────────────────────────────────

    private double validateTemperature(double t) {
        if (t < -273.15) {
            System.err.println("Ungültige Temperatur: " + t
                + " — Fallback auf -273.15");
            return -273.15;
        }
        return t;
    }

    private double validateHumidity(double h) {
        if (h < 0.0) {
            System.err.println("Ungültige Luftfeuchte: " + h
                + " — Fallback auf 0.0");
            return 0.0;
        }
        if (h > 100.0) {
            System.err.println("Ungültige Luftfeuchte: " + h
                + " — Fallback auf 100.0");
            return 100.0;
        }
        return h;
    }
}
