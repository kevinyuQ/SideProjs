import java.util.HashSet;
import java.util.Random;
import java.util.List;

public class WordGenerator {
    private SimplePhonology phonology;
    private Random chooser;
    private HashSet<String> words;

    public WordGenerator(int seed) {
        SimplePhonology ph = new SimplePhonology(seed);
        
    }

    public WordGenerator(SimplePhonology phonology, int seed) {
        words = new HashSet<>();
        chooser = new Random(seed);
        this.phonology = phonology;
        //HashSet<String> phInv = phonology.getPhInv();
        List<String> consonants = phonology.getConsonants();
        List<String> vowels = phonology.getVowels();
        int syllableCount = 0;
        for (int i = 0; i < 20; i++) {
            String word = "";
            while (chooser.nextInt() % 4 != 0 && syllableCount < 4) {
                String nextSyllable = phonology.generateSyllable();
                if (word.indexOf("ʰ") == word.length() - 1 && nextSyllable.indexOf("ʰ") == 0) {
                    nextSyllable = vowels.get(Math.abs(chooser.nextInt()) % vowels.size()) + nextSyllable;
                }
                word = word + nextSyllable;
                syllableCount++;
            }
            words.add(word);
            syllableCount = 0;
        }
    }

    /**
     * This method returns the set of all words created so far.
     * @return HashSet containing all the words in the language.
     */
    public HashSet<String> getWords() {
        return words;
    }
}
