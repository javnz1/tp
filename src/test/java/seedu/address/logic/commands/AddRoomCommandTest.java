package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.alias.AliasMapping;
import seedu.address.model.issue.IssueRecord;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.room.Room;
import seedu.address.testutil.RoomBuilder;


public class AddRoomCommandTest {

    @Test
    public void execute_roomAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingRoomAdded modelStub = new ModelStubAcceptingRoomAdded();
        Room validRoom = new RoomBuilder().build();

        CommandResult commandResult = new AddRoomCommand(validRoom).execute(modelStub);

        assertEquals(String.format(AddRoomCommand.MESSAGE_SUCCESS, validRoom), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validRoom), modelStub.roomsAdded);
    }

    @Test
    public void execute_duplicateRoom_throwsCommandException() {
        Room validRoom = new RoomBuilder().build();
        AddRoomCommand addRoomCommand = new AddRoomCommand(validRoom);
        ModelStub modelStub = new ModelStubWithRoom(validRoom);

        assertThrows(CommandException.class, AddRoomCommand.MESSAGE_DUPLICATE_ROOM, () ->
                addRoomCommand.execute(modelStub));
    }

    /**
     * A Model stub that always accepts the room being added.
     */
    private static class ModelStubAcceptingRoomAdded extends ModelStub {
        final ArrayList<Room> roomsAdded = new ArrayList<>();

        @Override
        public boolean hasRoom(Room room) {
            requireNonNull(room);
            return roomsAdded.stream().anyMatch(room::isSameRoom);
        }

        @Override
        public void addRoom(Room room) {
            requireNonNull(room);
            roomsAdded.add(room);
        }
    }

    /**
     * A Model stub that contains a single room.
     */
    private class ModelStubWithRoom extends ModelStub {
        private final Room room;

        ModelStubWithRoom(Room room) {
            requireNonNull(room);
            this.room = room;
        }

        @Override
        public boolean hasRoom(Room room) {
            requireNonNull(room);
            return this.room.isSameRoom(room);
        }
    }
}
