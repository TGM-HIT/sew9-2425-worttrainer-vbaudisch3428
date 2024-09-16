package Model;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Die Klasse WortEintrag repräsentiert einen Eintrag, der ein Wort und eine zugehörige Bild-URL speichert.
 */
public class WortEintrag {
    private String wort;
    private String url;

    /**
     * Konstruktor für die Klasse WortEintrag.
     * @param wort Das Wort, das gespeichert werden soll.
     * @param url Die URL des zugehörigen Bildes.
     * @throws IllegalArgumentException Wenn das Wort oder die URL null ist.
     */
    public WortEintrag(String wort, String url) {
        if (wort == null || url == null) {
            throw new IllegalArgumentException("Wort und URL dürfen nicht null sein.");
        }
        if (!url.matches("https?://.*")) {
            throw new IllegalArgumentException("Ungültige URL.");
        }
        this.wort = wort;
        this.url = url;
    }


    /**
     * Überprüft, ob die angegebene URL gültig ist.
     * @param url Die zu überprüfende URL.
     * @return true, wenn die URL gültig ist, sonst false.
     */
    public static boolean checkUrl(String url) {
        try {
            URI uri = new URI(url);
            return uri.getScheme() != null && uri.getHost() != null;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    /**
     * Gibt das gespeicherte Wort zurück.
     * @return Das Wort des Eintrags.
     */
    public String getWort() {
        return wort;
    }

    /**
     * Setzt das Wort auf einen neuen Wert, falls es nicht null und mindestens 2 Zeichen lang ist.
     * @param wort Das neue Wort.
     * @throws IllegalArgumentException Wenn das Wort weniger als 2 Zeichen hat oder null ist.
     */
    public void setWort(String wort) {
        if (wort != null && wort.length() >= 2) {
            this.wort = wort;
        } else {
            throw new IllegalArgumentException("Wort muss mindestens 2 Zeichen haben.");
        }
    }

    /**
     * Gibt die gespeicherte URL zurück.
     * @return Die URL des Eintrags.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setzt die URL auf einen neuen Wert, falls sie gültig ist.
     * @param url Die neue URL.
     * @throws IllegalArgumentException Wenn die URL ungültig ist.
     */
    public void setUrl(String url) {
        if (checkUrl(url)) {
            this.url = url;
        } else {
            throw new IllegalArgumentException("URL ist nicht gültig.");
        }
    }

    /**
     * Gibt die textuelle Repräsentation des Eintrags zurück.
     * @return Das Wort und die URL als String.
     */
    @Override
    public String toString() {
        return wort + " ; " + url;
    }
}
