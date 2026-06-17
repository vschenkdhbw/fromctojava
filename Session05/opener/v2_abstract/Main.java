public class Main {
    public static void main(String[] args) {

        Dog d = new Dog("Bello", 3, "Labrador");
        Cat c = new Cat("Minka", 5, true);

        d.makeSound();
        c.makeSound();

        System.out.println();

        d.describe();
        System.out.println();
        c.describe();

        System.out.println();

        // Polymorphismus funktioniert genauso wie in Version 1
        Animal a1 = new Dog("Rex", 2, "Schäferhund");
        Animal a2 = new Cat("Whiskers", 4, false);

        a1.makeSound();
        a2.makeSound();

        System.out.println();

        // Version 2: diese Zeile uncommentieren → Compilerfehler
        // Animal generic = new Animal("???", 0);  // ← Compilerfehler: Animal is abstract
    }
}
