import org.junit.Test;

import java.util.HashSet;

public class WordTester {

    @Test (timeout = 1000)
    public void testWordCreation() {
        SimplePhonology testPhonology = new SimplePhonology(422);
        WordGenerator wg = new WordGenerator(testPhonology, 422);
        HashSet<String> words = wg.getWords();
        for (String word : words) {
            System.out.println(word);
        }
    }
}
