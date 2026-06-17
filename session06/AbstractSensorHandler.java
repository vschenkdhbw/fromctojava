/**
 * AbstractSensorHandler — Session 05
 *
 * Template Method Pattern:
 *   handle() steuert den Ablauf: null-Check → Zähler → process()
 *   process() ist die Hook-Methode — Unterklassen implementieren sie.
 *
 * handle() ist final: Unterklassen dürfen den Ablauf nicht umgehen.
 * Nur process() und onNull() sind überschreibbar.
 */
public abstract class AbstractSensorHandler implements SensorDataHandler {

    private int handledCount = 0;
    private int skippedCount = 0;

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
     */
    protected void onNull() {
        System.err.println(getClass().getSimpleName() + ": null-Reading ignoriert");
    }

    /**
     * Gibt Zähler-Zusammenfassung aus.
     * Unterklassen rufen super.close() auf um diese Zeile zu erhalten.
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
