package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteRoomCommandParser implements Parser<DeleteRoomCommand> {

    public DeleteRoomCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteRoomCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteRoomCommand.MESSAGE_USAGE), pe);
        }
    }
}