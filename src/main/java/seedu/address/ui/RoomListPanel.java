package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.room.Room;

public class RoomListPanel extends UiPart<Region> {
    private static final String FXML = "RoomListPanel.fxml";

    @FXML
    private ListView<Room> roomListView;

    public RoomListPanel(ObservableList<Room> roomList) {
        super(FXML);
        roomListView.setItems(roomList);
        roomListView.setCellFactory(listView -> new RoomListViewCell());
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