package View;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import Control.Control;

/**
 * Die Klasse Panel repräsentiert das Haupt-Panel der Benutzeroberfläche des WortTrainers.
 * Sie enthält das Layout und die Benutzerinteraktionen wie die Eingabe von Wörtern, Anzeigen von Bildern und die Buttons.
 */
public class Panel extends JPanel {
    private JLabel frage;
    private JTextField eingabe;
    private JLabel bild;
    private JLabel richtiegeWoerter, anzahlWoerter;
    private JButton zuruecksetzen, wortHinzufuegen, laden, speichern;
    private Control control;

    /**
     * Konstruktor für das {@link Panel}. Er initialisiert die Benutzeroberfläche mit den entsprechenden Komponenten
     * und verbindet sie mit dem Controller.
     *
     * @param bildPfad Der Pfad zum Bild, das im Panel angezeigt werden soll.
     * @param control  Die Instanz des Controllers, der für die Interaktionen zuständig ist.
     * @param richtig  Die Anzahl der richtigen Versuche, die angezeigt wird.
     * @param durchgefuehrt Die Gesamtanzahl der durchgeführten Versuche.
     * @throws MalformedURLException Wenn der Bildpfad ungültig ist.
     */
    public Panel(String bildPfad, Control control, int richtig, int durchgefuehrt) throws MalformedURLException {
        this.control = control;
        this.setLayout(new BorderLayout());

        // Layout für die Eingabefrage und das Textfeld
        JPanel layout1 = new JPanel();
        layout1.setLayout(new BoxLayout(layout1, BoxLayout.PAGE_AXIS));

        frage = new JLabel("Welches Bild wird unten dargestellt");
        eingabe = new JTextField();
        eingabe.setActionCommand("Enter");
        eingabe.addActionListener(control);

        layout1.add(frage);
        layout1.add(eingabe);

        // Bildanzeige in einem JLabel
        bild = new JLabel(new ImageIcon(this.setBild(bildPfad)));

        // Layout für die Buttons und Statistiken
        JPanel layout2 = new JPanel();
        layout2.setLayout(new GridLayout(2, 4));

        richtiegeWoerter = new JLabel(Integer.toString(richtig));
        anzahlWoerter = new JLabel(Integer.toString(durchgefuehrt));

        // Initialisieren der Buttons
        zuruecksetzen = new JButton("Zurücksetzen!");
        zuruecksetzen.addActionListener(control);

        wortHinzufuegen = new JButton("Wort Hinzufügen!");
        wortHinzufuegen.addActionListener(control);

        laden = new JButton("Laden");
        laden.addActionListener(control);

        speichern = new JButton("Speichern");
        speichern.addActionListener(control);

        // Hinzufügen der Komponenten zu den Layouts
        layout2.add(new JLabel("Richtige Wörter"));
        layout2.add(richtiegeWoerter);
        layout2.add(zuruecksetzen);
        layout2.add(laden);
        layout2.add(new JLabel("Anzahl Wörter"));
        layout2.add(anzahlWoerter);
        layout2.add(wortHinzufuegen);
        layout2.add(speichern);

        // Positionieren der Layouts im Hauptpanel
        this.add(layout1, BorderLayout.NORTH);
        this.add(bild, BorderLayout.CENTER);
        this.add(layout2, BorderLayout.SOUTH);
    }

    /**
     * Gibt den eingegebenen Text des Textfeldes zurück.
     *
     * @return Der Text aus dem Eingabefeld.
     */
    public String getText() {
        return eingabe.getText();
    }

    /**
     * Setzt den Text des Eingabefeldes auf den angegebenen Wert.
     *
     * @param text Der Text, der im Eingabefeld angezeigt werden soll.
     */
    public void setText(String text) {
        eingabe.setText(text);
    }

    /**
     * Aktualisiert die Anzeige der richtigen Wörter und der Gesamtanzahl der Versuche.
     *
     * @param richtig Die Anzahl der richtigen Versuche.
     * @param durchgefuehrt Die Gesamtanzahl der durchgeführten Versuche.
     */
    public void updateRichtigeWoerter(int richtig, int durchgefuehrt) {
        richtiegeWoerter.setText("" + richtig);
        anzahlWoerter.setText("" + durchgefuehrt);
    }

    /**
     * Aktualisiert die Anzeige bei falschen Eingaben. Die Gesamtanzahl der durchgeführten Versuche wird erhöht.
     *
     * @param richtig Die Anzahl der richtigen Versuche.
     * @param durchgefuehrt Die Gesamtanzahl der durchgeführten Versuche.
     */
    public void updateFalscheWoerter(int richtig, int durchgefuehrt) {
        updateRichtigeWoerter(richtig, durchgefuehrt);
    }

    /**
     * Aktualisiert das Panel mit einem neuen Bild und der Statistik.
     *
     * @param sourcePath Der Pfad des neuen Bildes.
     * @param richtig Die Anzahl der richtigen Versuche.
     * @param durchgefuehrt Die Gesamtanzahl der durchgeführten Versuche.
     * @throws MalformedURLException Wenn der Bildpfad ungültig ist.
     */
    public void refresh(String sourcePath, int richtig, int durchgefuehrt) throws MalformedURLException {
        this.bild.setIcon(new ImageIcon(this.setBild(sourcePath)));
        this.updateRichtigeWoerter(richtig, durchgefuehrt);
        this.setText("");
    }

    /**
     * Lädt das Bild von der angegebenen URL und skaliert es auf die gewünschte Größe.
     *
     * @param bildPfad Der URL-Pfad des Bildes.
     * @return Das skalierte {@link Image}-Objekt.
     * @throws MalformedURLException Wenn der URL ungültig ist.
     */
    public Image setBild(String bildPfad) throws MalformedURLException {
        ImageIcon icon = new ImageIcon(new URL(bildPfad));
        Image image = icon.getImage();
        return image.getScaledInstance(600, 350, Image.SCALE_SMOOTH);
    }
}