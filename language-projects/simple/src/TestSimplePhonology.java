import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.HashSet;

public class TestSimplePhonology {
    @Test (timeout = 1000)
    public void testIPAToDesc() {
        SimplePhonology test = new SimplePhonology(3);
        HashSet<String> actual = test.ipaToDesc("f");
        for (String str : actual) System.out.println(str);
        assertTrue(actual.contains("Voiceless"));
        assertTrue(actual.contains("Labiodental"));
        assertTrue(actual.contains("Fricative"));
        assertTrue(actual.size() == 3);

        HashSet<String> actual2 = test.ipaToDesc("a");
        for (String str : actual2) System.out.println(str);
    }

    /**
     * For this test, try creating a phonemic inventory for a language. Make
     * sure that the phonemic inventory is what you would expect it to be.
     * Create multiple phonemic inventories, and use multiple different seeds.
     *
     * Here are seeds that I think are kinda cool:
     * 1234, 36, 3342 (<--)
     */
    @Test (timeout = 1000)
    public void testPhonemicInventory() {
        SimplePhonology tester = new SimplePhonology(1); // I kinda like 1234's phonemic inv.
        String[][] consonants = tester.consonantGrid();
        //DOES NOT INCLUDE ASPIRATED CONSONANTS
        for (int i = 0; i < consonants.length; i++) {
            for (int j = 0; j < consonants[0].length; j++) {
                System.out.print(consonants[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        String[][] vowels = tester.vowelGrid();
        for (String[] row : vowels) {
            for (String elem : row) {
                System.out.print(elem);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("ASPIRATED STOPS");
        HashSet<String> phInv = tester.getPhInv();
        for (String p : phInv) {
            if (p.contains("ʰ")) {
                System.out.print(p + " ");
            }
        }
    }

    @Test (timeout = 1000)
    public void testSameSeedSameInv() {
        SimplePhonology test = new SimplePhonology(413);
        SimplePhonology testDup = new SimplePhonology(413);
        assertEquals(test.getPhInv(), testDup.getPhInv());
        assertArrayEquals(test.consonantGrid(), testDup.consonantGrid());
        assertArrayEquals(test.vowelGrid(), testDup.vowelGrid());
    }

    public static void main(String[] args) {
        /*for (int i = 0; i < 10; i++) {
            Random testRandom = new Random(413);
            System.out.println(testRandom.nextInt());
            Random testRandomDup = new Random(413);
            System.out.println(testRandomDup.nextInt());
        }*/
        Random testRandom = new Random(413);
        System.out.println(testRandom.nextInt());
        Random testRandomDup = new Random(413);
        System.out.println(testRandomDup.nextInt());
    }
}
