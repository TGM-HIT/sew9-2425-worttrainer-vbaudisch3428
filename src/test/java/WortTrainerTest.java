import Model.WortEintrag;
import Model.WortListe;
import Model.WortTrainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Klasse WortTrainerTest enthält Tests für die Funktionalität der
 * WortTrainer-Klasse. Sie überprüft die Methoden zum Abrufen und
 * Überprüfen von Wort-Einträgen sowie das Setzen der Versuchszähler.
 *
 * @see Model.WortTrainer
 */
class WortTrainerTest {

    private WortTrainer trainer;

    /**
     * Setzt die Testumgebung vor jedem Testfall auf.
     * Initialisiert einen WortTrainer mit einem Beispielwort.
     */
    @BeforeEach
    void setUp() {
        WortListe liste = new WortListe();
        liste.addWort("Apfel", "https://res.cloudinary.com/saas-ag/image/upload/w_1200,h_630,b_white,c_pad,q_auto,f_auto/v1664972437/mpreis/products/ac8c0f84-00fa-4ae1-8f75-d32e6b9d5973.jpg");
        trainer = new WortTrainer(liste);
    }

    /**
     * Testet das Abrufen eines zufälligen Eintrags.
     * Überprüft, ob der Eintrag nicht null ist und den erwarteten Wert hat.
     */
    @Test
    void testGetZufälligenEintrag() {
        WortEintrag eintrag = trainer.getZufälligenEintrag();
        assertNotNull(eintrag);
        assertEquals("Apfel", eintrag.getWort());
    }

    /**
     * Testet die Überprüfung eines korrekten Wortes ohne Beachtung der Groß- und Kleinschreibung.
     * Überprüft, ob die Zähler für richtige und gesamt Versuche korrekt aktualisiert werden.
     */
    @Test
    void testCheckIgnoreCaseRichtig() {
        trainer.getZufälligenEintrag(); // Sicherstellen, dass ein Eintrag gesetzt ist
        boolean korrekt = trainer.checkIgnoreCase("Apfel");
        assertTrue(korrekt);
        assertEquals(1, trainer.getRichtigeVersuche());
        assertEquals(1, trainer.getGesamtVersuche());
    }

    /**
     * Testet die Überprüfung eines falschen Wortes ohne Beachtung der Groß- und Kleinschreibung.
     * Überprüft, ob die Zähler für richtige und gesamt Versuche korrekt aktualisiert werden.
     */
    @Test
    void testCheckIgnoreCaseFalsch() {
        trainer.getZufälligenEintrag(); // Sicherstellen, dass ein Eintrag gesetzt ist
        boolean korrekt = trainer.checkIgnoreCase("Birne");
        assertFalse(korrekt);
        assertEquals(0, trainer.getRichtigeVersuche());
        assertEquals(1, trainer.getGesamtVersuche());
    }

    /**
     * Testet das Setzen der Anzahl der richtigen Versuche.
     * Überprüft, ob der Wert korrekt gesetzt wird.
     */
    @Test
    void testSetRichtigeVersuche() {
        trainer.setRichtigeVersuche(5);
        assertEquals(5, trainer.getRichtigeVersuche());
    }

    /**
     * Testet das Setzen der Gesamtanzahl der Versuche.
     * Überprüft, ob der Wert korrekt gesetzt wird.
     */
    @Test
    void testSetGesamtVersuche() {
        trainer.setGesamtVersuche(10);
        assertEquals(10, trainer.getGesamtVersuche());
    }
}
