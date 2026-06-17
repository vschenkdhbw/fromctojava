/**
 * Sensor
 *
 * Repräsentiert einen physischen Sensor mit ID und Messeinheit.
 * Implementiert Describable — kann in eine List<Describable>
 * zusammen mit SensorReading und Station aufgenommen werden.
 *
 * Musterlösung für Collections-Aufgabe 5.3:
 * Studierende schreiben diese Klasse selbst und fügen sie
 * zur List<Describable> hinzu, ohne die for-each-Schleife zu ändern.
 */
public class Sensor implements Describable {

    private String id;
    private String unit;

    public Sensor(String id, String unit) {
        this.id   = id != null ? id : "UNKNOWN";
        this.unit = unit != null ? unit : "";
    }

    public String getId()   { return id; }
    public String getUnit() { return unit; }

    @Override
    public String describe() {
        return "Sensor " + id + " misst in " + unit;
    }
}
