/**
 * ConsolePrinter — Session 02, überarbeitet Session 05
 *
 * Gibt jedes Reading formatiert auf der Konsole aus.
 * Erbt null-Check und Zähler von AbstractSensorHandler.
 */
public class ConsolePrinter extends AbstractSensorHandler {

    @Override
    protected void process(SensorReading reading) {
        System.out.println(reading.getStationId()
            + " │ seq="        + reading.getSeq()
            + " │ "            + reading.getTemperatureC() + "°C"
            + " │ "            + reading.getHumidityPct()  + "%");
    }

    @Override
    public void close() {
        super.close();   // Zähler-Zusammenfassung von AbstractSensorHandler
    }
}
