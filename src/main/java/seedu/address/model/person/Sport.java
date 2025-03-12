package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Sport associated with a Person in the address book.
 * Guarantees: sport name is present and not null.
 */
public class Sport {

    public final String sportName;

    /**
     * Constructs a {@code Sport}.
     *
     * @param sportName A valid sport name.
     */
    public Sport(String sportName) {
        requireNonNull(sportName);
        this.sportName = sportName;
    }

    @Override
    public String toString() {
        return sportName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Sport otherSport)) {
            return false;
        }
        return sportName.equals(otherSport.sportName);
    }
}
