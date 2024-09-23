import Model.WortListe;
import Model.WortEintrag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Klasse WortListeTest enthält Tests für die Klasse WortListe.
 * Sie überprüft die Funktionalität des Hinzufügens, Löschens und Abrufens von
 * WortEinträgen in einer WortListe.
 *
 * @see Model.WortListe
 * @see Model.WortEintrag
 */
class WortListeTest {

    /**
     * Testet das Hinzufügen eines neuen WortEintrags zur WortListe.
     * Überprüft, ob die Anzahl der Einträge und das Wort korrekt sind.
     */
    @Test
    void testAddWort() {
        WortListe liste = new WortListe();
        liste.addWort("Apfel", "https://res.cloudinary.com/saas-ag/image/upload/w_1200,h_630,b_white,c_pad,q_auto,f_auto/v1664972437/mpreis/products/ac8c0f84-00fa-4ae1-8f75-d32e6b9d5973.jpg");
        assertEquals(1, liste.getWortEinträge().size());
        assertEquals("Apfel", liste.getEintrag(0).getWort());
    }

    /**
     * Testet das Löschen eines WortEintrags aus der WortListe.
     * Überprüft, ob der Eintrag erfolgreich gelöscht wurde.
     */
    @Test
    void testDeleteWort() {
        WortListe liste = new WortListe();
        liste.addWort("Apfel", "https://res.cloudinary.com/saas-ag/image/upload/w_1200,h_630,b_white,c_pad,q_auto,f_auto/v1664972437/mpreis/products/ac8c0f84-00fa-4ae1-8f75-d32e6b9d5973.jpg");
        assertTrue(liste.deleteWort("Apfel"));
        assertEquals(0, liste.getWortEinträge().size());
    }

    /**
     * Testet das Abrufen eines WortEintrags anhand des Index.
     * Überprüft, ob das korrekte Wort zurückgegeben wird.
     */
    @Test
    void testGetEintrag() {
        WortListe liste = new WortListe();
        liste.addWort("Apfel", "https://res.cloudinary.com/saas-ag/image/upload/w_1200,h_630,b_white,c_pad,q_auto,f_auto/v1664972437/mpreis/products/ac8c0f84-00fa-4ae1-8f75-d32e6b9d5973.jpg");
        WortEintrag eintrag = liste.getEintrag(0);
        assertEquals("Apfel", eintrag.getWort());
    }

    /**
     * Testet das Abrufen eines WortEintrags mit einem ungültigen Index.
     * Überprüft, ob eine IndexOutOfBoundsException geworfen wird.
     */
    @Test
    void testGetEintragUngültig() {
        WortListe liste = new WortListe();
        liste.addWort("Apfel", "https://res.cloudinary.com/saas-ag/image/upload/w_1200,h_630,b_white,c_pad,q_auto,f_auto/v1664972437/mpreis/products/ac8c0f84-00fa-4ae1-8f75-d32e6b9d5973.jpg");
        assertThrows(IndexOutOfBoundsException.class, () -> liste.getEintrag(1));
    }
}
