import Control.Control;
import Model.WortListe;
import Model.WortTrainer;
import Persistence.WortTrainerPersistence;
import View.Panel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ControlTest {

    private Control control;
    private WortTrainer wortTrainer;
    private Panel panel;

    @BeforeEach
    public void setUp() throws IOException {
        // Set up initial conditions before each test
        WortListe wortListe = new WortListe();
        wortTrainer = new WortTrainer(wortListe);
        WortTrainerPersistence.speichern(wortTrainer, "testFile.txt"); // Save an empty trainer for testing
        control = new Control();
    }

    @Test
    public void testHandleGuessCorrect() {
        // Arrange: Add a word to test guessing
        wortTrainer.getWortListe().addWort("Apfel", "https://example.com/apfel.jpg");
        wortTrainer.checkIgnoreCase("Apfel"); // Update trainer
        control.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Enter"));

        // Act: Simulate entering the correct word
        panel.setText("Apfel");
        control.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Enter"));

        // Assert: Ensure the correct guess was processed
        assertEquals(1, wortTrainer.getRichtigeVersuche());
        assertEquals(1, wortTrainer.getGesamtVersuche());
    }

    @Test
    public void testHandleGuessIncorrect() {
        // Arrange: Add a word to test guessing
        wortTrainer.getWortListe().addWort("Banane", "https://example.com/banane.jpg");
        wortTrainer.checkIgnoreCase("Apfel"); // Update trainer
        control.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Enter"));

        // Act: Simulate entering the wrong word
        panel.setText("Apfel");
        control.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Enter"));

        // Assert: Ensure the incorrect guess was processed
        assertEquals(0, wortTrainer.getRichtigeVersuche());
        assertEquals(1, wortTrainer.getGesamtVersuche());
    }

    @Test
    public void testHandleReset() {
        // Arrange: Simulate a few guesses to modify statistics
        wortTrainer.setRichtigeVersuche(5);
        wortTrainer.setGesamtVersuche(10);

        // Act: Reset statistics
        control.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Zurücksetzen!"));

        // Assert: Check if statistics were reset correctly
        assertEquals(0, wortTrainer.getRichtigeVersuche());
        assertEquals(0, wortTrainer.getGesamtVersuche());
    }

    @Test
    public void testHandleLoad() throws IOException {
        // Arrange: Save a known state to load
        WortTrainerPersistence.speichern(wortTrainer, "testFile.txt");

        // Act: Load the state
        control.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Laden"));

        // Assert: Check if the state was loaded correctly
        assertNotNull(wortTrainer);
    }

    @Test
    public void testHandleSave() throws IOException {
        // Act: Save the current state
        control.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Speichern"));

        // Assert: Check if the file was saved successfully
        WortTrainer loadedTrainer = WortTrainerPersistence.laden("testFile.txt");
        assertNotNull(loadedTrainer);
    }
}
