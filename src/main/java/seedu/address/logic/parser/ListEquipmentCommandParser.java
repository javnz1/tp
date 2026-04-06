package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListEquipmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListEquipmentCommand object
 */
public class ListEquipmentCommandParser implements Parser<ListEquipmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListEquipmentCommand
     * and returns a ListEquipmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListEquipmentCommand parse(String args) throws ParseException {
        if (!args.trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEquipmentCommand.MESSAGE_USAGE));
        }
        return new ListEquipmentCommand();
    }
}
