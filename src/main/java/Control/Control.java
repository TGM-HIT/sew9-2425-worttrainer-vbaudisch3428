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

public class Control implements ActionListener {
    private WortTrainer wortTrainer;
    private Panel panel;
    private static final String FILE_PATH = "/Users/vbaudisch/Library/CloudStorage/GoogleDrive-vincent.baudisch@gmail.com/Meine Ablage/SEW/5BHIT/Worttrainer/src/main/java/Control/Worttrainer.txt";

    public Control() {
        try {
            WortListe wortListe = new WortListe();
            // Initialisiere WortListe mit Daten aus der Datei
            wortTrainer = new WortTrainer(wortListe);
            // Lade initialen WortTrainer
            wortTrainer = WortTrainerPersistence.laden(FILE_PATH);
            // Erzeuge das Panel und zeige es an
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Enter".equals(command)) {
            handleGuess();
        } else if ("Zurücksetzen!".equals(command)) {
            handleReset();
        } else if ("Wort Hinzufügen!".equals(command)) {
            handleAddWord();
        } else if ("Laden".equals(command)) {
            handleLoad();
        } else if ("Speichern".equals(command)) {
            handleSave();
        }
    }

    private void handleGuess() {
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

    private void handleReset() {
        wortTrainer.setRichtigeVersuche(0);
        wortTrainer.setGesamtVersuche(0);
        panel.updateRichtigeWoerter(wortTrainer.getRichtigeVersuche(), wortTrainer.getGesamtVersuche());
    }

    private void handleAddWord() {
        String wort = JOptionPane.showInputDialog("Geben Sie das Wort ein:");
        String url = JOptionPane.showInputDialog("Geben Sie die Bild-URL ein:");
        try {
            wortTrainer.getWortListe().addWort(wort, url);
            JOptionPane.showMessageDialog(null, "Wort hinzugefügt!");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Fehler: " + ex.getMessage());
        }
    }

    private void handleLoad() {
        try {
            wortTrainer = WortTrainerPersistence.laden(FILE_PATH);
            panel.refresh(wortTrainer.getZufälligenEintrag().getUrl(), wortTrainer.getRichtigeVersuche(), wortTrainer.getGesamtVersuche());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden: " + e.getMessage());
        }
    }

    private void handleSave() {
        try {
            WortTrainerPersistence.speichern(wortTrainer, FILE_PATH);
            JOptionPane.showMessageDialog(null, "Erfolgreich gespeichert!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern: " + e.getMessage());
        }
    }
}
