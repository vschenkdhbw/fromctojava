import java.io.*;

/**
 * DateiDemo — Session 06, Teil 2.2
 *
 * Zweck: Dieselbe try/catch/finally-Struktur wie ExceptionDemo —
 * aber jetzt mit einer checked Exception (IOException).
 *
 * IOException ist checked (extends Exception, nicht RuntimeException).
 * Der Compiler zwingt euch zu einer Entscheidung:
 *   - catches IOException hier selbst, ODER
 *   - deklariert throws IOException in der Signatur
 *
 * Experiment 1: Programm mit gültigem Pfad ausführen.
 * Experiment 2: Pfad auf nicht-existierenden Ordner ändern.
 *   → Was ändert sich an der Ausgabe? Läuft finally trotzdem?
 */
public class DateiDemo {

    public static void main(String[] args) {
        System.out.println("vor try");

        try {
            Writer fw = new FileWriter("/nixda/tmp/demo.txt");
            fw.write("Hallo");
            fw.close();
            System.out.println("Schreiben OK");
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        } finally {
            System.out.println("finally läuft immer");
        }

        System.out.println("nach try-catch-finally");
    }
}
