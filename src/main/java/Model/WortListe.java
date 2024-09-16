package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse WortListe verwaltet eine Liste von WortEinträgen.
 */
public class WortListe {
    private List<WortEintrag> wortEinträge;

    /**
     * Konstruktor für die Klasse WortListe.
     * Initialisiert eine leere Liste von WortEinträgen.
     */
    public WortListe() {
        this.wortEinträge = new ArrayList<>();
    }

    /**
     * Fügt ein neues Wort mit einer URL zur Liste hinzu.
     * @param wort Das hinzuzufügende Wort.
     * @param url Die zugehörige Bild-URL.
     * @throws IllegalArgumentException Wenn das Wort oder die URL null ist.
     */
    public void addWort(String wort, String url) {
        if (wort == null || url == null) {
            throw new IllegalArgumentException("Wort und URL dürfen nicht null sein.");
        }
        WortEintrag neuerEintrag = new WortEintrag(wort, url);
        wortEinträge.add(neuerEintrag);
    }

    /**
     * Gibt den WortEintrag am angegebenen Index zurück.
     * @param index Der Index des gewünschten Eintrags.
     * @return Der WortEintrag am angegebenen Index.
     * @throws IndexOutOfBoundsException Wenn der Index ungültig ist.
     */
    public WortEintrag getEintrag(int index) {
        if (index < 0 || index >= wortEinträge.size()) {
            throw new IndexOutOfBoundsException("Index außerhalb des gültigen Bereichs.");
        }
        return wortEinträge.get(index);
    }

    /**
     * Entfernt einen WortEintrag basierend auf dem Wort.
     * @param wort Das zu entfernende Wort.
     * @return true, wenn das Wort erfolgreich entfernt wurde, sonst false.
     * @throws IllegalArgumentException Wenn das Wort null ist.
     */
    public boolean deleteWort(String wort) {
        if (wort == null) {
            throw new IllegalArgumentException("Wort darf nicht null sein.");
        }
        return wortEinträge.removeIf(eintrag -> eintrag.getWort().equals(wort));
    }

    /**
     * Gibt die Liste aller WortEinträge zurück.
     * @return Eine Liste der WortEinträge.
     */
    public List<WortEintrag> getWortEinträge() {
        return wortEinträge;
    }

    /**
     * Gibt eine textuelle Repräsentation der WortListe zurück.
     * @return Alle WortEinträge als String.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (WortEintrag eintrag : wortEinträge) {
            sb.append(eintrag.toString()).append("\n");
        }
        return sb.toString();
    }
}
