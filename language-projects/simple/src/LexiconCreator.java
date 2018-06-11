import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

/** NOTE: this class handles the task of taking words and assigning them meaning.
 *  The creation of these words is the job of the WordGenerator class.
 */
public class LexiconCreator {
    HashMap<String, String> formToMeaning;
    private HashSet<String> nouns;
    private HashSet<String> verbs;

    public LexiconCreator(WordGenerator wg) {
        HashSet<String> words = wg.getWords();

        nouns.add("I");
        nouns.add("you");
        nouns.add("he");
        nouns.add("she");
        nouns.add("they");
        nouns.add("dog");
        nouns.add("wolf");
        nouns.add("tree");

        verbs.add("see");
        verbs.add("eat");
        verbs.add("touch");
    }

    public void printLexicon() {
        for (String key : formToMeaning.keySet()) {
            System.out.println(key + " : " + formToMeaning.get(key));
        }
    }
}
