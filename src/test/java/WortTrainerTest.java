import Model.WortEintrag;
import Model.WortListe;
import Model.WortTrainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WortTrainerTest {

    private WortTrainer trainer;

    @BeforeEach
    void setUp() {
        WortListe liste = new WortListe();
        liste.addWort("Apfel", "https://example.com/apfel.jpg");
        trainer = new WortTrainer(liste);
    }

    @Test
    void testGetZufälligenEintrag() {
        WortEintrag eintrag = trainer.getZufälligenEintrag();
        assertNotNull(eintrag);
        assertEquals("Apfel", eintrag.getWort());
    }

    @Test
    void testCheckIgnoreCaseRichtig() {
        trainer.getZufälligenEintrag(); // Sicherstellen, dass ein Eintrag gesetzt ist
        boolean korrekt = trainer.checkIgnoreCase("Apfel");
        assertTrue(korrekt);
        assertEquals(1, trainer.getRichtigeVersuche());
        assertEquals(1, trainer.getGesamtVersuche());
    }

    @Test
    void testCheckIgnoreCaseFalsch() {
        trainer.getZufälligenEintrag(); // Sicherstellen, dass ein Eintrag gesetzt ist
        boolean korrekt = trainer.checkIgnoreCase("Birne");
        assertFalse(korrekt);
        assertEquals(0, trainer.getRichtigeVersuche());
        assertEquals(1, trainer.getGesamtVersuche());
    }

    @Test
    void testSetRichtigeVersuche() {
        trainer.setRichtigeVersuche(5);
        assertEquals(5, trainer.getRichtigeVersuche());
    }

    @Test
    void testSetGesamtVersuche() {
        trainer.setGesamtVersuche(10);
        assertEquals(10, trainer.getGesamtVersuche());
    }
}
