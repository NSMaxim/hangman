import java.util.*;

public class BaseDictionaryImpl implements Dictionary {
    private static final Map<String, List<String>> wordsWithHints = new HashMap<String, List<String>>() {{
        put("CAR", Arrays.asList("it has wheels", "can be steered", "seen all over the world"));
    }};

    public Map<String, List<String>> getWordsWithHints() {
        return wordsWithHints;
    }

    public String random() {
        Random generator = new Random();
        Object[] allWords = getWordsWithHints().keySet().toArray();
        return String.valueOf(allWords[generator.nextInt(getWordsWithHints().size())]);
    }

    public List<String> hintsFor(String word) {
        return getWordsWithHints().get(word);
    }

}
