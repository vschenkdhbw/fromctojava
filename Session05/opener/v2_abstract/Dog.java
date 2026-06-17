/**
 * Dog — erbt von Animal.
 * Überschreibt makeSound() mit hundespezifischem Verhalten.
 * describe() kommt kostenlos von Animal — keine Änderung nötig.
 */
public class Dog extends Animal {

    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);          // Pflicht: Basisklassen-Konstruktor zuerst
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Woof! Ich bin " + getName() + ".");
    }

    @Override
    public void describe() {
        super.describe();          // Basisklassen-describe() aufrufen
        System.out.println("  Rasse: " + breed);
    }
}
