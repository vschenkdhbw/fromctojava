import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

/**
 * FileTest
 *
 * Isoliertes Experiment für Session 04, Opener O.3 und Teil 1.
 * Nicht Teil des Sensor-Projekts — nur zum Verstehen von File I/O.
 *
 * Führt drei Varianten aus und zeigt den Unterschied:
 *   1. FileWriter ohne close()  → Datei leer (Puffer nicht geleert)
 *   2. FileWriter mit close()   → Datei gefüllt
 *   3. BufferedWriter           → Datei gefüllt, plattformunabhängige Zeilenumbrüche
 */
public class FileTest {

    public static void main(String[] args) throws Exception {

        // ── Experiment 1: FileWriter OHNE close() ─────────────────────────
        // Opener O.3: was erwartet ihr in der Datei?
        // Ergebnis: die Datei ist leer — der Puffer wurde nie geleert.
        //
        // Writer fw1 = new FileWriter("experiment1_ohne_close.txt");
        // fw1.write("Zeile 1\n");
        // fw1.write("Zeile 2\n");
        // fw1.write("Zeile 3\n");
        // // close() absichtlich weggelassen
        // System.out.println("Experiment 1 abgeschlossen — öffnet experiment1_ohne_close.txt");

        // ── Experiment 2: FileWriter MIT close() ──────────────────────────
        // Teil 1.1: close() hinzugefügt — jetzt sind die Daten da.
        //
        System.out.println("── Experiment 2: FileWriter mit close() ──");
        Writer fw2 = new FileWriter("experiment2_mit_close.txt");
        fw2.write("Zeile 1\n");
        fw2.write("Zeile 2\n");
        fw2.write("Zeile 3\n");
        fw2.close();
        System.out.println("Fertig. Öffnet experiment2_mit_close.txt");

        // ── Experiment 3: BufferedWriter ──────────────────────────────────
        // Teil 1.2: BufferedWriter als Wrapper.
        // Vorteil: newLine() ist plattformunabhängig (\r\n auf Windows, \n auf Unix).
        // close() macht: flush() + Datei schließen.
        //
        System.out.println("\n── Experiment 3: BufferedWriter ──");
        Writer fw3 = new FileWriter("experiment3_buffered.txt");
        BufferedWriter bw = new BufferedWriter(fw3);
        bw.write("Zeile 1");
        bw.newLine();
        bw.write("Zeile 2");
        bw.newLine();
        bw.write("Zeile 3");
        bw.newLine();
        bw.close();   // flush() + fw3.close() — bw.close() schließt auch fw3
        System.out.println("Fertig. Öffnet experiment3_buffered.txt");

        // ── Experiment 4: Überschreiben vs. Anhängen ─────────────────────
        // Teil 1.3: zweiter Parameter im FileWriter-Konstruktor.
        // false (Standard) = überschreiben  — wie fopen("f", "w") in C
        // true             = anhängen        — wie fopen("f", "a") in C
        //
        System.out.println("\n── Experiment 4: Anhängen ──");
        BufferedWriter append1 = new BufferedWriter(new FileWriter("experiment4_append.txt", false));
        append1.write("Erster Durchlauf");
        append1.newLine();
        append1.close();

        BufferedWriter append2 = new BufferedWriter(new FileWriter("experiment4_append.txt", true));
        append2.write("Zweiter Durchlauf — angehängt");
        append2.newLine();
        append2.close();
        System.out.println("Fertig. experiment4_append.txt enthält beide Zeilen.");
    }
}
