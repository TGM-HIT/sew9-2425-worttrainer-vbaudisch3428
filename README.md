# Rechtschreibtrainer für Kinder

## Beschreibung

Der Rechtschreibtrainer für Kinder ist eine Java-Anwendung, die darauf abzielt, die Rechtschreibfähigkeiten von Grundschulkindern zu verbessern. Die Anwendung zeigt ein Bild an und fordert die Kinder auf, das passende Wort einzugeben. Die eingegebenen Wörter werden mit den korrekten Wörtern verglichen, und das Programm gibt Feedback zur Eingabe. Die Anwendung verfolgt die Anzahl der richtigen und falschen Versuche und speichert den Fortschritt.

## Funktionen

- **Bild-Wort-Paare:** Die Anwendung enthält Paare von Bildern und zugehörigen Wörtern, die für das Training verwendet werden.
- **Eingabeüberprüfung:** Die Eingaben der Kinder werden mit dem richtigen Wort verglichen, und es wird Feedback zur Richtigkeit der Eingabe gegeben.
- **Statistik:** Die Anwendung verfolgt, wie oft insgesamt, richtig und falsch geraten wurde.
- **Persistenz:** Der Zustand des Trainers, einschließlich der Wort-Bild-Paare, der aktuellen Statistik und des ausgewählten Paars, wird gespeichert und kann beim nächsten Start wiederhergestellt werden.
- **Grafische Oberfläche:** Die Benutzeroberfläche wird mit `JOptionPane` realisiert, um eine einfache und interaktive Nutzung zu ermöglichen.

## Technologien

- **Java:** Die Hauptprogrammiersprache für die Implementierung.
- **JOptionPane:** Für die grafische Benutzeroberfläche und Benutzerinteraktionen.
- **Persistenz:** Die Speicherung des Zustands kann durch verschiedene Formate wie JSON, XML oder Java-Serialisierung erfolgen. (Die genaue Implementierung ist je nach Wahl des Formats unterschiedlich.)

## Hinweise für Anwender

- Beim Programmstart wird überprüft, ob bereits gespeicherte Daten vorhanden sind. Falls nicht, wird der Trainer mit einigen vorab definierten Wort-Bild-Paaren initialisiert.
- Der Trainer zeigt das aktuelle Bild und die Statistik an und ermöglicht die Eingabe des Worts.
- Bei der Eingabe kann der Benutzer entweder das Bild-Wort-Paar bestätigen oder einen neuen Versuch starten.
- Die Anwendung speichert regelmäßig den aktuellen Zustand, um den Fortschritt zu erhalten.

## Nutzung

1. Starten Sie die Anwendung.
2. Geben Sie das Wort zum angezeigten Bild ein.
3. Überprüfen Sie das Feedback und die Statistiken.
4. Wiederholen Sie den Vorgang oder schließen Sie das Programm.

Viel Spaß beim Lernen und Trainieren!
