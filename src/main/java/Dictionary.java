import java.util.List;
import java.util.Map;

public interface Dictionary {

    public Map<String, List<String>> getWordsWithHints();

    public String random();

    public List<String> hintsFor(String word);

}
