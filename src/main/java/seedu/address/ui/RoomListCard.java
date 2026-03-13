package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.room.Room;

/**
 * A UI component that displays information of a {@code Room}.
 */
public class RoomListCard extends UiPart<Region> {

    private static final String FXML = "RoomListCard.fxml";

    public final Room room;

    @FXML
    private HBox cardPane;
    @FXML
    private Label roomName;
    @FXML
    private Label id;
    @FXML
    private Label roomLocation;
    @FXML
    private Label status;

    /**
     * Creates a {@code RoomCode} with the given {@code Room} and index to display.
     */
    public RoomListCard(Room room, int displayedIndex) {
        super(FXML);
        this.room = room;
        id.setText(displayedIndex + ". ");
        roomName.setText(room.getName().toString());
        roomLocation.setText(room.getLocation().toString());
        status.setText(room.getStatus().toString());
    }
}
