package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.issue.IssueRecord;
import seedu.address.model.person.StudentId;

/**
 * Tests for {@link ReturnCommand}.
 */
public class ReturnCommandTest {

    private static final IssueRecord VALID_ISSUE_RECORD = new IssueRecord(
            "Wilson-Evolution-Basketball-1",
            new StudentId("a1234567a"),
            LocalDateTime.of(2099, 3, 15, 17, 0));

    @Test
    public void execute_itemReturned_success() throws Exception {
        ModelStubAcceptingReturn modelStub = new ModelStubAcceptingReturn();
        ReturnCommand returnCommand = new ReturnCommand("Wilson-Evolution-Basketball-1");

        CommandResult result = returnCommand.execute(modelStub);

        assertEquals(VALID_ISSUE_RECORD, modelStub.removedIssueRecord);
        assertEquals(String.format(ReturnCommand.MESSAGE_SUCCESS,
                        VALID_ISSUE_RECORD.getItemId(),
                        VALID_ISSUE_RECORD.getStudentId()),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_itemNotIssued_throwsCommandException() {
        ModelStub modelStub = new ModelStub() {
            @Override
            public Optional<IssueRecord> getIssueRecordByItemId(String itemId) {
                return Optional.empty();
            }

            @Override
            public String resolveAlias(String input) {
                return input.toUpperCase();
            }
        };

        ReturnCommand returnCommand = new ReturnCommand("Wilson-Evolution-Basketball-1");

        assertThrows(CommandException.class,
                String.format(ReturnCommand.MESSAGE_NOT_ISSUED,
                        "WILSON-EVOLUTION-BASKETBALL-1"), () -> returnCommand.execute(modelStub));
    }

    /**
     * A model stub that always accepts a return.
     */
    private static class ModelStubAcceptingReturn extends ModelStub {
        private IssueRecord removedIssueRecord;

        @Override
        public Optional<IssueRecord> getIssueRecordByItemId(String itemId) {
            return Optional.of(VALID_ISSUE_RECORD);
        }

        @Override
        public void removeIssueRecord(IssueRecord issueRecord) {
            removedIssueRecord = issueRecord;
        }

        @Override
        public String resolveAlias(String input) {
            return input.toUpperCase();
        }
    }
}
