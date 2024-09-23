package Control;

import Model.WortListe;
import Model.WortTrainer;
import View.Panel;
import Persistence.WortTrainerPersistence;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Die Klasse Control fungiert als Controller in einem MVC-Architekturmodell und verarbeitet die Benutzeraktionen,
 * die von der Benutzeroberfläche (Panel) initiiert werden.
 * Sie steuert die Logik des WortTrainer, einschließlich der Überprüfung von Benutzereingaben,
 * der Verwaltung der richtigen und gesamt durchgeführten Versuche sowie dem Hinzufügen neuer Wörter.
 * Zusätzlich ermöglicht die Klasse das Speichern und Laden der Daten des WortTrainers
 * in eine externe Datei über die Persistenzschicht.
 *
 * @see Model.WortTrainer
 * @see View.Panel
 * @see Persistence.WortTrainerPersistence
 */

public class Control implements ActionListener {
    private WortTrainer wortTrainer;
    private Panel panel;
    private static final String FILE_PATH = "/Users/vbaudisch/Library/CloudStorage/GoogleDrive-vincent.baudisch@gmail.com/Meine Ablage/SEW/5BHIT/Worttrainer/src/main/java/Control/Worttrainer.txt";

    public static String getFilePath() {
        return FILE_PATH;
    }

    public Control() {
        try {
            WortListe wortListe = new WortListe();
            wortTrainer = new WortTrainer(wortListe);
            wortTrainer = WortTrainerPersistence.laden(FILE_PATH);
            panel = new Panel(wortTrainer.getZufälligenEintrag().getUrl(), this, wortTrainer.getRichtigeVersuche(), wortTrainer.getGesamtVersuche());
            JFrame frame = new JFrame("Wort Trainer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden des Bildes: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden des WortTrainers: " + e.getMessage());
        }
    }

    public WortTrainer getWortTrainer() {
        return wortTrainer;
    }

    public Panel getPanel() {
        return panel;
    }


    /**
     * Verarbeitet die Aktionen der Benutzeroberfläche.
     * @param e ActionEvent, das die Benutzeraktion beschreibt.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Enter":
                handleGuess();
                break;
            case "Zurücksetzen!":
                handleReset();
                break;
            case "Wort Hinzufügen!":
                handleAddWord();
                break;
            case "Laden":
                handleLoad();
                break;
            case "Speichern":
                handleSave();
                break;
        }
    }

    /**
     * Verarbeitet den Rateversuch des Benutzers.
     * Prüft, ob das eingegebene Wort korrekt ist, und aktualisiert das Panel.
     */
    public void handleGuess() {
        String eingabe = panel.getText();
        if (wortTrainer.checkIgnoreCase(eingabe)) {
            JOptionPane.showMessageDialog(null, "Richtig!");
        } else {
            JOptionPane.showMessageDialog(null, "Falsch!");
        }
        try {
            panel.refresh(wortTrainer.getZufälligenEintrag().getUrl(), wortTrainer.getRichtigeVersuche(), wortTrainer.getGesamtVersuche());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Setzt die Statistiken (richtige und Gesamtversuche) des WortTrainers zurück.
     */
    public void handleReset() {
        wortTrainer.setRichtigeVersuche(0);
        wortTrainer.setGesamtVersuche(0);
        panel.updateRichtigeWoerter(wortTrainer.getRichtigeVersuche(), wortTrainer.getGesamtVersuche());
    }

    /**
     * Fügt ein neues Wort mit zugehöriger Bild-URL zur WortListe des WortTrainers hinzu.
     */
    public void handleAddWord() {
        String wort = JOptionPane.showInputDialog("Geben Sie das Wort ein:");
        String url = JOptionPane.showInputDialog("Geben Sie die Bild-URL ein:");
        try {
            wortTrainer.getWortListe().addWort(wort, url);
            JOptionPane.showMessageDialog(null, "Wort hinzugefügt!");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Fehler: " + ex.getMessage());
        }
    }

    /**
     * Lädt den gespeicherten Zustand des WortTrainers aus einer Datei.
     */
    private void handleLoad() {
        try {
            wortTrainer = WortTrainerPersistence.laden(FILE_PATH);
            panel.refresh(wortTrainer.getZufälligenEintrag().getUrl(), wortTrainer.getRichtigeVersuche(), wortTrainer.getGesamtVersuche());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden: " + e.getMessage());
        }
    }

    /**
     * Speichert den aktuellen Zustand des WortTrainers in eine Datei.
     */
    private void handleSave() {
        try {
            WortTrainerPersistence.speichern(wortTrainer, FILE_PATH);
            JOptionPane.showMessageDialog(null, "Erfolgreich gespeichert!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern: " + e.getMessage());
        }
    }
}
