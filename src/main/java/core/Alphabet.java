package core;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Interface for Alphabets
 *
 * @author Maxim Nacu
 */

@FunctionalInterface
public interface Alphabet {

    List<String> allLetters();

    default List<String> availableLetters(Set<String> notAvailableLetters) {
        Set<String> upperCaseNotAvaLetters = notAvailableLetters.stream().map(String::toUpperCase).collect(Collectors.toSet());
        return allLetters().stream().filter(letter -> !upperCaseNotAvaLetters.contains(letter)).collect(Collectors.toList());
    }

}
