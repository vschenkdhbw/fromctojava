import java.util.ArrayList;
import java.util.List;

/**
 * InMemoryStore
 *
 * Implementiert SensorDataHandler.
 * Speichert empfangene SensorReadings in einer Liste im Arbeitsspeicher.
 * Gibt auf close() eine Zusammenfassung aus: Anzahl, Min- und Max-Temperatur.
 */
public class InMemoryStore implements SensorDataHandler {

    private List<SensorReading> readings = new ArrayList<>();

    /**
     * Speichert das Reading in der internen Liste.
     * Bei reading == null: Fehlermeldung auf System.err, kein Absturz.
     *
     * Warum null prüfen? Dieselbe Begründung wie in ConsolePrinter:
     * das Interface garantiert keinen null-freien Eingabestrom.
     * Ein null in der ArrayList würde close() bei der Auswertung
     * mit einer NullPointerException zum Absturz bringen.
     */
    @Override
    public void handle(SensorReading reading) {
        if (reading == null) {
            System.err.println("InMemoryStore: null-Reading empfangen — übersprungen");
            return;
        }
        readings.add(reading);
    }

    /**
     * Gibt eine Zusammenfassung aller gespeicherten Readings aus.
     * Darf mehrfach aufgerufen werden.
     */
    @Override
    public void close() {
        System.out.println("── InMemoryStore: " + readings.size() + " Readings gespeichert ──");

        if (readings.isEmpty()) {
            System.out.println("   (keine Daten)");
            return;
        }

        double min = readings.get(0).getTemperatureC();
        double max = readings.get(0).getTemperatureC();

        for (SensorReading r : readings) {
            if (r.getTemperatureC() < min) min = r.getTemperatureC();
            if (r.getTemperatureC() > max) max = r.getTemperatureC();
        }

        System.out.println("   Min-Temperatur: " + min + "°C");
        System.out.println("   Max-Temperatur: " + max + "°C");
    }
}