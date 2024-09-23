import Control.Control;
import Model.WortTrainer;
import Persistence.WortTrainerPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ControlTest {
    private Control control;

    @BeforeEach
    void setUp() {
        control = new Control();
    }

    @Test
    void testLoadWortTrainer() {
        try {
            WortTrainer loadedTrainer = WortTrainerPersistence.laden(Control.getFilePath());
            assertNotNull(loadedTrainer);
            assertNotNull(loadedTrainer.getWortListe());
            assertFalse(loadedTrainer.getWortListe().getWortEinträge().isEmpty());
        } catch (IOException e) {
            fail("IOException occurred during loading: " + e.getMessage());
        }
    }

    @Test
    void testHandleGuessCorrect() {
        control.getWortTrainer().getWortListe().addWort("Löwe", "https://www.ast-reisen.de/wp-content/uploads/2018/07/KEN_2018_1TKI_06C5A8313-1.jpg");
        control.getWortTrainer().getZufälligenEintrag();

        String guess = control.getWortTrainer().getAktuellenEintrag().getWort();

        control.getPanel().setText(guess);
        control.handleGuess();

        assertEquals(1, control.getWortTrainer().getRichtigeVersuche());
        assertEquals(1, control.getWortTrainer().getGesamtVersuche());
    }

    @Test
    void testHandleGuessIncorrect() {
        control.getWortTrainer().getWortListe().addWort("Löwe", "https://www.ast-reisen.de/wp-content/uploads/2018/07/KEN_2018_1TKI_06C5A8313-1.jpg");
        control.getWortTrainer().getZufälligenEintrag();

        String incorrectGuess = "Tiger";

        control.getPanel().setText(incorrectGuess);
        control.handleGuess();

        assertEquals(0, control.getWortTrainer().getRichtigeVersuche());
        assertEquals(1, control.getWortTrainer().getGesamtVersuche());
    }

    @Test
    void testResetStatistics() {
        control.getWortTrainer().setRichtigeVersuche(5);
        control.getWortTrainer().setGesamtVersuche(10);

        control.handleReset();

        assertEquals(0, control.getWortTrainer().getRichtigeVersuche());
        assertEquals(0, control.getWortTrainer().getGesamtVersuche());
    }
}
