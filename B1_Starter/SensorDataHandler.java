public interface SensorDataHandler {
    void handle(SensorReading reading);
    void close();
}
