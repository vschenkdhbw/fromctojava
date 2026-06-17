import java.io.*;
import 

/**
 * CsvWriterBroken — Session 06, Opener
 *
 * Zweck: Das "stille Versagen" demonstrieren.
 * Ein leerer catch-Block ist die gefährlichste Zeile Code die ihr schreiben könnt:
 * das Programm wirkt korrekt — und ist es nicht.
 *
 * Experiment 1: Normaler Pfad (/tmp/broken.csv).
 *   Was steht in der Datei? Was auf der Konsole?
 *
 * Experiment 2: Pfad auf nicht-existierenden Ordner ändern:
 *   new FileWriter("/nicht/vorhanden/broken.csv")
 *   Was steht jetzt auf der Konsole? Was in der Datei?
 *
 * Vergleich mit C:
 *   FILE* f = fopen("/nicht/vorhanden/broken.csv", "w");
 *   // Rückgabewert ignoriert — kein Compiler-Einwand
 *   fprintf(f, "1,S1,19.3\n");
 *   Ergebnis: identisches stilles Versagen.
 *
 * Der Unterschied: Java macht es euch schwerer zu ignorieren (checked Exception).
 * Aber ein leerer catch-Block umgeht das vollständig.
 */
public class CsvWriterBroken {

    public static void main(String[] args) {
        try {
            Writer fw = new FileWriter("/bingbong/tmp/broken.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("1,S1,19.3,64.2");
            bw.newLine();
            bw.write("2,S1,22.1,61.0");
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            // Fehler ignorieren  ← das ist das Problem
            println("Fehler: " + e.getMessage());  // das wäre die bessere Alternative
        }

        System.out.println("Fertig.");   // erscheint immer — auch wenn nichts geschrieben wurde
    }
}
