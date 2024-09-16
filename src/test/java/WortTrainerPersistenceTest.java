import Model.WortEintrag;
import Model.WortListe;
import Model.WortTrainer;
import Persistence.WortTrainerPersistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WortTrainerPersistenceTest {

    private static final String TEST_FILE = "testWortTrainer.txt";
    private WortTrainer wortTrainer;

    @BeforeEach
    void setUp() {
        WortListe wortListe = new WortListe();
        wortListe.addWort("Apfel", "https://example.com/apfel.jpg");
        wortListe.addWort("Banane", "https://example.com/banane.jpg");
        wortTrainer = new WortTrainer(wortListe);
        wortTrainer.setRichtigeVersuche(3);
        wortTrainer.setGesamtVersuche(7);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSpeichernUndLaden() throws IOException {
        WortTrainerPersistence.speichern(wortTrainer, TEST_FILE);
        WortTrainer geladenerTrainer = WortTrainerPersistence.laden(TEST_FILE);

        assertEquals(wortTrainer.getRichtigeVersuche(), geladenerTrainer.getRichtigeVersuche());
        assertEquals(wortTrainer.getGesamtVersuche(), geladenerTrainer.getGesamtVersuche());

        assertEquals(wortTrainer.getWortListe().getWortEinträge().size(),
                geladenerTrainer.getWortListe().getWortEinträge().size());

        WortEintrag originalEintrag = wortTrainer.getWortListe().getEintrag(0);
        WortEintrag geladenerEintrag = geladenerTrainer.getWortListe().getEintrag(0);
        assertEquals(originalEintrag.getWort(), geladenerEintrag.getWort());
        assertEquals(originalEintrag.getUrl(), geladenerEintrag.getUrl());
    }

    @Test
    void testSpeichernUndLadenLeeresWortTrainer() throws IOException {
        WortTrainer leererTrainer = new WortTrainer(new WortListe());
        leererTrainer.setRichtigeVersuche(0);
        leererTrainer.setGesamtVersuche(0);

        WortTrainerPersistence.speichern(leererTrainer, TEST_FILE);
        WortTrainer geladenerTrainer = WortTrainerPersistence.laden(TEST_FILE);

        assertEquals(leererTrainer.getRichtigeVersuche(), geladenerTrainer.getRichtigeVersuche());
        assertEquals(leererTrainer.getGesamtVersuche(), geladenerTrainer.getGesamtVersuche());
        assertTrue(geladenerTrainer.getWortListe().getWortEinträge().isEmpty());
    }

    @Test
    void testLadenMitNichtExistierenderDatei() {
        assertThrows(IOException.class, () -> WortTrainerPersistence.laden("nichtExistierendeDatei.txt"));
    }
}
