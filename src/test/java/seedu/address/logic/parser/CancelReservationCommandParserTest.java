package seedu.address.logic.parser;


import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CancelReservationCommand;
import seedu.address.model.person.StudentId;
import seedu.address.model.reservation.Reservation;

/**
 * Tests for {@link CancelReservationCommandParser}.
 */
public class CancelReservationCommandParserTest {

    private final CancelReservationCommandParser parser = new CancelReservationCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Reservation reservation = new Reservation("Hall-2", new StudentId("a1234567a"),
                LocalDateTime.of(2099, 3, 15, 9, 0),
                LocalDateTime.of(2099, 3, 15, 11, 0));

        assertParseSuccess(parser,
                " Hall-2 a1234567a f/2099-03-15 0900 t/2099-03-15 1100",
                new CancelReservationCommand(reservation));
    }


    @Test
    public void parse_invalidStudentId_failure() {
        assertParseFailure(parser,
                " Hall-2 invalid f/2099-03-15 0900 t/2099-03-15 1100",
                StudentId.MESSAGE_CONSTRAINTS);
    }
}
