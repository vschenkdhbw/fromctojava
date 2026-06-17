/**
 * SensorDataHandler — Session 02
 *
 * Vertrag für alle Handler die SensorReadings verarbeiten.
 * close() ist Teil des Vertrags: seit Session 04 wissen wir warum.
 */
public interface SensorDataHandler {
    void handle(SensorReading reading);
    void close();
}
