import java.util.*;

/*
 * InMemory Dictionary for english words
 *
 * @author Maxim Nacu
 */

public class InMemoryDictionary implements Dictionary {
    private static final Map<String, List<String>> wordsWithHints = new HashMap<String, List<String>>() {{
        put("CAR", Arrays.asList("it has wheels", "can be steered", "seen all over the world"));
        put("PRINCESS", Arrays.asList("a king is her relative", "stuck ina  castle most of the time", "disney loves them"));
        put("ALADDIN", Arrays.asList("used to have his own Genie", "likes his monkey", "married to a disney princess"));
    }};

    @Override
    public Map<String, List<String>> getWordsWithHints() {
        return wordsWithHints;
    }

    @Override
    public String random() {
        Random generator = new Random();
        Object[] allWords = getWordsWithHints().keySet().toArray();
        return String.valueOf(allWords[generator.nextInt(getWordsWithHints().size())]);
    }

    @Override
    public List<String> hintsFor(String word) {
        return getWordsWithHints().get(word);
    }

}
