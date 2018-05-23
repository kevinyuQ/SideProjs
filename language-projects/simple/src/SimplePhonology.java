import java.util.*;

public class SimplePhonology {
    private HashSet<Character> phInv;
    private Double SEED;
    private boolean voiced;
    private HashSet<String> poas;

    // Making this was so tedious -_- ... Maybe there was a smarter way.
    private static final Character[][] IPACHART = new Character[][]{
            {'p', 'b', '0', '0', '0', '0', 't', 'd', '0', '0', 'ʈ', 'ɖ', 'c', 'ɟ', 'k', 'g', 'q', 'ɢ', '0', '0', 'ʔ', '0'},
            {'m', '0', 'ɱ', '0', '0', '0', 'n', '0', '0', '0', 'ɳ', '0', 'ɲ', '0', 'ŋ', '0', 'ɴ', '0', '0', '0', '0', '0'},
            {'ʙ', '0', '0', '0', '0', '0', 'r', '0', '0', '0', 'ɽ', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'ɸ', 'β', 'f', 'v', 'θ', 'ð', 's', 'z', 'ʃ', 'ʒ', 'ʂ', 'ʐ', 'ç', 'ʝ', 'x', 'ɣ', 'χ', 'ʁ', 'ħ', 'ʕ', 'h', 'ɦ'},
            {'0', '0', '0', '0', '0', '0', 'ɬ', 'ɮ', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', 'ʋ', '0', '0', '0', 'ɹ', '0', '0', '0', 'ɻ', '0', 'j', '0', 'ɰ', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', 'l', '0', '0', '0', 'ɭ', '0', 'ʎ', '0', 'ʟ', '0', '0', '0', '0', '0', '0'},
            {'i', 'y', '0', '0', 'ɨ', 'ʉ', '0', '0', 'ɯ', 'u'},
            {'0', '0', 'ɪ', 'ʏ', '0', '0', '0', 'ʊ', '0', '0'},
            {'e', 'ø', '0', '0', 'ɘ', 'ɵ', '0', '0', 'ɤ', 'o'},
            {'0', '0', '0', '0', 'ə', '0', '0', '0', '0', '0'},
            {'ɛ', 'œ', '0', '0', 'ɜ', 'ɞ', '0', '0', 'ʌ', 'ɔ'},
            {'æ', '0', '0', '0', 'ɐ', '0', '0', '0', '0', '0'},
            {'a', 'ɶ', '0', '0', '0', '0', '0', '0', 'ɑ', 'ɒ'}
    };

    private static final String IPA =
            "pb0000td00ʈɖcɟkgqɢ00ʔ0" +
                    "m0ɱ000n000ɳ0ɲ0ŋ0ɴ00000" +
                    "ʙ00000r000ɽ00000000000"  +
                    "ɸβfvθðszʃʒʂʐçʝxɣχʁħʕhɦ" +
                    "000000ɬɮ00000000000000" +
                    "000ʋ000ɹ000ɻ0j0ɰ000000" +
                    "0000000l000ɭ0ʎ0ʟ000000" +
                    "iy00ɨʉ00ɯu" +
                    "00ɪʏ000ʊ00" +
                    "eø00ɘɵ00ɤo" +
                    "0000ə00000" +
                    "ɛœ00ɜɞ00ʌɔ" +
                    "æ000ɐ00000" +
                    "aɶ000000ɑɒ";

    public SimplePhonology(int seed) {
        fillBase(); // this is foundation
        Random chooser = new Random(seed);
        addExtra(chooser); // now add in the extra features
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
     * This method creates a phonetic inventory for the language. This method
     * destructively modifies the phonemic inventory after the base has been added in.
     * It chooses among the remaining places of articulation (labiodental, dental, retroflex, etc.).
     */
    private void addExtra(Random chooser) {
        decideCharacterisitics(chooser);

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
        decideAspirated();
        decideSyllStruct();
    }

    /**
     * This method decides which extra places of articulation to include.
     * (First try) Decides among the following place's of articulation:
     * retroflex, palatal, and uvular.
     */
    private void decideExtraPOAs(Random chooser) {
        String[] options = {"Retroflex", "Palatal", "Uvular"};
        while (chooser.nextInt() % 3 != 0) {
            poas.add(options[chooser.nextInt() % 3]);
        }
    }

    /**
     * This method decides whether or not to include voiced stops and fricatives.
     */
    private void decideVoiced(Random chooser) {
        voiced = chooser.nextBoolean();
        /*if (chooser.nextBoolean()) {*/
            /*for (int i = 0; i < phInv.size(); i++) {
                Character phoneme = phInv.get(i);
                if (ipaToDesc(phoneme).contains("Stop") || ipaToDesc(phoneme).contains("Fricative")
                        && !ipaToDescCons(phoneme).contains("Voiced")) {
                    int index = IPA.indexOf(phoneme);
                    Character newPh = IPA.charAt(index + 1);
                    phInv.add(index + 1, newPh);
                }
            }*/
        }

    }

    /**
     * This method decides what the conlang's syllable structure will be. For simplicity,
     * syllables must have a vowel nucleus and at least on consonant.
     * Options include: CV, CVC, CLVC (L = liquid), CVCC
     */
    private void decideSyllStruct(Random chooser) {
        String[] options = {"CV", "CVC"};
        syllStruct = options[chooser.nextInt() % 2]; // Maybe use hash map for syllable structure
    }

    /**
     * This method adds the retroflex consonants into the phonemic inventory.
     * Start at index 11 in a row of the ipa chart.
     */
    private void addRetroflexes() {
        for (int i = 0; i < 7; i++) {
            phInv.add(IPACHART[i][11]);
            if (voiced) {
                phInv.add(IPACHART[i][12]);
            }
        }
    }

    private HashSet<String> ipaToDesc(Character ipa) {
        if (IPA.indexOf(ipa) < 22 * 7) {
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
        int index = IPA.indexOf(ipa);
        String[] moas = new String[]{
                "Stop", "Nasal", "Trill",
                "Tap", "Fricative", "Lateral Fricative",
                "Lateral Fricative", "Approximant", "Lateral Approximant"
        };
        String[] poas = new String[]{
                "Bilabial", "Labiodental", "Dental",
                "Alveolar", "Post-Alveolar", "Retroflex",
                "Palatal", "Velar", "Uvular", "Pharyngeal",
                "Glottal"
        };
        int counter = 0;
        while (counter * 22 <= index) {
            counter += 1;
        }
        counter -= 1;
        String manner = moas[counter]; //moas.get(counter);
        int counter2 = 0;
        while (counter2 * 2 <= index - counter * 22) {
            counter2 += 1;
        }
        counter2 -= 1;
        String place = poas[counter2]; //poas.get(counter2);
        String voicing;
        if (counter2 % 2 == 0) {
            voicing = "Voiceless";
        } else {
            voicing = "Voiced";
        }
        HashSet<String> description = new HashSet<>();
        description.add(voicing);
        description.add(place);
        description.add(manner);
        return description;
    }

    private HashSet<String> ipaToDescVowels(Character ipa) {
        int index = IPA.indexOf(ipa);
        String[] heights = new String[]{
                "High", "Near-close", "Close-mid", "Mid",
                "Open-mid", "Near-open", "Open"
        };
        String[] frontnesses = new String[]{
                "Front", "Near-front", "Central", "Near-back",
                "Back"
        };
        index -= 154; // CHECK IF THIS NUMBER IS CORRECT
        int counter = 0;
        while (counter * 10 <= index) {
            counter++;
        }
        counter -= 1;
        String height = heights[counter];
        int counter2 = 0;
        while (counter2 * 2 <= index - counter * 10) {
            counter++;
        }
        counter2 -= 1;
        String frontness = frontnesses[counter2];
        String roundness;
        if ((index - counter * 10) % 2 == 0) {
            roundness = "Rounded";
        } else {
            roundness = "Unrounded";
        }
        HashSet<String> description = new HashSet<>();
        description.add(roundness);
        description.add(frontness);
        description.add(height);
        return description;
    }

}
