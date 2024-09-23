import Control.Control;
import Model.WortTrainer;
import Persistence.WortTrainerPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Klasse ControlTest enthält Tests für die Klasse Control.
 * Sie überprüft die Funktionalität des WortTrainers, einschließlich
 * das Laden von Wörtern, das Hinzufügen neuer Wörter, das
 * Verarbeiten von Rateversuchen und das Zurücksetzen von Statistiken.
 *
 * @see Control
 * @see Model.WortTrainer
 * @see Persistence.WortTrainerPersistence
 */
class ControlTest {
    private Control control;

    /**
     * Initialisiert eine Control-Instanz vor jedem Test.
     */
    @BeforeEach
    void setUp() {
        control = new Control();
    }

    /**
     * Testet das Laden des WortTrainers aus einer Datei.
     * Stellt sicher, dass der geladene Trainer nicht null ist
     * und dass die Wortliste nicht leer ist.
     */
    @Test
    void testLoadWortTrainer() {
        try {
            WortTrainer loadedTrainer = WortTrainerPersistence.laden(Control.FILE_PATH);
            assertNotNull(loadedTrainer);
            assertNotNull(loadedTrainer.getWortListe());
            assertFalse(loadedTrainer.getWortListe().getWortEinträge().isEmpty());
        } catch (IOException e) {
            fail("IOException occurred during loading: " + e.getMessage());
        }
    }

    /**
     * Testet die Verarbeitung eines korrekten Rateversuchs.
     * Überprüft, ob die Statistiken korrekt aktualisiert werden.
     */
    @Test
    void testHandleGuessCorrect() {
        control.wortTrainer.getWortListe().addWort("Löwe", "https://www.ast-reisen.de/wp-content/uploads/2018/07/KEN_2018_1TKI_06C5A8313-1.jpg");
        control.wortTrainer.getZufälligenEintrag();

        String guess = control.wortTrainer.getAktuellenEintrag().getWort();

        control.panel.setText(guess);
        control.handleGuess();

        assertEquals(1, control.wortTrainer.getRichtigeVersuche());
        assertEquals(1, control.wortTrainer.getGesamtVersuche());
    }

    /**
     * Testet die Verarbeitung eines falschen Rateversuchs.
     * Überprüft, ob die Statistiken korrekt aktualisiert werden.
     */
    @Test
    void testHandleGuessIncorrect() {
        control.wortTrainer.getWortListe().addWort("Löwe", "https://www.ast-reisen.de/wp-content/uploads/2018/07/KEN_2018_1TKI_06C5A8313-1.jpg");
        control.wortTrainer.getZufälligenEintrag();

        String incorrectGuess = "Tiger";

        control.panel.setText(incorrectGuess);
        control.handleGuess();

        assertEquals(0, control.wortTrainer.getRichtigeVersuche());
        assertEquals(1, control.wortTrainer.getGesamtVersuche());
    }

    /**
     * Testet das Zurücksetzen der Statistiken des WortTrainers.
     * Überprüft, ob die Statistiken auf null zurückgesetzt werden.
     */
    @Test
    void testResetStatistics() {
        control.wortTrainer.setRichtigeVersuche(5);
        control.wortTrainer.setGesamtVersuche(10);

        control.handleReset();

        assertEquals(0, control.wortTrainer.getRichtigeVersuche());
        assertEquals(0, control.wortTrainer.getGesamtVersuche());
    }
}
