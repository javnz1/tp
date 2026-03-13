package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.application.Platform;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.model.ModelManager;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;


public class MainWindowTest {

    private static final Logger logger = LogsCenter.getLogger(RoomListCardTest.class);

    @TempDir
    public Path temporaryFolder;

    @BeforeAll
    public static void setupSpec() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            logger.info("JavaFX platform already started, skipping initialization.");
        }
    }

    @Test
    public void constructor_initializesCorrectly() {
        Path abPath = temporaryFolder.resolve("addressBook.json");
        JsonAddressBookStorage abStorage = new JsonAddressBookStorage(abPath);
        JsonUserPrefsStorage prefStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));

        StorageManager storage = new StorageManager(abStorage, prefStorage);
        LogicManager logic = new LogicManager(new ModelManager(), storage);

        Platform.runLater(() -> {
            MainWindow mainWindow = new MainWindow(new Stage(), logic);
            assertNotNull(mainWindow.getRoot());
        });
    }
}
