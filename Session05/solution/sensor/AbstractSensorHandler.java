/**
 * AbstractSensorHandler — abstrakte Basisklasse für SensorDataHandler
 *
 * Neu in Session 05.
 *
 * Template Method Pattern:
 *   handle() ist die Template-Methode — sie steuert den Ablauf:
 *     null-Check → Zähler → Delegation an process()
 *   process() ist die Hook-Methode — Unterklassen implementieren sie.
 *
 * handle() ist final: Unterklassen dürfen den Ablauf nicht umgehen.
 * Nur process() und onNull() sind überschreibbar.
 *
 * Unterklassen die von AbstractSensorHandler erben müssen NICHT
 * mehr implements SensorDataHandler schreiben — das ist bereits hier
 * deklariert.
 *
 * Design by Contract:
 *   process(): Precondition — reading ist nicht null (garantiert durch handle())
 *   close():   Postcondition — Zähler-Zusammenfassung wurde ausgegeben
 */
public abstract class AbstractSensorHandler implements SensorDataHandler {

    private int handledCount = 0;
    private int skippedCount = 0;

    /**
     * Template-Methode: steuert den Ablauf, darf nicht überschrieben werden.
     * Unterklassen implementieren process() — nicht handle().
     */
    @Override
    public final void handle(SensorReading reading) {
        if (reading == null) {
            skippedCount++;
            onNull();
            return;
        }
        handledCount++;
        process(reading);
    }

    /**
     * Hook-Methode: Unterklassen implementieren hier ihre Logik.
     * Precondition: reading ist garantiert nicht null.
     */
    protected abstract void process(SensorReading reading);

    /**
     * Wird aufgerufen wenn handle(null) empfangen wird.
     * Unterklassen können überschreiben — müssen aber nicht.
     * Standard: Fehlermeldung auf System.err.
     */
    protected void onNull() {
        System.err.println(getClass().getSimpleName()
            + ": null-Reading ignoriert");
    }

    /**
     * Gibt Zähler-Zusammenfassung aus.
     * Unterklassen können mit super.close() erweitern.
     */
    @Override
    public void close() {
        System.out.println("── " + getClass().getSimpleName()
            + ": " + handledCount + " verarbeitet, "
            + skippedCount + " übersprungen ──");
    }

    public int getHandledCount() { return handledCount; }
    public int getSkippedCount() { return skippedCount; }
}
