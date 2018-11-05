package core;/*
 * Enum to represent current status of a Hangman game
 *
 * @author Maxim Nacu
 */

public enum HangmanStatus {
    IN_PROGRESS("In Progress"),
    WON("Won"),
    LOST("Lost");

    private final String value;

    HangmanStatus(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
