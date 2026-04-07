package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EQUIPMENTS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all equipment in the address book to the user.
 */
public class ListEquipmentCommand extends Command {

    public static final String COMMAND_WORD = "list-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of all equipment.\n"
            + "Format: " + COMMAND_WORD + "\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all equipment";
    public static final String MESSAGE_EMPTY_LIST = "Equipment list is currently empty. Use the 'add-e' command to "
            + "add your first equipment!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredEquipmentList(PREDICATE_SHOW_ALL_EQUIPMENTS);

        if (model.getFilteredEquipmentList().isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST, false, false, true, true, true);
        }

        return new CommandResult(MESSAGE_SUCCESS,
                false, false, true, true, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ListEquipmentCommand);
    }

    @Override
    public int hashCode() {
        return ListEquipmentCommand.class.hashCode();
    }
}
