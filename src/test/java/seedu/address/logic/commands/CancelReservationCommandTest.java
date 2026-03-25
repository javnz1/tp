package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.person.StudentId;
import seedu.address.model.reservation.Reservation;

/**
 * Tests for {@link CancelReservationCommand}.
 */
public class CancelReservationCommandTest {

    private static final Reservation VALID_RESERVATION = new Reservation(
            "Hall-2",
            new StudentId("a1234567a"),
            LocalDateTime.of(2099, 3, 15, 9, 0),
            LocalDateTime.of(2099, 3, 15, 11, 0));

    @Test
    public void execute_cancelReservation_success() throws Exception {
        ModelStubAcceptingCancel modelStub = new ModelStubAcceptingCancel();
        CancelReservationCommand command = new CancelReservationCommand(VALID_RESERVATION);

        CommandResult result = command.execute(modelStub);

        assertEquals(VALID_RESERVATION, modelStub.removedReservation);
        assertEquals(String.format(CancelReservationCommand.MESSAGE_SUCCESS,
                        VALID_RESERVATION.getResourceId(),
                        VALID_RESERVATION.getStudentId(),
                        VALID_RESERVATION.getFormattedStartDateTime(),
                        VALID_RESERVATION.getFormattedEndDateTime()),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_reservationNotFound_throwsCommandException() {
        ModelStub modelStub = new ModelStub() {
            @Override
            public Optional<Reservation> getMatchingReservation(Reservation reservation) {
                return Optional.empty();
            }

            @Override
            public String resolveAlias(String input) {
                return input.toUpperCase();
            }
        };

        CancelReservationCommand command = new CancelReservationCommand(VALID_RESERVATION);

        assertThrows(CommandException.class,
                String.format(CancelReservationCommand.MESSAGE_NOT_FOUND,
                        "HALL-2",
                        VALID_RESERVATION.getStudentId(),
                        VALID_RESERVATION.getFormattedStartDateTime(),
                        VALID_RESERVATION.getFormattedEndDateTime()), () -> command.execute(modelStub));
    }

    /**
     * A model stub that always accepts a reservation cancellation.
     */
    private static class ModelStubAcceptingCancel extends ModelStub {
        private Reservation removedReservation;

        @Override
        public Optional<Reservation> getMatchingReservation(Reservation reservation) {
            return Optional.of(VALID_RESERVATION);
        }

        @Override
        public void removeReservation(Reservation reservation) {
            removedReservation = reservation;
        }

        @Override
        public String resolveAlias(String input) {
            return input.toUpperCase();
        }
    }
}
