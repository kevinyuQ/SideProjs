import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class TestSimpleMorphology {

    @Test (timeout = 1000)
    public void testDecideAffixes() {
        SimplePhonology sp = new SimplePhonology(312);
        SimpleMorphology sm = new SimpleMorphology(312, sp);
        HashMap<String, String> suffixes = sm.getAffixes();
        for (String suffix : suffixes.keySet()) {
            System.out.println(suffix + " -- " + suffixes.get(suffix));
        }
    }

    @Test (timeout = 1000)
    public void testSameSeedSameSuffixes() {
        SimpleMorphology sm = new SimpleMorphology(3, new SimplePhonology(3));
        SimpleMorphology smDup = new SimpleMorphology(3, new SimplePhonology(3));
        assertEquals(sm.getAffixes(), smDup.getAffixes());
    }

    @Test (timeout = 1000)
    public void testAddAffix() {
        SimpleMorphology sm = new SimpleMorphology(312, new SimplePhonology(312));
        HashMap<String, String> affixes = sm.getAffixes();
        for (String affix : affixes.keySet()) {
            System.out.println(affix + " -- " + affixes.get(affix));
        }
        WordGenerator wg = new WordGenerator(new SimplePhonology(312), 312);
        HashSet<String> words = wg.getWords();
        ArrayList<String> wordList = new ArrayList<>();
        for (String w : words) {
            wordList.add(w);
        }
        String w = wordList.get(2);
        for (String nounCase : affixes.keySet()) {
            System.out.println(sm.addAffix(w, nounCase) + ": " + w + "-" + nounCase);
        }
    }
}
