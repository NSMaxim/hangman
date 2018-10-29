import com.sun.deploy.util.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class AlphabetTest {

    private Alphabet alphabet = new Alphabet();

    @Test
    public void alphabetLetters() {
        assertEquals(26, alphabet.allLetters().size());
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", StringUtils.join(alphabet.allLetters(), ""));
        assertEquals("AEFGHIJKLMNOPQRSTUVWXYZ", StringUtils.join(alphabet.availableLetters(Arrays.asList("B", "c", "d")), ""));
    }

}
