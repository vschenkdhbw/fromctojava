/**
 * SensorDataHandler
 *
 * Vertrag für alle Komponenten die SensorReading-Objekte
 * verarbeiten. Eine Komponente soll von diesem Vertrag abhängen —
 * nicht von einer konkreten Implementierung.
 *
 * Vertrag:
 *   handle(reading) — verarbeitet ein einzelnes Reading.
 *                     Verhält sich bei reading == null defensiv:
 *                     Fehlermeldung auf System.err, kein Absturz.
 *   close()         — schließt die Verarbeitung ab, gibt
 *                     Ressourcen frei oder gibt eine Zusammenfassung aus.
 *                     Darf mehrfach aufgerufen werden ohne Fehler.
 */
public interface SensorDataHandler {
    void handle(SensorReading reading);
    void close();
}