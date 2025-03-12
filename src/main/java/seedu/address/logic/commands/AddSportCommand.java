package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Sport;

/**
 * Adds a sport to an existing contact in the address book.
 */
public class AddSportCommand extends Command {

    public static final String COMMAND_WORD = "addsport";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a sport to the contact identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) s/SPORT_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 s/Badminton";
    public static final String MESSAGE_SUCCESS = "Added sport: %1$s to contact: %2$s";
    public static final String MESSAGE_DUPLICATE_SPORT = "This sport already exists for the contact";

    private final int index;
    private final Sport sport;

    /**
     * Creates an AddSportCommand to add the specified sport to the person at the given index.
     *
     * @param index The index of the person in the filtered person list.
     * @param sport The sport to be added.
     */
    public AddSportCommand(int index, Sport sport) {
        requireNonNull(sport);
        this.index = index;
        this.sport = sport;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Retrieve the filtered person list
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index < 0 || index >= lastShownList.size()) {
            throw new CommandException("Invalid person index");
        }

        Person personToEdit = lastShownList.get(index);

        // Check if the sport is already present
        if (personToEdit.getSports().contains(sport)) {
            throw new CommandException(MESSAGE_DUPLICATE_SPORT);
        }

        // Create a new list of sports including the new sport.
        List<Sport> updatedSports = new ArrayList<>(personToEdit.getSports());
        updatedSports.add(sport);

        // Create a new Person with the updated sports list.
        Person editedPerson = createEditedPerson(personToEdit, updatedSports);
        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, sport.toString(), personToEdit.getName().fullName));
    }

    /**
     * Creates and returns a {@code Person} with an updated list of sports.
     *
     * @param personToEdit The person to be updated.
     * @param updatedSports The new list of sports.
     * @return A new Person object with the updated sports.
     */
    private static Person createEditedPerson(Person personToEdit, List<Sport> updatedSports) {
        // Assuming that the Person constructor has been updated to include a List<Sport> sports parameter.
        return new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                personToEdit.getTags(),
                updatedSports);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof AddSportCommand otherCommand)) {
            return false;
        }
        return index == otherCommand.index && sport.equals(otherCommand.sport);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("sport", sport)
                .toString();
    }
}
