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

/**
 * Die Klasse WortTrainerPersistenceTest enthält Tests für die Persistenzschicht
 * der WortTrainer-Klasse. Sie überprüft die Funktionalität des Speicherns
 * und Ladens von WortTrainern in und aus Dateien.
 *
 * @see Model.WortTrainer
 * @see Persistence.WortTrainerPersistence
 */

class WortTrainerPersistenceTest {

    private static final String TEST_FILE = "testWortTrainer.txt";
    private WortTrainer wortTrainer;

    /**
     * Setzt die Testumgebung vor jedem Testfall auf.
     * Initialisiert einen WortTrainer mit Beispielworten.
     */
    @BeforeEach
    void setUp() {
        WortListe wortListe = new WortListe();
        wortListe.addWort("Löwe", "https://www.ast-reisen.de/wp-content/uploads/2018/07/KEN_2018_1TKI_06C5A8313-1.jpg");
        wortListe.addWort("Flugzeug", "https://ungarnheute.hu/wp-content/uploads/2024/02/pexels-tibor-szabo-16926515-2048x1365-1.jpg");
        wortTrainer = new WortTrainer(wortListe);
        wortTrainer.setRichtigeVersuche(3);
        wortTrainer.setGesamtVersuche(7);
    }
    /**
     * Räumt nach jedem Testfall auf.
     * Löscht die Testdatei, wenn sie existiert.
     */
    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Testet das Speichern und Laden eines WortTrainings.
     * Überprüft, ob die geladenen Werte mit den gespeicherten übereinstimmen.
     */
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

    /**
     * Testet das Speichern und Laden eines leeren WortTrainings.
     * Überprüft, ob die geladenen Werte korrekt sind.
     */
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

    /**
     * Testet das Laden eines WortTrainings mit einer nicht existierenden Datei.
     * Überprüft, ob eine IOException geworfen wird.
     */
    @Test
    void testLadenMitNichtExistierenderDatei() {
        assertThrows(IOException.class, () -> WortTrainerPersistence.laden("nichtExistierendeDatei.txt"));
    }
}
