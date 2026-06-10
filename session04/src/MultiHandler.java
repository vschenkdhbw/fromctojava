import java.util.ArrayList;
import java.util.List;

/**
 * MultiHandler
 *
 * Implementiert SensorDataHandler und enthält eine Liste von SensorDataHandlern.
 * Delegiert jeden handle()- und close()-Aufruf an alle enthaltenen Handler.
 *
 * Kernprinzip: MultiHandler IST selbst ein SensorDataHandler.
 * processAll kennt MultiHandler nicht — es sieht nur SensorDataHandler.
 * Das ist dasselbe Prinzip wie List<Describable>: der Aufrufer kennt
 * nur den Vertrag, nicht die konkreten Typen dahinter.
 *
 * Design by Contract:
 *   add(handler):
 *     Postcondition — handler wird bei allen zukünftigen Aufrufen
 *                     von handle() und close() berücksichtigt.
 *
 *   handle(reading):
 *     Postcondition — handle(reading) wurde auf jedem enthaltenen
 *                     Handler aufgerufen (in Reihenfolge der Hinzufügung).
 *                     Bei leerer Handler-Liste: kein Absturz, nichts passiert.
 *
 *   close():
 *     Postcondition — close() wurde auf jedem enthaltenen Handler aufgerufen.
 *                     Bei leerer Handler-Liste: kein Absturz, nichts passiert.
 *
 * Hinweis Komposition vs. Vererbung:
 *   MultiHandler verwendet Komposition — er HAT eine Liste von Handlern.
 *   Er ERBT nicht von einem konkreten Handler.
 *   Warum? Weil MultiHandler kein spezialisierter ConsolePrinter oder
 *   CsvWriter ist — er ist ein Koordinator. Komposition ist hier die
 *   richtige Wahl. (Vererbung: spätere Session.)
 */
public class MultiHandler implements SensorDataHandler {

    private List<SensorDataHandler> handlers = new ArrayList<>();

    /**
     * Fügt einen Handler zur Liste hinzu.
     * Alle zukünftigen handle()- und close()-Aufrufe werden an ihn delegiert.
     */
    public void add(SensorDataHandler handler) {
        handlers.add(handler);
    }

    /**
     * Delegiert handle() an alle enthaltenen Handler.
     * Reihenfolge: in der Reihenfolge in der Handler hinzugefügt wurden.
     */
    @Override
    public void handle(SensorReading reading) {
        for (SensorDataHandler h : handlers) {
            h.handle(reading);
        }
    }

    /**
     * Delegiert close() an alle enthaltenen Handler.
     * Jeder Handler ist dafür verantwortlich seinen eigenen Zustand zu schließen.
     */
    @Override
    public void close() {
        for (SensorDataHandler h : handlers) {
            h.close();
        }
    }
}
