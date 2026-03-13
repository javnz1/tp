package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.room.Room;
import seedu.address.testutil.RoomBuilder;

public class RoomListPanelTest {

    private static final Logger logger = LogsCenter.getLogger(RoomListCardTest.class);

    @BeforeAll
    public static void setupSpec() {
        try {
            javafx.application.Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            logger.info("JavaFX platform already started, skipping initialization.");
        }
    }

    @Test
    public void constructor_validRoomList_loadsListView() {
        ObservableList<Room> roomList = FXCollections.observableArrayList(
                new RoomBuilder().withName("Kent-Ridge-Outdoor-Court").build()
        );
        RoomListPanel roomListPanel = new RoomListPanel(roomList);

        assertNotNull(roomListPanel.getRoot());
    }
}
