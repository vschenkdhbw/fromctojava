/**
 * FilterPrinter — gibt nur Readings aus deren Temperatur über einem Schwellenwert liegt
 *
 * Musterlösung Session 05, Teil 4.2 (zweite Unterklasse).
 *
 * Zeigt: Unterklassen können eigene Konstruktor-Parameter haben.
 * Der Schwellenwert wird beim Erzeugen gesetzt — danach unveränderlich.
 */
public class FilterPrinter extends AbstractSensorHandler {

    private final double threshold;

    /**
     * @param threshold  Nur Readings mit temperatureC > threshold werden ausgegeben.
     */
    public FilterPrinter(double threshold) {
        this.threshold = threshold;
    }

    @Override
    protected void process(SensorReading reading) {
        // Precondition: reading != null — garantiert durch AbstractSensorHandler
        if (reading.getTemperatureC() > threshold) {
            System.out.println("[FILTER >" + threshold + "°C] " + reading.describe());
        }
        // sonst: still ignorieren — kein Zähler nötig, Basisklasse zählt bereits
    }
}
