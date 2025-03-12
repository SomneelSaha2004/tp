package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Sport;

/**
 * Jackson-friendly version of {@link Sport}.
 */
public class JsonAdaptedSport {

    private final String sportName;

    /**
     * Constructs a {@code JsonAdaptedSport} with the given sport details.
     */
    @JsonCreator
    public JsonAdaptedSport(@JsonProperty("sportName") String sportName) {
        this.sportName = sportName;
    }

    /**
     * Converts a given {@code Sport} into this class for Jackson use.
     */
    public JsonAdaptedSport(Sport source) {
        this.sportName = source.sportName; // or use a getter if available: source.getSportName()
    }

    /**
     * Converts this Jackson-friendly adapted sport object into the model's {@code Sport} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted sport.
     */
    public Sport toModelType() throws IllegalValueException {
        if (sportName == null) {
            throw new IllegalValueException("Sport's sportName field is missing!");
        }
        // Optionally, you can add further validations here if needed.
        return new Sport(sportName);
    }
}
