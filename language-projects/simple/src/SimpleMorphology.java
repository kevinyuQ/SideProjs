import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

/**
 * For simplicity, stick with SOV language with suffixes only.
 */
public class SimpleMorphology {
    SimplePhonology phonology;
    Random chooser;
    HashMap<String, String> affixes;
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
        this.affixes = new HashMap<>();
        this.phonology = phonology;
        chooser = new Random(seed);
        this.phInv = phonology.getPhInv();
        decideAffixes();
        /*String affix = phonology.generateSyllable(); //For now, for simplicity -- keep them as suffixes
        while (affixes.keySet().contains(affix)) {
            affix = affix + phonology.generateSyllable();
        }
        Double probability = 1.00;
        for (int i = 0; i < CASES.length; i++) {
            if (i > 3) {
                probability -= 0.08;
            }
            int x = Math.abs(chooser.nextInt()) % 100;
            if (x < probability * 100) {
                affixes.put(affix, CASES[i]);
            }
        }*/
    }

    /**
     * This method decides which affixes to include. It guarantees that nominative, accusative, and genitive
     * get used. For the next noun cases, the chances that those cases get used is decreased. This is based on
     * the noun case hierarchy: https://en.wikipedia.org/wiki/Case_hierarchy
     */
    private void decideAffixes() {
        /*String affix = phonology.generateSyllable(); //For now, for simplicity -- keep them as suffixes
        while (affixes.keySet().contains(affix)) {
            affix = affix + phonology.generateSyllable();
        }
        Double probability = 1.00;*/
        for (int i = 0; i < CASES.length; i++) {
            String affix = phonology.generateSyllable();
            while (affixes.keySet().contains(affix)) {
                affix = affix + phonology.generateSyllable();
            }
            Double probability = 1.00;
            if (i > 3) {
                probability -= 0.16;
            }
            int x = Math.abs(chooser.nextInt()) % 100;
            if (x < probability * 100) {
                affixes.put(affix, CASES[i]);
            } else if (x > probability * 100) {
                break;
            }
        }
    }

    /**
     * This method adds a case affix onto a given word depending on what the desired noun case is.
     * @param noun
     * @param nounCase
     * @return
     */
    public String addAffix(String noun, String nounCase) {
        String affix = affixes.get(nounCase);
        return noun + affix; //FOR SIMPLICITY: USE SUFFIXES ONLY
    }

    /**
     * This method returns the HashMap of all of the language's affixes.
     * @return a hash map representing the language's affixes
     */
    public HashMap<String, String> getAffixes() {
        return affixes;
    }
}
