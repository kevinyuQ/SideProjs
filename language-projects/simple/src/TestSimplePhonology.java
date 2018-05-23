import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

public class TestSimplePhonology {
    @Test (timeout = 1000)
    public void testIPAToDesc() {
        SimplePhonology test = new SimplePhonology(3);
        HashSet<String> actual = test.ipaToDesc('f');
        for (String str : actual) System.out.println(str);
        assertTrue(actual.contains("Voiceless"));
        assertTrue(actual.contains("Labiodental"));
        assertTrue(actual.contains("Fricative"));
        assertTrue(actual.size() == 3);

        HashSet<String> actual2 = test.ipaToDesc('a');
        for (String str : actual2) System.out.println(str);
    }
}
