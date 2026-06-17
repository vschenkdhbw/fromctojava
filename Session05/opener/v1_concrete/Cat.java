/**
 * Cat — erbt von Animal.
 * Überschreibt makeSound() mit katzspezifischem Verhalten.
 * describe() kommt kostenlos von Animal — keine Änderung nötig.
 */
public class Cat extends Animal {

    private boolean isIndoor;

    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);          // Pflicht: Basisklassen-Konstruktor zuerst
        this.isIndoor = isIndoor;
    }

    @Override
    public void makeSound() {
        System.out.println("Miau! Ich bin " + getName() + ".");
    }

    @Override
    public void describe() {
        super.describe();          // Basisklassen-describe() aufrufen
        System.out.println("  " + (isIndoor ? "Wohnungskatze" : "Freigänger"));
    }
}
