import java.util.*;

public class SimplePhonology {
    private List<Character> phInv;
    private Double SEED;
    private List<String> secArts;

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
        decideCharacterisitics();

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
        decideExtraPOAs();
        decideVoiced(chooser);
        decideAspirated();
        decideSyllStruct();
    }

    /**
     * This method decides whether or not to include voiced stops and fricatives.
     */
    private void decideVoiced(Random chooser) {
        if (chooser.nextBoolean()) {
            for (int i = 0; i < phInv.size(); i++) {
                String phoneme = phInv.get(i);
                if (ipaToDescCons(phoneme).contains("Stop")
                        && !ipaToDescCons(phoneme).contains("Voiced")) {
                    int index = IPA.indexOf(phoneme);
                    String newPh = Character.toString(IPA.charAt(index + 1));
                    phInv.add(index + 1, newPh);
                }
            }
        }

    }

    /**
     * This method decides what the conlang's syllable structure will be. For simplicity,
     * syllables must have a vowel nucleus and at least on consonant.
     * Options include: CV, CVC, CLVC (L = liquid), CVCC
     */
    private void decideSyllStruct() {

    }

    /**
     * This method decides which extra places of articulation to include.
     * (First try) Decides among the following place's of articulation:
     * retroflex, palatal, and uvular.
     */
    private void decideExtraPOAs() {

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
