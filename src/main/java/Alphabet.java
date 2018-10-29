import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Alphabet {

    private static List<String> letters = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    public List<String> allLetters() {
        return letters;
    }

    public List<String> availableLetters(List<String> notAvailableLetters) {
        notAvailableLetters.replaceAll(String::toUpperCase);
        return letters.stream().filter(string -> !notAvailableLetters.contains(string)).collect(Collectors.toList());
    }
}
