import Model.WortEintrag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Klasse WortEintragTest enthält Tests für die Klasse WortEintrag.
 * Sie überprüft die Funktionalität der Erstellung von WortEinträgen,
 * einschließlich der Validierung von Wörtern und URLs sowie der
 * Methoden zum Setzen von Werten.
 *
 * @see Model.WortEintrag
 */
class WortEintragTest {

    /**
     * Testet die Erstellung eines WortEintrags.
     * Überprüft, ob das Wort und die URL korrekt gesetzt werden.
     */
    @Test
    void testWortEintragErstellung() {
        WortEintrag eintrag = new WortEintrag("Apfel", "https://res.cloudinary.com/saas-ag/image/upload/w_1200,h_630,b_white,c_pad,q_auto,f_auto/v1664972437/mpreis/products/ac8c0f84-00fa-4ae1-8f75-d32e6b9d5973.jpg");
        assertEquals("Apfel", eintrag.getWort());
        assertEquals("https://res.cloudinary.com/saas-ag/image/upload/w_1200,h_630,b_white,c_pad,q_auto,f_auto/v1664972437/mpreis/products/ac8c0f84-00fa-4ae1-8f75-d32e6b9d5973.jpg", eintrag.getUrl());
    }

    /**
     * Testet die Erstellung eines WortEintrags mit einer ungültigen URL.
     * Überprüft, ob eine IllegalArgumentException geworfen wird.
     */
    @Test
    void testUngültigeUrl() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WortEintrag("Test", "invalid-url");
        });
    }

    /**
     * Testet das Setzen eines neuen Worts im WortEintrag.
     * Überprüft, ob das Wort erfolgreich aktualisiert wird.
     */
    @Test
    void testSetWort() {
        WortEintrag eintrag = new WortEintrag("Apfel", "https://res.cloudinary.com/saas-ag/image/upload/w_1200,h_630,b_white,c_pad,q_auto,f_auto/v1664972437/mpreis/products/ac8c0f84-00fa-4ae1-8f75-d32e6b9d5973.jpg");
        eintrag.setWort("Birne");
        assertEquals("Birne", eintrag.getWort());
    }

    /**
     * Testet das Setzen eines ungültigen Worts im WortEintrag.
     * Überprüft, ob eine IllegalArgumentException geworfen wird.
     */
    @Test
    void testSetWortUngültig() {
        WortEintrag eintrag = new WortEintrag("Apfel", "https://res.cloudinary.com/saas-ag/image/upload/w_1200,h_630,b_white,c_pad,q_auto,f_auto/v1664972437/mpreis/products/ac8c0f84-00fa-4ae1-8f75-d32e6b9d5973.jpg");
        assertThrows(IllegalArgumentException.class, () -> eintrag.setWort("A"));
    }

    /**
     * Testet das Setzen einer neuen URL im WortEintrag.
     * Überprüft, ob die URL erfolgreich aktualisiert wird.
     */
    @Test
    void testSetUrl() {
        WortEintrag eintrag = new WortEintrag("Apfel", "https://res.cloudinary.com/saas-ag/image/upload/w_1200,h_630,b_white,c_pad,q_auto,f_auto/v1664972437/mpreis/products/ac8c0f84-00fa-4ae1-8f75-d32e6b9d5973.jpg");
        eintrag.setUrl("https://eu-images.contentstack.com/v3/assets/blt085f7ef3068b29af/blt63d61e1cbdc2f58b/63a19da71a5252480c4ee85d/birne.png?disposition=inline&crop=618,463.3033730357322,x0,y0,safe");
        assertEquals("https://eu-images.contentstack.com/v3/assets/blt085f7ef3068b29af/blt63d61e1cbdc2f58b/63a19da71a5252480c4ee85d/birne.png?disposition=inline&crop=618,463.3033730357322,x0,y0,safe", eintrag.getUrl());
    }

    /**
     * Testet das Setzen einer ungültigen URL im WortEintrag.
     * Überprüft, ob eine IllegalArgumentException geworfen wird.
     */
    @Test
    void testSetUrlUngültig() {
        WortEintrag eintrag = new WortEintrag("Apfel", "https://res.cloudinary.com/saas-ag/image/upload/w_1200,h_630,b_white,c_pad,q_auto,f_auto/v1664972437/mpreis/products/ac8c0f84-00fa-4ae1-8f75-d32e6b9d5973.jpg");
        assertThrows(IllegalArgumentException.class, () -> eintrag.setUrl("ungültigeUrl"));
    }
}
