package Model;

import java.net.URI;
import java.net.URISyntaxException;

public class WortEintrag {
    private String wort;
    private String url;

    public WortEintrag(String wort, String url) {
        if (wort == null || url == null) {
            throw new IllegalArgumentException("Wort und URL dürfen nicht null sein.");
        }
        this.wort = wort;
        this.url = url;
    }

    public static boolean checkUrl(String url) {
        try {
            URI uri = new URI(url);
            return uri.getScheme() != null && uri.getHost() != null;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    public String getWort() {
        return wort;
    }

    public void setWort(String wort) {
        if (wort != null && wort.length() >= 2) {
            this.wort = wort;
        } else {
            throw new IllegalArgumentException("Wort muss mindestens 2 Zeichen haben.");
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (checkUrl(url)) {
            this.url = url;
        } else {
            throw new IllegalArgumentException("URL ist nicht gültig.");
        }
    }

    @Override
    public String toString() {
        return wort + " ; " + url;
    }
}
