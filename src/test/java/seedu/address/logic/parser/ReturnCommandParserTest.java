package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReturnCommand;
import seedu.address.model.issue.IssueRecord;

/**
 * Tests for {@link ReturnCommandParser}.
 */
public class ReturnCommandParserTest {

    private final ReturnCommandParser parser = new ReturnCommandParser();

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, " Wilson-Evolution-Basketball-1",
                new ReturnCommand("Wilson-Evolution-Basketball-1"));
    }

    @Test
    public void parse_noArgs_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_tooManyArgs_failure() {
        assertParseFailure(parser, " Wilson-Evolution-Basketball-1 extra",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidItemId_failure() {
        assertParseFailure(parser, " !invalid", IssueRecord.MESSAGE_ITEM_ID_CONSTRAINTS);
    }
}
