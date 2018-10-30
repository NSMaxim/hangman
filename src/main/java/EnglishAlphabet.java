import java.util.Arrays;
import java.util.List;

/*
 * English Alphabet
 *
 * @author Maxim Nacu
 */

public class EnglishAlphabet implements Alphabet {

    private static List<String> letters = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    @Override
    public List<String> allLetters() {
        return letters;
    }

}
