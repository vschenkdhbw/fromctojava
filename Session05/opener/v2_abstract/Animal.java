/**
 * Animal — abstrakte Basisklasse (Version 2)
 *
 * Einzige Änderung gegenüber Version 1: das Schlüsselwort "abstract".
 *
 * Zwei Effekte:
 *   1. new Animal(...) ist jetzt ein Compilerfehler — ein "Tier ohne Art"
 *      kann nicht mehr instanziiert werden.
 *   2. makeSound() kann abstract werden — der Compiler zwingt jede
 *      Unterklasse zur Implementierung.
 *
 * Dog.java und Cat.java sind identisch zu Version 1 — kein einziges
 * Zeichen ändert sich. Das ist der Punkt.
 */
public abstract class Animal {

    private String name;
    private int    age;

    public Animal(String name, int age) {
        this.name = name;
        this.age  = age;
    }

    // abstract: kein Körper, Unterklassen müssen implementieren
    public abstract void makeSound();

    public void describe() {
        System.out.println("Tier: " + name + ", " + age + " Jahre alt.");
    }

    public String getName() { return name; }
    public int    getAge()  { return age;  }
}
