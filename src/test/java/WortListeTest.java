import Model.WortListe;
import Model.WortEintrag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WortListeTest {

    @Test
    void testAddWort() {
        WortListe liste = new WortListe();
        liste.addWort("Apfel", "https://example.com/apfel.jpg");
        assertEquals(1, liste.getWortEinträge().size());
        assertEquals("Apfel", liste.getEintrag(0).getWort());
    }

    @Test
    void testDeleteWort() {
        WortListe liste = new WortListe();
        liste.addWort("Apfel", "https://example.com/apfel.jpg");
        assertTrue(liste.deleteWort("Apfel"));
        assertEquals(0, liste.getWortEinträge().size());
    }

    @Test
    void testGetEintrag() {
        WortListe liste = new WortListe();
        liste.addWort("Apfel", "https://example.com/apfel.jpg");
        WortEintrag eintrag = liste.getEintrag(0);
        assertEquals("Apfel", eintrag.getWort());
    }

    @Test
    void testGetEintragUngültig() {
        WortListe liste = new WortListe();
        liste.addWort("Apfel", "https://example.com/apfel.jpg");
        assertThrows(IndexOutOfBoundsException.class, () -> liste.getEintrag(1));
    }
}
