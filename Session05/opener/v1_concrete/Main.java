public class Main {
    public static void main(String[] args) {

        Dog d = new Dog("Bello", 3, "Labrador");
        Cat c = new Cat("Minka", 5, true);

        // makeSound() — virtuelle Methode: tatsächlicher Typ entscheidet
        d.makeSound();
        c.makeSound();

        System.out.println();

        // describe() — Basisklasse + Unterklasse via super.describe()
        d.describe();
        System.out.println();
        c.describe();

        System.out.println();

        // Polymorphismus: beide in einer Animal-Variable
        Animal a1 = new Dog("Rex", 2, "Schäferhund");
        Animal a2 = new Cat("Whiskers", 4, false);

        a1.makeSound();   // welche makeSound() wird aufgerufen?
        a2.makeSound();

        System.out.println();

        // Version 1: das ist erlaubt — aber macht es Sinn?
        Animal generic = new Animal("???", 0);
        generic.makeSound();   // "??? macht ein Geräusch." — sinnlos
        generic.describe();
    }
}
