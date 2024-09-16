package Model;

import java.util.List;
import java.util.Random;

/**
 * Die Klasse WortTrainer verwaltet die Logik zum Trainieren von Wörtern, inklusive Richtige und Gesamtversuche.
 */
public class WortTrainer {
    private WortListe wortListe;
    private WortEintrag aktuellerEintrag;
    private int richtigeVersuche;
    private int gesamtVersuche;

    /**
     * Konstruktor für die Klasse WortTrainer.
     * Initialisiert die Liste der WortEinträge und die Versuche.
     * @param wortListe Die Liste der Wörter, die zum Training verwendet wird.
     */
    public WortTrainer(WortListe wortListe) {
        this.wortListe = wortListe;
        this.richtigeVersuche = 0;
        this.gesamtVersuche = 0;
    }

    /**
     * Gibt die WortListe zurück, die zum Training verwendet wird.
     * @return Die WortListe des Trainers.
     */
    public WortListe getWortListe() {
        return wortListe;
    }

    /**
     * Gibt den aktuellen WortEintrag zurück, der gerade trainiert wird.
     * @return Der aktuelle WortEintrag.
     */
    public WortEintrag getAktuellenEintrag() {
        return aktuellerEintrag;
    }

    /**
     * Wählt einen zufälligen Eintrag aus der WortListe aus und gibt ihn zurück.
     * @return Der zufällig ausgewählte WortEintrag.
     */
    public WortEintrag getZufälligenEintrag() {
        Random random = new Random();
        List<WortEintrag> einträge = wortListe.getWortEinträge();
        if (einträge.isEmpty()) return null;
        aktuellerEintrag = einträge.get(random.nextInt(einträge.size()));
        return aktuellerEintrag;
    }

    /**
     * Überprüft, ob die eingegebene Antwort mit dem aktuellen Wort übereinstimmt (Groß-/Kleinschreibung wird ignoriert).
     * @param eingabe Die Benutzereingabe.
     * @return true, wenn die Eingabe korrekt ist, sonst false.
     */
    public boolean checkIgnoreCase(String eingabe) {
        if (aktuellerEintrag == null) return false;
        boolean korrekt = aktuellerEintrag.getWort().equalsIgnoreCase(eingabe);
        if (korrekt) {
            richtigeVersuche++;
        }
        gesamtVersuche++;
        return korrekt;
    }

    /**
     * Gibt die Anzahl der richtigen Versuche zurück.
     * @return Die Anzahl der richtigen Versuche.
     */
    public int getRichtigeVersuche() {
        return richtigeVersuche;
    }

    /**
     * Setzt die Anzahl der richtigen Versuche.
     * @param richtigeVersuche Die neue Anzahl der richtigen Versuche.
     */
    public void setRichtigeVersuche(int richtigeVersuche) {
        this.richtigeVersuche = richtigeVersuche;
    }

    /**
     * Gibt die Gesamtzahl der Versuche zurück.
     * @return Die Gesamtzahl der Versuche.
     */
    public int getGesamtVersuche() {
        return gesamtVersuche;
    }

    /**
     * Setzt die Gesamtzahl der Versuche.
     * @param gesamtVersuche Die neue Anzahl der Gesamtversuche.
     */
    public void setGesamtVersuche(int gesamtVersuche) {
        this.gesamtVersuche = gesamtVersuche;
    }
}
