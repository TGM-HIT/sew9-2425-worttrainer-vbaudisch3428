package Model;

import java.util.List;
import java.util.Random;

public class WortTrainer {
    private WortListe wortListe;
    private WortEintrag aktuellerEintrag;
    private int richtigeVersuche;
    private int gesamtVersuche;

    public WortTrainer(WortListe wortListe) {
        this.wortListe = wortListe;
        this.richtigeVersuche = 0;
        this.gesamtVersuche = 0;
    }

    public WortListe getWortListe() {
        return wortListe;
    }

    public WortEintrag getAktuellenEintrag() {
        return aktuellerEintrag;
    }

    public WortEintrag getZufälligenEintrag() {
        Random random = new Random();
        List<WortEintrag> einträge = wortListe.getWortEinträge();
        if (einträge.isEmpty()) return null;
        aktuellerEintrag = einträge.get(random.nextInt(einträge.size()));
        return aktuellerEintrag;
    }

    public boolean checkIgnoreCase(String eingabe) {
        if (aktuellerEintrag == null) return false;
        boolean korrekt = aktuellerEintrag.getWort().equalsIgnoreCase(eingabe);
        if (korrekt) {
            richtigeVersuche++;
        }
        gesamtVersuche++;
        return korrekt;
    }

    public int getRichtigeVersuche() {
        return richtigeVersuche;
    }

    public void setRichtigeVersuche(int richtigeVersuche) {
        this.richtigeVersuche = richtigeVersuche;
    }

    public int getGesamtVersuche() {
        return gesamtVersuche;
    }

    public void setGesamtVersuche(int gesamtVersuche) {
        this.gesamtVersuche = gesamtVersuche;
    }
}
