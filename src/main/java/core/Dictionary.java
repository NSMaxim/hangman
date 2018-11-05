package core;

import java.util.List;
import java.util.Map;

/*
 * Interface for Dictionary
 *
 * @author Maxim Nacu
 */

public interface Dictionary {

    Map<String, List<String>> getWordsWithHints();

    String random();

    List<String> hintsFor(String word);
}
