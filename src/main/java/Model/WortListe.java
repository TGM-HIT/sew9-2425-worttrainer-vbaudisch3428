package Model;

import java.util.ArrayList;
import java.util.List;

public class WortListe {
    private List<WortEintrag> wortEinträge;

    public WortListe() {
        this.wortEinträge = new ArrayList<>();
    }

    public void addWort(String wort, String url) {
        if (wort == null || url == null) {
            throw new IllegalArgumentException("Wort und URL dürfen nicht null sein.");
        }
        WortEintrag neuerEintrag = new WortEintrag(wort, url);
        wortEinträge.add(neuerEintrag);
    }

    public WortEintrag getEintrag(int index) {
        if (index < 0 || index >= wortEinträge.size()) {
            throw new IndexOutOfBoundsException("Index außerhalb des gültigen Bereichs.");
        }
        return wortEinträge.get(index);
    }

    public boolean deleteWort(String wort) {
        if (wort == null) {
            throw new IllegalArgumentException("Wort darf nicht null sein.");
        }
        return wortEinträge.removeIf(eintrag -> eintrag.getWort().equals(wort));
    }

    public List<WortEintrag> getWortEinträge() {
        return wortEinträge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (WortEintrag eintrag : wortEinträge) {
            sb.append(eintrag.toString()).append("\n");
        }
        return sb.toString();
    }
}
