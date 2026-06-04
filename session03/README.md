# Session 03 — Polymorphismus & Collections

## Lernziele

- Polymorphismus verstehen: warum eine Methode eine Abstraktion kennen sollte, nicht eine konkrete Klasse
- Den C-Stil-Schaden benennen: was wächst, was bleibt unberührt, wer hat wie viele Gründe sich zu ändern
- Interfaces in drei verschiedenen Domänen anwenden (Sensor-Ausgabe, Notifications, Payments)
- `List<T>` mit verschiedenen Typparametern anlegen und befüllen
- Expliziten Iterator schreiben: `iterator()`, `hasNext()`, `next()`
- For-each als Iterator-Kurzform erkennen und anwenden
- `List<Describable>` mit gemischten Objekten iterieren

## Dateien

| Datei | Herkunft | Zweck |
|---|---|---|
| `Describable.java` | Session 02 | Interface: `describe()` |
| `SensorReading.java` | Session 02 | Gekapselte Sensordaten, implementiert `Describable` |
| `Station.java` | Session 02 | Stationsmodell, implementiert `Describable` |
| `SensorDataHandler.java` | Session 02 | Interface: `handle()` + `close()` |
| `ConsolePrinter.java` | Session 02 | Implementiert `SensorDataHandler` |
| `InMemoryStore.java` | Session 02 | Implementiert `SensorDataHandler` |
| `Order.java` | Session 03 neu | Datenmodell für Übung 3 (Payment) |
| `PaymentProcessor.java` | Session 03 neu | Interface für Übung 3 — Studierende schreiben dies selbst |
| `CreditCardProcessor.java` | Session 03 neu | Implementierung für Übung 3 — Musterlösung |
| `Sensor.java` | Session 03 neu | Neue `Describable`-Klasse für Collections C.5 |
| `Main.java` | Session 03 neu | Musterlösung: alle Konzepte demonstriert |

## Kompilieren und ausführen

```bash
cd session03/src
javac *.java
java Main
```

## Erwartete Ausgabe

```
═══ SensorDataHandler ═══
S1 | seq=1 | 19.3°C | 64.2%
S1 | seq=2 | 22.1°C | 61.0%
S1 | seq=3 | -273.15°C | 58.0%
── ConsolePrinter: 3 Readings ausgegeben ──

── InMemoryStore: 3 Readings gespeichert ──
   Min-Temperatur: -273.15°C
   Max-Temperatur: 22.1°C

═══ PaymentProcessor ═══
Kreditkarte: 49.99 EUR abgebucht für Sensorkabel (Order ORD-001)
Kreditkarte: 129.0 EUR abgebucht für Arduino Mega (Order ORD-002)
Kreditkarte: 9.95 EUR abgebucht für Breadboard (Order ORD-003)

═══ Collections: Typparameter ═══
Zahlen: 5 Einträge
Drittes Element: Basel

═══ Expliziter Iterator ═══
  10
  20
  30
  40
  50
Summe: 150

═══ For-Each ═══
Summe (for-each): 150

═══ List<Describable> ═══
S1 | seq=1 | 19.3°C | 64.2%
Station Nord in Freiburg
S2 | seq=2 | 22.1°C | 61.0%
Station Süd in Basel
Sensor TEMP-01 misst in °C
Sensor HUM-01 misst in %rH
```

## Der Vertrag

> Ein Interface ist ein Vertrag — keine Implementierung, keine Felder, nur Signaturen.
> Völlig unterschiedliche Klassen können denselben Vertrag unterschreiben.
>
> In C: eine Funktion kennt den Struct, greift auf seine Felder zu, und muss angepasst werden wenn sich der Struct ändert.
> In Java: das Objekt kennt sich selbst — der Aufrufer weiß nur, was das Objekt tun kann, nicht wie.
