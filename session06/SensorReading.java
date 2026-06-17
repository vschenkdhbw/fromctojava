/**
 * SensorReading — seit Session 01, erweitert in Session 02
 *
 * Kapselt einen Messwert: private Felder, Getter, validierte Setter.
 * Implementiert Describable (Session 02).
 */
public class SensorReading implements Describable {

    private int    seq;
    private String stationId;
    private double temperatureC;
    private double humidityPct;

    public SensorReading(int seq, String stationId, double temperatureC, double humidityPct) {
        this.seq          = seq;
        this.stationId    = stationId;
        this.temperatureC = validateTemperature(temperatureC);
        this.humidityPct  = humidityPct;
    }

    private double validateTemperature(double t) {
        if (t < -273.15) {
            System.err.println("Ungültige Temperatur: " + t + " → auf -273.15 gesetzt");
            return -273.15;
        }
        return t;
    }

    public void setTemperatureC(double temperatureC) {
        this.temperatureC = validateTemperature(temperatureC);
    }

    public int    getSeq()          { return seq; }
    public String getStationId()    { return stationId; }
    public double getTemperatureC() { return temperatureC; }
    public double getHumidityPct()  { return humidityPct; }

    @Override
    public String describe() {
        return "Reading " + seq + ": " + temperatureC + "°C @ " + stationId;
    }
}
