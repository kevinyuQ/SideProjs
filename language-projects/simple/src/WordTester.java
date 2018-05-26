import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

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

    @Test (timeout = 1000)
    public void testSameSeedSameWords() {
        WordGenerator wg = new WordGenerator(new SimplePhonology(41313), 41313);
        WordGenerator wgDup = new WordGenerator(new SimplePhonology(41313), 41313);
        for (String word : wg.getWords()) {
            System.out.println(word);
        }
        for (String word : wgDup.getWords()) {
            System.out.println(word);
        }
        assertEquals(wg.getWords(), wgDup.getWords());
    }
}
