package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all rooms in the address book to the user.
 */
public class ListRoomCommand extends Command {

    public static final String COMMAND_WORD = "list-r";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of all room.\n"
            + "Format: " + COMMAND_WORD + "\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all rooms";
    public static final String MESSAGE_EMPTY_LIST = "Room list is currently empty. Use the 'add-r' command to "
            + "add your first room!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);

        if (model.getFilteredRoomList().isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST, false, false, true, true, true);
        }

        return new CommandResult(MESSAGE_SUCCESS,
                false, false, true, true, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ListRoomCommand);
    }

    @Override
    public int hashCode() {
        return ListRoomCommand.class.hashCode();
    }
}
