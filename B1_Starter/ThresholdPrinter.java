public class ThresholdPrinter implements SensorDataHandler {
    private double schwelle;
    private int    anzahl = 0;

    public ThresholdPrinter(double schwelle) {
        this.schwelle = schwelle;
    }

    @Override
    public void handle(SensorReading reading) {
        if (reading == null) return;                 // null abfangen
        if (reading.getTemperatureC() > schwelle) {
            System.out.println(reading.getStationId() + " | "
                             + reading.getTemperatureC() + " Grad C");
            anzahl++;
        }
    }

    @Override
    public void close() {
        System.out.println("ThresholdPrinter: " + anzahl
                         + " Reading(s) ueber " + schwelle + " Grad C ausgegeben");
    }
}