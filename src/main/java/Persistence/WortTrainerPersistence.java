package Persistence;

import Model.WortTrainer;
import Model.WortListe;
import Model.WortEintrag;

import java.io.*;

/**
 * Die Klasse WortTrainerPersistence stellt Methoden zum Speichern und Laden eines {@link WortTrainer} in bzw. aus einer Datei bereit.
 */
public class WortTrainerPersistence {

    /**
     * Speichert den {@link WortTrainer} in einer Textdatei.
     * Die Methode speichert sowohl die Wort-Einträge als auch die Statistik (richtige und gesamt Versuche).
     *
     * @param wortTrainer Der zu speichernde WortTrainer.
     * @param fileName    Der Pfad zur Datei, in die gespeichert werden soll.
     * @throws IOException Wenn ein Fehler beim Schreiben in die Datei auftritt.
     */
    public static void speichern(WortTrainer wortTrainer, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Speichern der Wort-Einträge
            for (WortEintrag eintrag : wortTrainer.getWortListe().getWortEinträge()) {
                writer.write(eintrag.getWort() + "," + eintrag.getUrl());
                writer.newLine();
            }
            // Speichern der Statistik
            writer.write("RichtigeVersuche:" + wortTrainer.getRichtigeVersuche());
            writer.newLine();
            writer.write("GesamtVersuche:" + wortTrainer.getGesamtVersuche());
            writer.newLine();
        }
    }

    /**
     * Lädt einen {@link WortTrainer} aus einer Textdatei.
     * Die Methode liest die Wort-Einträge und die Statistik (richtige und gesamt Versuche) aus der Datei und stellt sie im WortTrainer wieder her.
     *
     * @param fileName Der Pfad zur Datei, aus der der WortTrainer geladen werden soll.
     * @return Der geladene WortTrainer mit allen Einträgen und der gespeicherten Statistik.
     * @throws IOException Wenn ein Fehler beim Lesen aus der Datei auftritt.
     */
    public static WortTrainer laden(String fileName) throws IOException {
        WortListe wortListe = new WortListe();
        int richtigeVersuche = 0;
        int gesamtVersuche = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("RichtigeVersuche:")) {
                    richtigeVersuche = Integer.parseInt(line.substring("RichtigeVersuche:".length()));
                } else if (line.startsWith("GesamtVersuche:")) {
                    gesamtVersuche = Integer.parseInt(line.substring("GesamtVersuche:".length()));
                } else {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String wort = parts[0];
                        String url = parts[1];
                        wortListe.addWort(wort, url);
                    }
                }
            }
        }

        WortTrainer wortTrainer = new WortTrainer(wortListe);
        wortTrainer.setRichtigeVersuche(richtigeVersuche);
        wortTrainer.setGesamtVersuche(gesamtVersuche);
        return wortTrainer;
    }
}
