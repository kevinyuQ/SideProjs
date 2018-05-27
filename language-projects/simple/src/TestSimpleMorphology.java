import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

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
}
