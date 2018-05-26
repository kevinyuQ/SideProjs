import java.util.*;

public class SimplePhonology {
    Random chooser;
    private HashSet<String> phInv = new HashSet<>();
    private Double SEED;
    private boolean voiced;
    private HashSet<String> poas = new HashSet<>();
    private int aspiration;
    private String syllStruct;

    // Making this was so tedious -_- ... Maybe there was a smarter way.
    // 0 represents a blank spot.
    private static final String[][] IPACHART = new String[][]{
            //Consonants start at row 0
            {"p", "b", "0", "0", "0", "0", "t", "d", "0", "0", "ʈ", // last element here is index 10
                    "ɖ", "c", "ɟ", "k", "g", "q", "ɢ", "0", "0", "ʔ", "0"},
            {"0", "m", "0", "ɱ", "0", "0", "0", "n", "0", "0", "0",
                    "ɳ", "0", "ɲ", "0", "ŋ", "0", "ɴ", "0", "0", "0", "0"},
            {"0", "ʙ", "0", "0", "0", "0", "0", "r", "0", "0", "0",
                    "ɽ", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "ⱱ", "0", "0", "0", "ɾ", "0", "0", "0",
                    "ɽ", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"ɸ", "β", "f", "v", "θ", "ð", "s", "z", "ʃ", "ʒ", "ʂ",
                    "ʐ", "ç", "ʝ", "x", "ɣ", "χ", "ʁ", "ħ", "ʕ", "h", "ɦ"},
            {"0", "0", "0", "0", "0", "0", "ɬ", "ɮ", "0", "0", "0",
                    "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "ʋ", "0", "0", "0", "ɹ", "0", "0", "0",
                    "ɻ", "0", "j", "0", "ɰ", "0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "0", "0", "0", "0", "l", "0", "0", "0",
                    "ɭ", "0", "ʎ", "0", "ʟ", "0", "0", "0", "0", "0", "0"},
            //Vowels start at row 8
            {"i", "y", "0", "0", "ɨ", "ʉ", "0", "0", "ɯ", "u"},
            {"0", "0", "ɪ", "ʏ", "0", "0", "0", "ʊ", "0", "0"},
            {"e", "ø", "0", "0", "ɘ", "ɵ", "0", "0", "ɤ", "o"},
            {"0", "0", "0", "0", "ə", "0", "0", "0", "0", "0"},
            {"ɛ", "œ", "0", "0", "ɜ", "ɞ", "0", "0", "ʌ", "ɔ"},
            {"æ", "0", "0", "0", "ɐ", "0", "0", "0", "0", "0"},
            {"a", "ɶ", "0", "0", "0", "0", "0", "0", "ɑ", "ɒ"}
    };

    public SimplePhonology(int seed) {
        fillBase(); // this is foundation
        this.chooser = new Random(seed);
        decideCharacterisitics(); // now add in the extra features
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
        phInv.add("p");
        phInv.add("t");
        phInv.add("k");
        phInv.add("m");
        phInv.add("n");
        phInv.add("ŋ");
        phInv.add("j");
        phInv.add("w");
        phInv.add("s");
        phInv.add("i");
        phInv.add("u");
        phInv.add("a");
    }

    /**
     * This method decides the characteristics of the conlang.
     * Decides the following characteristics:
     * 1. Has voiced consonants (voiced stops, voiced affricates, and voiced fricatives)
     * 2. Has aspirated stops
     * 3. Syllable structure
     * 4. Which places of articulation to include besides those in the base inventory.
     */
    private void decideCharacterisitics() {
        decideExtraPOAs();
        decideVoiced();
        decideAspirated();
        decideSyllStruct();
        addExtra();
    }

    /**
     * FIRST
     * This method decides which extra places of articulation to include.
     * (First try) Decides among the following place"s of articulation:
     * retroflex, palatal, and uvular.
     */
    private void decideExtraPOAs() {
        String[] options = {"Retroflex", "Palatal", "Uvular"};
        while (chooser.nextInt() % 2 != 0) {
            poas.add(options[Math.abs(chooser.nextInt()) % 3]);
        }
    }

    /**
     * SECOND
     * This method decides whether or not to include voiced stops and fricatives.
     */
    private void decideVoiced() {
        voiced = chooser.nextBoolean();
    }

    /**
     * THIRD
     * This method decides whether or not to include aspirated plosives.
     */
    private void decideAspirated() {
        /*
        OPTIONS:
        voiceless unaspirated and voiced unaspirated (0 - 3)
        voiceless aspirated and voiced unaspirated (4 - 7)
        voiceless aspirated and voiced aspirated (8 - 9)
         */
        aspiration = chooser.nextInt() % 10;
    }

    /**
     * FOURTH
     * This method decides what the conlang"s syllable structure will be. For simplicity,
     * syllables must have a vowel nucleus and at least on consonant.
     * Options include: CV, CVC, CLVC (L = liquid), CVCC
     */
    private void decideSyllStruct() {
        String[] options = {"CV", "CVC"};
        syllStruct = options[Math.abs(chooser.nextInt()) % 2]; // (???) Maybe use hash map for syllable structure
    }

    /**
     * FIFTH
     * This method adds the extra phonemes that were not originally included in the intial phonemic
     * inventory.
     */
    private void addExtra() {
        for (String poa : poas) {
            addExtraConsonants(poa);
        }
        addVowels();
    }

    /**
     * This method adds extra consonants for a particular place of articulation.
     *
     * @param poa
     */
    private void addExtraConsonants(String poa) {
        HashMap<String, Integer> poaToIndex = new HashMap<>();
        // Check these numbers
        poaToIndex.put("Bilabial", 0);
        poaToIndex.put("Alveolar", 6);
        poaToIndex.put("Retroflex", 10);
        poaToIndex.put("Palatal", 12);
        poaToIndex.put("Velar", 14);
        poaToIndex.put("Uvular", 16);
        addConsonants(poaToIndex.get(poa));
    }

    /**
     * This method adds the retroflex consonants into the phonemic inventory.
     * Start at index 11 in a row of the ipa chart.
     */
    private void addConsonants(int poaIndex) {
        int index = poaIndex;
        for (int i = 0; i < 7; i++) {
            if (i == 1 || i == 2 || i == 3 || i == 6 || i == 7) {
                index = poaIndex + 1;
            } else {
                index = poaIndex;
            }
            Random probabilityChooser = new Random();
            int probability = 100;
            if (i > 0 && (poaIndex == 0 || poaIndex == 6 || poaIndex == 14)) {
                probability -= Math.abs(probabilityChooser.nextInt()) % 150;
            }
            if (!IPACHART[i][index].equals("0") && probability > 50) {
                phInv.add(IPACHART[i][index]);
                if (voiced) {
                    phInv.add(IPACHART[i][poaIndex + 1]);
                }
                if (aspiration >= 4 && aspiration <= 7 && i == 0) {
                    phInv.add(IPACHART[i][poaIndex] + "ʰ");
                } else if (aspiration >= 8 && aspiration <= 9 && i == 0) {
                    phInv.add(IPACHART[i][poaIndex] + "ʰ");
                    if (voiced) {
                        phInv.add(IPACHART[i][poaIndex + 1] + "ʰ");
                    }
                }
            }
        }
    }

    /**
     * Adds new vowels into the phonemic inventory.
     */
    private void addVowels() {
        int withoutNewVowelsSize = phInv.size();
        while (chooser.nextInt() % 6 != 0 || phInv.size() < withoutNewVowelsSize + 3) {
            int vowelRow = chooser.nextInt(7) + 8;
            int vowelCol = chooser.nextInt(10);
            String newVowel = IPACHART[vowelRow][vowelCol];
            if (!newVowel.equals("0")) {
                phInv.add(newVowel);
            }
        }
    }

    HashSet<String> ipaToDesc(String ipa) {
        if (locate(ipa)[0] < 8) {
            return ipaToDescCons(ipa);
        } else {
            return ipaToDescVowels(ipa);
        }
    }

    /**
     * This method converts an ipa symbol for a consonant into a full description,
     * e.g. g --> voiced velar stop
     *
     * @param ipa
     * @return
     */
    private HashSet<String> ipaToDescCons(String ipa) {
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

    private HashSet<String> ipaToDescVowels(String ipa) {
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
        description.add("Vowel");
        return description;
    }

    /**
     * For a particular speech sound ipa, this method returns an array giving the
     * row and column in which that ipa symbol appears.
     *
     * @param ipa the speech sound to be located
     * @return an array, first element is row, second element is column
     */
    private int[] locate(String ipa) {
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

    /**
     * This method creates a syllable based on the consonants and vowels of the language
     * and the language's syllable structure rule.
     * @param consonants the set of all consonants
     * @param vowels the set of all vowel.
     * @return a string representing a syllable conforming to the language's syllable structure rule.
     */
    String generateSyllable(List<String> consonants, List<String> vowels) {
        //String syllStruct = phonology.getSyllStruct();
        int cIndex = Math.abs(chooser.nextInt()) % consonants.size();
        int vIndex = Math.abs(chooser.nextInt()) % vowels.size();
        String syllable = consonants.get(cIndex) + vowels.get(vIndex);
        if (syllStruct.equals("CVC")) {
            String newConsonant = consonants.get(Math.abs(chooser.nextInt()) % consonants.size());
            while (ipaToDesc(newConsonant).contains("Approximant")) {
                newConsonant = consonants.get(Math.abs(chooser.nextInt()) % consonants.size());
            }
            syllable = syllable + newConsonant;
        }
        return syllable;
    }

    /**
     * Returns the language's phonemic inventory.
     *
     * @return the phonemic inventory.
     */
    public HashSet<String> getPhInv() {
        return phInv;
    }

    /**
     * This method will be used for word generation. It returns the language's
     * syllable structure.
     * @return a string representing the language's syllable structure.
     */
    public String getSyllStruct() {
        return syllStruct;
    }

    public List<String> getConsonants() {
        List<String> consonants = new ArrayList<>();
        for (String p : phInv) {
            if (!ipaToDesc(p).contains("Vowel")) {
                consonants.add(p);
            }
        }
        return consonants;
    }

    public List<String> getVowels() {
        List<String> vowels = new ArrayList<>();
        for (String p : phInv) {
            if (ipaToDesc(p).contains("Vowel")) {
                vowels.add(p);
            }
        }
        return vowels;
    }

    /**
     * This method is more for the purpose of testing. It takes the phonemic inventory and creates
     * a chart similar to the IPACHART above. This chart contains the consonants.
     *
     * @return a 2d representation of the language's consonant inventory.
     */
    String[][] consonantGrid() {
        String[][] cGrid = new String[8][22];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < IPACHART[i].length; j++) {
                if (phInv.contains(IPACHART[i][j])) {
                    cGrid[i][j] = IPACHART[i][j];
                } else {
                    cGrid[i][j] = "0";
                }
            }
        }
        return cGrid;
    }

    /**
     * This method is more for the purpose of testing. This method returns the vowels in a grid.
     * @return a 2d representation of the vowels in the language.
     */
    String[][] vowelGrid() {
        String[][] vGrid = new String[7][10];
        for (int i = 8; i < 15; i++) {
            for (int j = 0; j < IPACHART[9].length; j++) {
                if (phInv.contains(IPACHART[i][j])) {
                    vGrid[i - 8][j] = IPACHART[i][j];
                } else {
                    vGrid[i - 8][j] = "0";
                }
            }
        }
        return vGrid;
    }
}
