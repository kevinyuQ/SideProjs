import java.util.HashSet;
import java.util.Random;
import java.util.List;

public class WordGenerator {
    SimplePhonology phonology;
    Random chooser;
    HashSet<String> words;

    public WordGenerator(int seed) {
        chooser = new Random(seed);
        phonology = new SimplePhonology(seed);
        //HashSet<String> phInv = phonology.getPhInv();
        List<String> consonants = phonology.getConsonants();
        List<String> vowels = phonology.getVowels();
        String syllStruct = phonology.getSyllStruct();
        if (syllStruct.equals("CV")) {
            int i = chooser.nextInt() % consonants.size();
            consonants.get(i);
            i = chooser.nextInt() % vowels.size();
        }
    }

    /**
     * This method creates a syllable based on the consonants and vowels of the language
     * and the language's syllable structure rule.
     * @param consonants the set of all consonants
     * @param vowels the set of all vowels
     * @param chooser a Random instance that chooses which consonants and vowels to use
     *                (pseudo-random)
     * @return a string representing a syllable conforming to the language's syllable structure rule.
     */
    private String generateSyllable(List<String> consonants, List<String> vowels, Random chooser) {
        String syllStruct = phonology.getSyllStruct();
        int cIndex = chooser.nextInt() % consonants.size();
        int vIndex = chooser.nextInt() % vowels.size();
        String syllable = consonants.get(cIndex) + consonants.get(vIndex);
        if (syllStruct.equals("CVC")) {
            syllable = syllable + consonants.get(chooser.nextInt() % consonants.size());
        }
        return syllable;
    }
}
