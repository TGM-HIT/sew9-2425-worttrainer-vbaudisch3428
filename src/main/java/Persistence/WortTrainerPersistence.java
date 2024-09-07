package Persistence;

import Model.WortTrainer;
import Model.WortListe;
import Model.WortEintrag;

import java.io.*;

public class WortTrainerPersistence {

    // Methode zum Speichern des WortTrainer in einer Textdatei
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

    // Methode zum Laden des WortTrainer aus einer Textdatei
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
