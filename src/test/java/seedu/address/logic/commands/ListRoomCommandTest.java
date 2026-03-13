package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRooms.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListRoomCommand.
 */
public class ListRoomCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsEverything() {
        CommandResult expectedCommandResult = new CommandResult(ListRoomCommand.MESSAGE_SUCCESS, true);
        assertCommandSuccess(new ListRoomCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        model.updateFilteredRoomList(room -> false);
        CommandResult expectedCommandResult = new CommandResult(ListRoomCommand.MESSAGE_SUCCESS, true);
        assertCommandSuccess(new ListRoomCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_emptyList_throwsCommandException() {
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        assertCommandFailure(new ListRoomCommand(), emptyModel, ListRoomCommand.MESSAGE_FAILURE);
    }

    @Test
    public void equals() {
        ListRoomCommand listRoomCommand = new ListRoomCommand();
        assertTrue(listRoomCommand.equals(listRoomCommand));
        assertTrue(listRoomCommand.equals(new ListRoomCommand()));
        assertFalse(listRoomCommand.equals(null));
        assertFalse(listRoomCommand.equals(new ClearCommand()));
    }
}
