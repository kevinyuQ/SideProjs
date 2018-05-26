import java.util.HashSet;
import java.util.Random;
import java.util.List;

public class WordGenerator {
    private SimplePhonology phonology;
    private Random chooser;
    private HashSet<String> words;

    public WordGenerator(SimplePhonology phonology, int seed) {
        words = new HashSet<>();
        chooser = new Random(seed);
        this.phonology = phonology;
        //HashSet<String> phInv = phonology.getPhInv();
        List<String> consonants = phonology.getConsonants();
        List<String> vowels = phonology.getVowels();
        for (int i = 0; i < 20; i++) {
            String word = "";
            while (chooser.nextInt() % 3 != 0) {
                String nextSyllable = phonology.generateSyllable(consonants, vowels);
                if (word.indexOf("ʰ") == word.length() - 1 && nextSyllable.indexOf("ʰ") == 0) {
                    nextSyllable = vowels.get(Math.abs(chooser.nextInt()) % vowels.size()) + nextSyllable;
                }
                word = word + nextSyllable; //generateSyllable(consonants, vowels);
            }
            words.add(word);
        }
    }

    /**
     * This method creates a syllable based on the consonants and vowels of the language
     * and the language's syllable structure rule.
     * @param consonants the set of all consonants
     * @param vowels the set of all vowel.
     * @return a string representing a syllable conforming to the language's syllable structure rule.
     */
     /*String generateSyllable(List<String> consonants, List<String> vowels) {
        String syllStruct = phonology.getSyllStruct();
        int cIndex = Math.abs(chooser.nextInt()) % consonants.size();
        int vIndex = Math.abs(chooser.nextInt()) % vowels.size();
        String syllable = consonants.get(cIndex) + vowels.get(vIndex);
        if (syllStruct.equals("CVC")) {
            String newConsonant = consonants.get(Math.abs(chooser.nextInt()) % consonants.size());
            while (phonology.ipaToDesc(newConsonant).contains("Approximant")) {
                newConsonant = consonants.get(Math.abs(chooser.nextInt()) % consonants.size());
            }
            syllable = syllable + newConsonant;
        }
        return syllable;
    }*/

    /**
     * This method returns the set of all words created so far.
     * @return HashSet containing all the words in the language.
     */
    public HashSet<String> getWords() {
        return words;
    }
}
