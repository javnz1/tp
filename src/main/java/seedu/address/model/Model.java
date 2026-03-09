package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.issue.IssueRecord;
import seedu.address.model.person.Person;
import seedu.address.model.room.Room;
import seedu.address.model.person.StudentId;
import seedu.address.model.reservation.Reservation;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true for rooms */
    Predicate<Room> PREDICATE_SHOW_ALL_ROOMS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    ReadOnlyUserPrefs getUserPrefs();

    GuiSettings getGuiSettings();

    void setGuiSettings(GuiSettings guiSettings);

    Path getAddressBookFilePath();

    void setAddressBookFilePath(Path addressBookFilePath);

    void setAddressBook(ReadOnlyAddressBook addressBook);

    ReadOnlyAddressBook getAddressBook();

    boolean hasPerson(Person person);

    void deletePerson(Person target);

    void addPerson(Person person);

    void setPerson(Person target, Person editedPerson);

    ObservableList<Person> getFilteredPersonList();

    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Returns true if a room with the same identity as {@code room} exists in the system. */
    boolean hasRoom(Room room);

    /** Adds the given room. */
    void addRoom(Room room);

    /** Returns an unmodifiable view of the filtered room list. */
    ObservableList<Room> getFilteredRoomList();

    void updateFilteredRoomList(Predicate<Room> predicate);

    boolean hasStudentId(StudentId studentId);

    boolean hasReservableItem(String resourceId);

    boolean hasConflictingReservation(Reservation reservation);

    Optional<Reservation> getConflictingReservation(Reservation reservation);

    void addReservation(Reservation reservation);

    ObservableList<Reservation> getReservationList();
  
    boolean hasIssuableItem(String itemId);
  
    boolean hasIssuedItem(String itemId);
  
    Optional<IssueRecord> getIssueRecordByItemId(String itemId);
  
    void addIssueRecord(IssueRecord issueRecord);
  
    ObservableList<IssueRecord> getIssueRecordList();

}
