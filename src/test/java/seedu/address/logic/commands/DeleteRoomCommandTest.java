package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ROOM;
import static seedu.address.testutil.TypicalRooms.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.Room;
import seedu.address.testutil.RoomBuilder;

public class DeleteRoomCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Room roomToDelete = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(INDEX_FIRST_ROOM);

        String expectedMessage = String.format(DeleteRoomCommand.MESSAGE_DELETE_ROOM_SUCCESS, roomToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteRoom(roomToDelete);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, true);

        assertCommandSuccess(deleteRoomCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredRoomList().size() + 1);
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(outOfBoundsIndex);

        assertCommandFailure(deleteRoomCommand, model, DeleteRoomCommand.MESSAGE_FAILURE);
    }

    @Test
    public void execute_bookedRoom_throwsCommandException() {
        // Create a room with a UNIQUE name that isn't in TypicalRooms
        Room bookedRoom = new RoomBuilder()
                .withName("Unique-Room-999")
                .withStatus("Booked")
                .build();

        model.addRoom(bookedRoom);

        Index indexLast = Index.fromOneBased(model.getFilteredRoomList().size());

        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(indexLast);

        assertCommandFailure(deleteRoomCommand, model, DeleteRoomCommand.MESSAGE_ROOM_BOOKED);
    }
}
