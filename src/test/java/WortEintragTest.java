import Model.WortEintrag;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WortEintragTest {

    @Test
    void testWortEintragErstellung() {
        WortEintrag eintrag = new WortEintrag("Apfel", "https://example.com/apfel.jpg");
        assertEquals("Apfel", eintrag.getWort());
        assertEquals("https://example.com/apfel.jpg", eintrag.getUrl());
    }

    @Test
    void testUngültigeUrl() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WortEintrag("Test", "invalid-url");
        });
    }

    @Test
    void testSetWort() {
        WortEintrag eintrag = new WortEintrag("Apfel", "https://example.com/apfel.jpg");
        eintrag.setWort("Birne");
        assertEquals("Birne", eintrag.getWort());
    }

    @Test
    void testSetWortUngültig() {
        WortEintrag eintrag = new WortEintrag("Apfel", "https://example.com/apfel.jpg");
        assertThrows(IllegalArgumentException.class, () -> eintrag.setWort("A"));
    }

    @Test
    void testSetUrl() {
        WortEintrag eintrag = new WortEintrag("Apfel", "https://example.com/apfel.jpg");
        eintrag.setUrl("https://example.com/neu.jpg");
        assertEquals("https://example.com/neu.jpg", eintrag.getUrl());
    }

    @Test
    void testSetUrlUngültig() {
        WortEintrag eintrag = new WortEintrag("Apfel", "https://example.com/apfel.jpg");
        assertThrows(IllegalArgumentException.class, () -> eintrag.setUrl("ungültigeUrl"));
    }
}