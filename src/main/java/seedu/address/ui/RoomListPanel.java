package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.room.Room;

/**
 * Panel containing the list of rooms.
 */
public class RoomListPanel extends UiPart<Region> {
    private static final String FXML = "RoomListPanel.fxml";

    @FXML
    private ListView<Room> roomListView;

    /**
     * Creates a {@code RoomListPanel} with the given {@code ObservableList}.
     */
    public RoomListPanel(ObservableList<Room> roomList) {
        super(FXML);
        roomListView.setItems(roomList);
        roomListView.setCellFactory(listView -> new RoomListViewCell());
    }

    ListView<Room> getRoomListView() {
        return roomListView; // This is the @FXML variable name in your class
    }

    // Internal class for the Custom Cell
    class RoomListViewCell extends ListCell<Room> {
        @Override
        protected void updateItem(Room room, boolean empty) {
            super.updateItem(room, empty);
            if (empty || room == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RoomListCard(room, getIndex() + 1).getRoot());
            }
        }
    }
}
