/**
 * SummaryPrinter — gibt jedes Reading aus und zeigt Min/Max-Temperatur beim close()
 *
 * Musterlösung Session 05, Teil 4.2.
 *
 * Erbt von AbstractSensorHandler:
 *   - null-Check und Zähler kommen kostenlos von der Basisklasse
 *   - process() ist der einzige Pflicht-Beitrag dieser Klasse
 *   - close() erweitert die Basisklassen-Version via super.close()
 */
public class SummaryPrinter extends AbstractSensorHandler {

    private double minTemp =  Double.MAX_VALUE;
    private double maxTemp = -Double.MAX_VALUE;

    @Override
    protected void process(SensorReading reading) {
        // Precondition: reading != null — garantiert durch AbstractSensorHandler
        System.out.println(reading.describe());
        if (reading.getTemperatureC() < minTemp)
            minTemp = reading.getTemperatureC();
        if (reading.getTemperatureC() > maxTemp)
            maxTemp = reading.getTemperatureC();
    }

    /**
     * Erweitert close() der Basisklasse: zuerst Zähler (super), dann Temperatur.
     */
    @Override
    public void close() {
        super.close();   // Basisklasse: "X verarbeitet, Y übersprungen"
        if (getHandledCount() > 0) {
            System.out.println("   Min-Temperatur: " + minTemp + " °C");
            System.out.println("   Max-Temperatur: " + maxTemp + " °C");
        }
    }
}
