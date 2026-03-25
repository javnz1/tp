package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reservation.Reservation;

/**
 * Cancels an existing reservation.
 */
public class CancelReservationCommand extends Command {

    public static final String COMMAND_WORD = "cancel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Cancels an existing reservation.\n"
            + "Parameters: ITEM_OR_ROOM_ID STUDENT_ID f/START_DATE_TIME t/END_DATE_TIME\n"
            + "Date/time format: yyyy-MM-dd HHmm\n"
            + "Example: " + COMMAND_WORD + " Hall-2 a1234567a f/2099-03-15 0900 t/2099-03-15 1100";

    public static final String MESSAGE_SUCCESS =
            "Reservation cancelled:\nReserved %1$s by Student %2$s from %3$s to %4$s";
    public static final String MESSAGE_NOT_FOUND =
            "Error:\nNo matching reservation found for %1$s by %2$s from %3$s to %4$s";

    private final Reservation reservationToCancel;

    /**
     * Creates a CancelReservationCommand to cancel the specified reservation.
     *
     * @param reservation The reservation to cancel.
     */
    public CancelReservationCommand(Reservation reservation) {
        requireNonNull(reservation);
        this.reservationToCancel = reservation;
    }

    /**
     * Executes the cancel reservation command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A command result containing the success message.
     * @throws CommandException If no matching reservation exists.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        String resolvedResourceId = model.resolveAlias(reservationToCancel.getResourceId());
        Reservation resolvedReservation = new Reservation(
                resolvedResourceId,
                reservationToCancel.getStudentId(),
                reservationToCancel.getStartDateTime(),
                reservationToCancel.getEndDateTime());

        Optional<Reservation> existingReservation = model.getMatchingReservation(resolvedReservation);
        if (existingReservation.isEmpty()) {
            throw new CommandException(String.format(
                    MESSAGE_NOT_FOUND,
                    resolvedReservation.getResourceId(),
                    resolvedReservation.getStudentId(),
                    resolvedReservation.getFormattedStartDateTime(),
                    resolvedReservation.getFormattedEndDateTime()));
        }

        Reservation reservation = existingReservation.get();
        model.removeReservation(reservation);

        return new CommandResult(String.format(
                MESSAGE_SUCCESS,
                reservation.getResourceId(),
                reservation.getStudentId(),
                reservation.getFormattedStartDateTime(),
                reservation.getFormattedEndDateTime()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CancelReservationCommand)) {
            return false;
        }

        CancelReservationCommand otherCommand = (CancelReservationCommand) other;
        return reservationToCancel.equals(otherCommand.reservationToCancel);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("reservationToCancel", reservationToCancel)
                .toString();
    }
}
