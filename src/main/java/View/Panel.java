package View;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import Control.Control;

public class Panel extends JPanel {
    private JLabel frage;
    private JTextField eingabe;
    private JLabel bild;
    private JLabel richtiegeWoerter, anzahlWoerter;
    private JButton zuruecksetzen, wortHinzufuegen, laden, speichern;
    private Control control;

    public Panel(String bildPfad, Control control, int richtig, int durchgefuehrt) throws MalformedURLException {
        this.control = control;
        this.setLayout(new BorderLayout());

        JPanel layout1 = new JPanel();
        layout1.setLayout(new BoxLayout(layout1, BoxLayout.PAGE_AXIS));

        frage = new JLabel("Welches Bild wird unten dargestellt");
        eingabe = new JTextField();
        eingabe.setActionCommand("Enter");
        eingabe.addActionListener(control);

        layout1.add(frage);
        layout1.add(eingabe);

        bild = new JLabel(new ImageIcon(this.setBild(bildPfad))); // Anzeigen in einem JLabel

        JPanel layout2 = new JPanel();
        layout2.setLayout(new GridLayout(2, 4));

        richtiegeWoerter = new JLabel(Integer.toString(richtig));
        anzahlWoerter = new JLabel(Integer.toString(durchgefuehrt));
        // Zurücksetzen Button
        zuruecksetzen = new JButton("Zurücksetzen!");
        zuruecksetzen.addActionListener(control);
        // Wort hinzufügen Button
        wortHinzufuegen = new JButton("Wort Hinzufügen!");
        wortHinzufuegen.addActionListener(control);
        // Laden Button
        laden = new JButton("Laden");
        laden.addActionListener(control);
        // Speichern Button
        speichern = new JButton("Speichern");
        speichern.addActionListener(control);
        // Buttons und Label zum unteren Layout hinzufügen
        layout2.add(new JLabel("Richtige Wörter"));
        layout2.add(richtiegeWoerter);
        layout2.add(zuruecksetzen);
        layout2.add(laden);
        layout2.add(new JLabel("Anzahl Wörter"));
        layout2.add(anzahlWoerter);
        layout2.add(wortHinzufuegen);
        layout2.add(speichern);
        // Alle Layouts positionieren
        this.add(layout1, BorderLayout.NORTH);
        this.add(bild, BorderLayout.CENTER);
        this.add(layout2, BorderLayout.SOUTH);
    }

    // Methode für den Text der Eingabe
    public String getText() {
        return eingabe.getText();
    }

    // Methode zum Setzen des Textes der Eingabe
    public void setText(String text) {
        eingabe.setText(text);
    }

    // Falls richtig wird das Label durchgefuehrt und Richtige Wörter erhöht
    public void updateRichtigeWoerter(int richtig, int durchgefuehrt) {
        richtiegeWoerter.setText("" + richtig);
        anzahlWoerter.setText("" + durchgefuehrt);
    }

    // Bei falscher Eingabe wird die Anzahl durchgefuehrt erhöht
    public void updateFalscheWoerter(int richtig, int durchgefuehrt) {
        updateRichtigeWoerter(richtig, durchgefuehrt);
    }

    public void refresh(String sourcePath, int richtig, int durchgefuehrt) throws MalformedURLException {
        this.bild.setIcon(new ImageIcon(this.setBild(sourcePath)));
        this.updateRichtigeWoerter(richtig, durchgefuehrt);
        this.setText("");
    }

    public Image setBild(String bildPfad) throws MalformedURLException {
        ImageIcon icon = new ImageIcon(new URL(bildPfad));
        Image image = icon.getImage(); // Umwandeln in ein Image-Objekt
        return image.getScaledInstance(600, 350, Image.SCALE_SMOOTH); // Skalieren auf gewünschte Größe
    }
}
