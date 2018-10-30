import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AlphabetTest {

    private Alphabet alphabet = new EnglishAlphabet();

    @Test
    public void alphabetLetters() {
        assertEquals(26, alphabet.allLetters().size());
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", StringUtils.join(alphabet.allLetters(), ""));
        Set<String> wrongLetters = new HashSet<>(Arrays.asList("B", "c", "d"));
        assertEquals("AEFGHIJKLMNOPQRSTUVWXYZ", StringUtils.join(alphabet.availableLetters(wrongLetters), ""));
    }

}
