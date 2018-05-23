import java.util.*;

public class SimplePhonology {
    private HashSet<Character> phInv = new HashSet<>();
    private Double SEED;
    private boolean voiced;
    private HashSet<String> poas = new HashSet<>();
    private int aspiration;
    private String syllStruct;

    // Making this was so tedious -_- ... Maybe there was a smarter way.
    // 0 represents a blank spot.
    private static final Character[][] IPACHART = new Character[][]{
            //Consonants start at row 0
            {'p', 'b', '0', '0', '0', '0', 't', 'd', '0', '0', 'ʈ', // last element here is index 10
                    'ɖ', 'c', 'ɟ', 'k', 'g', 'q', 'ɢ', '0', '0', 'ʔ', '0'},
            {'0', 'm', '0', 'ɱ', '0', '0', '0', 'n', '0', '0', '0',
                    'ɳ', '0', 'ɲ', '0', 'ŋ', '0', 'ɴ', '0', '0', '0', '0'},
            {'0', 'ʙ', '0', '0', '0', '0', '0', 'r', '0', '0', '0',
                    'ɽ', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', 'ⱱ', '0', '0', '0', 'ɾ', '0', '0', '0',
                    'ɽ', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'ɸ', 'β', 'f', 'v', 'θ', 'ð', 's', 'z', 'ʃ', 'ʒ', 'ʂ',
                    'ʐ', 'ç', 'ʝ', 'x', 'ɣ', 'χ', 'ʁ', 'ħ', 'ʕ', 'h', 'ɦ'},
            {'0', '0', '0', '0', '0', '0', 'ɬ', 'ɮ', '0', '0', '0',
                    '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', 'ʋ', '0', '0', '0', 'ɹ', '0', '0', '0',
                    'ɻ', '0', 'j', '0', 'ɰ', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', 'l', '0', '0', '0',
                    'ɭ', '0', 'ʎ', '0', 'ʟ', '0', '0', '0', '0', '0', '0'},
            //Vowels start at row 8
            {'i', 'y', '0', '0', 'ɨ', 'ʉ', '0', '0', 'ɯ', 'u'},
            {'0', '0', 'ɪ', 'ʏ', '0', '0', '0', 'ʊ', '0', '0'},
            {'e', 'ø', '0', '0', 'ɘ', 'ɵ', '0', '0', 'ɤ', 'o'},
            {'0', '0', '0', '0', 'ə', '0', '0', '0', '0', '0'},
            {'ɛ', 'œ', '0', '0', 'ɜ', 'ɞ', '0', '0', 'ʌ', 'ɔ'},
            {'æ', '0', '0', '0', 'ɐ', '0', '0', '0', '0', '0'},
            {'a', 'ɶ', '0', '0', '0', '0', '0', '0', 'ɑ', 'ɒ'}
    };

    public SimplePhonology(int seed) {
        fillBase(); // this is foundation
        Random chooser = new Random(seed);
        decideCharacterisitics(chooser); // now add in the extra features
    }

    /**
     * This method creates a phonemic inventory of sounds that must be included
     * in the languages. This phonemic inventory acts as the basis for the language.
     */
    private void fillBase() {
        // First add initial poas
        poas.add("Bilabial");
        poas.add("Alveolar");
        poas.add("Velar");
        // Then initialize voicing.
        voiced = false;
        // Add the first phonemes
        phInv.add('p');
        phInv.add('t');
        phInv.add('k');
        phInv.add('m');
        phInv.add('n');
        phInv.add('ŋ');
        phInv.add('j');
        phInv.add('w');
        phInv.add('s');
        phInv.add('i');
        phInv.add('u');
        phInv.add('a');
    }

    /**
     * This method decides the characteristics of the conlang.
     * Decides the following characteristics:
     * 1. Has voiced consonants (voiced stops, voiced affricates, and voiced fricatives)
     * 2. Has aspirated stops
     * 3. Syllable structure
     * 4. Which places of articulation to include besides those in the base inventory.
     */
    private void decideCharacterisitics(Random chooser) {
        decideExtraPOAs(chooser);
        decideVoiced(chooser);
        decideAspirated(chooser);
        decideSyllStruct(chooser);
        addExtra(chooser);
    }

    /**
     * FIRST
     * This method decides which extra places of articulation to include.
     * (First try) Decides among the following place's of articulation:
     * retroflex, palatal, and uvular.
     */
    private void decideExtraPOAs(Random chooser) {
        String[] options = {"Retroflex", "Palatal", "Uvular"};
        while (chooser.nextInt() % 2 != 0) {
            poas.add(options[chooser.nextInt() % 3]);
        }
    }

    /**
     * SECOND
     * This method decides whether or not to include voiced stops and fricatives.
     */
    private void decideVoiced(Random chooser) {
        voiced = chooser.nextBoolean();
    }

    /**
     * THIRD
     * This method decides whether or not to include aspirated plosives.
     * @param chooser a pseudo-random generator that decides what will get chosen.
     */
    private void decideAspirated(Random chooser) {
        aspiration = chooser.nextInt() % 10;
    }

    /**
     * FOURTH
     * This method decides what the conlang's syllable structure will be. For simplicity,
     * syllables must have a vowel nucleus and at least on consonant.
     * Options include: CV, CVC, CLVC (L = liquid), CVCC
     */
    private void decideSyllStruct(Random chooser) {
        String[] options = {"CV", "CVC"};
        syllStruct = options[chooser.nextInt() % 2]; // (???) Maybe use hash map for syllable structure
    }

    /**
     * FIFTH
     * This method adds the extra phonemes that were not originally included in the intial phonemic
     * inventory.
     * @param chooser
     */
    private void addExtra(Random chooser) {
        for (String poa : poas) {
            addExtraConsonants(poa);
        }
        if (voiced) addVoiced();
        //if (aspiration) addAspirated();

    }

    private void addExtraConsonants(String poa) {
        HashMap<String, Integer> poaToIndex = new HashMap<>();
        // Check these numbers
        poaToIndex.put("Retroflex", 10);
        poaToIndex.put("Palatal", 12);
        poaToIndex.put("Uvular", 16);
        if (poa.equals("Retroflex")) {
            addRetroflexes();
        } else if (poa.equals("Palatal")) {
            addPalatals();
        } else if (poa.equals("Uvular")) {
            addUvulars();
        } else {
            return;
        }
    }

    /**
     * This method adds the retroflex consonants into the phonemic inventory.
     * Start at index 11 in a row of the ipa chart.
     */
    private void addRetroflexes(int poaIndex) {
        for (int i = 0; i < 7; i++) {
            if (!IPACHART[i][poaIndex].equals('0')) {
                //phInv.add(IPACHART[i][10]);
                phInv.add(IPACHART[i][poaIndex]);
                if (voiced) {
                    phInv.add(IPACHART[i][10]);
                }
            }
        }
    }

    /**
     * This method adds the palatal consonants, including the palatal stops,
     * palatal fricatives, etc.
     */
    private void addPalatals(){
        ;
    }

    /**
     * Adds voiced counterparts to voiceless consonants.
     */
    private void addVoiced() {
        return;
    }

    /**
     * This method adds the uvular consonants, including the uvular stops, etc.
     */
    private void addUvulars() {
        return;
    }

    HashSet<String> ipaToDesc(Character ipa) {
        if (locate(ipa)[0] < 8) {
            return ipaToDescCons(ipa);
        } else {
            return ipaToDescVowels(ipa);
        }
    }
    /**
     * This method converts an ipa symbol for a consonant into a full description,
     * e.g. g --> voiced velar stop
     * @param ipa
     * @return
     */
    private HashSet<String> ipaToDescCons(Character ipa) {
        int[] location = locate(ipa); //IPA.indexOf(ipa);
        String[] moas = new String[]{
                "Stop", "Nasal", "Trill",
                "Tap", "Fricative", "Lateral Fricative",
                "Approximant", "Lateral Approximant"
        };
        String[] poas = new String[]{
                "Bilabial", "Labiodental", "Dental",
                "Alveolar", "Post-Alveolar", "Retroflex",
                "Palatal", "Velar", "Uvular", "Pharyngeal",
                "Glottal"
        };
        String[] voicingOptions = new String[]{"Voiceless", "Voiced"};
        int row = location[0];
        int col = location[1];
        String manner = moas[row];
        String place = poas[col / 2];
        String voicing = voicingOptions[col % 2];
        HashSet<String> description = new HashSet<>();
        description.add(voicing);
        description.add(place);
        description.add(manner);
        return description;
    }

    private HashSet<String> ipaToDescVowels(Character ipa) {
        int[] location = locate(ipa); //IPA.indexOf(ipa);
        String[] heights = new String[]{
                "High", "Near-close", "Close-mid", "Mid",
                "Open-mid", "Near-open", "Open"
        };
        String[] frontnesses = new String[]{
                "Front", "Near-front", "Central", "Near-back",
                "Back"
        };
        String[] roundnessOptions = new String[]{"Unrounded", "Rounded"};
        int row = location[0];
        int col = location[1];
        String height = heights[row - 8];
        String frontness = frontnesses[col / 2];
        String roundness = roundnessOptions[col % 2];
        HashSet<String> description = new HashSet<>();
        description.add(roundness);
        description.add(frontness);
        description.add(height);
        return description;
    }

    /**
     * For a particular speech sound ipa, this method returns an array giving the
     * row and column in which that ipa symbol appears.
     * @param ipa the speech sound to be located
     * @return an array, first element is row, second element is column
     */
    private int[] locate(Character ipa) {
        int row = 0;
        int col = 0;
        for (int i = 0; i < IPACHART.length; i++) {
            int lastColumnNum = IPACHART[i].length;
            for (int j = 0; j < lastColumnNum; j++) {
                if (IPACHART[i][j].equals(ipa)) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        return new int[]{row, col};
    }
}
