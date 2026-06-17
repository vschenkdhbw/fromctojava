/**
 * ExceptionDemo — Session 06, Teil 2.1
 *
 * Zweck: Die Kontrollstruktur von try/catch/finally isoliert zeigen —
 * ohne Dateisystem, ohne Imports, ohne Ablenkung.
 *
 * ArrayIndexOutOfBoundsException ist unchecked (extends RuntimeException),
 * daher kein throws in der Signatur nötig. Der Compiler schweigt —
 * aber der Fehler fliegt trotzdem zur Laufzeit.
 *
 * Aufgabe: Vorhersagen was ausgegeben wird, bevor ihr ausführt.
 * Dann: zahlen[5] auf zahlen[1] ändern — was ändert sich?
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        System.out.println("vor try");

        try {
            int[] zahlen = new int[3];
            zahlen[5] = 42;                              // Index 5 existiert nicht — wirft immer
            System.out.println("nach dem Fehler");       // wird nie erreicht wenn Exception fliegt
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("gefangen: " + e.getMessage());
        } finally {
            System.out.println("finally läuft immer");   // egal ob Exception oder nicht
        }

        System.out.println("nach try-catch-finally");
    }
}
