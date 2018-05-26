import java.util.HashSet;
import java.util.Random;

public class SimpleMorphology {
    Random chooser;
    /*
     Case hierarchy: nominative → accusative or ergative → genitive →
                     dative → locative or prepositional → ablative and/or instrumental → others.
     */
    private static final String[] CASES = new String[]{
            "Nominative", "Accusative", "Genitive", "Dative", "Locative", "Ablative", "Instrumental",
            "Vocative"
    };
    HashSet<String> phInv;

    public SimpleMorphology(int seed, SimplePhonology phonology) {
        chooser = new Random(seed);
        this.phInv = phonology.getPhInv();
        //WordGenerator wg = new
    }

}
