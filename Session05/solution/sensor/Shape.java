/**
 * Shape — abstrakte Basisklasse (Teil 2 + 3)
 *
 * Version nach Teil 3: describe() ist in die Basisklasse gezogen worden.
 * Das ist das Template Method Pattern:
 *   describe() = Template-Methode (steuert Ausgabe, ruft area() auf)
 *   area()     = Hook-Methode     (Unterklassen liefern die Berechnung)
 */
public abstract class Shape {

    private String color;

    public Shape(String color) {
        this.color = color;
    }

    // Hook-Methode: jede Unterklasse muss die Fläche berechnen
    public abstract double area();

    // Template-Methode: Ablauf fest, Inhalt (area()) variabel
    // Unterklassen müssen describe() NICHT überschreiben —
    // aber sie könnten es via super.describe() erweitern
    public void describe() {
        System.out.printf("Farbe: %s | Fläche: %.2f%n", color, area());
    }

    public String getColor() { return color; }
}
