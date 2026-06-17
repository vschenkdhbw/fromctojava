/**
 * Animal — konkrete Basisklasse (Version 1)
 *
 * Enthält das gemeinsame Konzept: Name, Alter, describe().
 * makeSound() ist hier generisch — Unterklassen überschreiben es.
 *
 * Problem mit dieser Version: new Animal("Rex", 3) ist erlaubt.
 * Ein "Tier ohne Art" macht konzeptuell keinen Sinn.
 */
public class Animal {

    private String name;
    private int    age;

    public Animal(String name, int age) {
        this.name = name;
        this.age  = age;
    }

    public void makeSound() {
        System.out.println(name + " macht ein Geräusch.");
    }

    public void describe() {
        System.out.println("Tier: " + name + ", " + age + " Jahre alt.");
    }

    public String getName() { return name; }
    public int    getAge()  { return age;  }
}
