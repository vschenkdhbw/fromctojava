/**
 * SensorDataHandler
 *
 * Vertrag für alle Komponenten die SensorReading-Objekte verarbeiten.
 * Eine Komponente soll von diesem Vertrag abhängen —
 * nicht von einer konkreten Implementierung.
 *
 * Design by Contract:
 *   handle(reading)
 *     Precondition:  Das Objekt ist offen (close() wurde noch nicht aufgerufen).
 *     Postcondition: Das Reading wurde verarbeitet (oder bei null ignoriert).
 *
 *   close()
 *     Precondition:  keine
 *     Postcondition: Alle Ressourcen sind freigegeben; alle gepufferten Daten
 *                    sind persistent (z.B. auf der Festplatte).
 *     Invariante:    Darf mehrfach aufgerufen werden ohne Fehler.
 */
public interface SensorDataHandler {
    void handle(SensorReading reading);
    void close();
}
