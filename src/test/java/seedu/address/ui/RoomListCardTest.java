package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.room.Room;
import seedu.address.testutil.RoomBuilder;

public class RoomListCardTest {

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
    public void constructor_validRoom_initializesFields() {
        Room room = new RoomBuilder().withName("Sports-Hall-4").withLocation("University-Town").build();
        RoomListCard roomListCard = new RoomListCard(room, 1);

        assertNotNull(roomListCard.getRoot());
    }

    @Test
    public void equals() {
        Room room = new RoomBuilder().withName("MPSH-2").build();
        RoomListCard card1 = new RoomListCard(room, 1);
        RoomListCard card2 = new RoomListCard(room, 1);

        assertEquals(card1, card1);

        assertNotEquals(card1, null);
        assertNotEquals(card1, "Not a card");

        assertTrue(card1.getRoot().isVisible());
    }
}
