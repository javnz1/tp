package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ReturnCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.issue.IssueRecord;

/**
 * Parses input arguments and creates a new ReturnCommand object.
 */
public class ReturnCommandParser implements Parser<ReturnCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReturnCommand
     * and returns a ReturnCommand object for execution.
     *
     * @param args Full user input arguments.
     * @return A ReturnCommand for the specified item.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public ReturnCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty() || trimmedArgs.split("\\s+").length != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE));
        }

        if (!IssueRecord.isValidItemId(trimmedArgs)) {
            throw new ParseException(IssueRecord.MESSAGE_ITEM_ID_CONSTRAINTS);
        }

        return new ReturnCommand(trimmedArgs);
    }
}
