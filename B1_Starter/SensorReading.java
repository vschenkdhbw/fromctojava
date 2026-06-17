public class SensorReading {
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
            System.err.println("Ungueltige Temperatur: " + t);
            return -273.15;
        }
        return t;
    }

    public int    getSeq()          { return seq; }
    public String getStationId()    { return stationId; }
    public double getTemperatureC() { return temperatureC; }
    public double getHumidityPct()  { return humidityPct; }
}
