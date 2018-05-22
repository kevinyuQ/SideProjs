import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SimplePhonology {
    private static final List<String> baseInv = new ArrayList<>();
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
                    "iyɨʉɯu" +
                    "ɪʏ000ʊ" +
                    "eøɘɵɤo" +
                    "00ə000" +
                    "ɛœɜɞʌɔ" +
                    "æ0ɐ000" +
                    "aɶ00ɑɒ";

    public SimplePhonology(int seed) {
        fillBase();
        Random chooser = new Random(seed);
        chooseSecArts(chooser);
    }

    /**
     * This method creates a phonemic inventory of sounds that must be included
     * in the languages. This phonemic inventory acts as the basis for the language.
     */
    private void fillBase() {
        baseInv.add("p");
        baseInv.add("t");
        baseInv.add("k");
        baseInv.add("m");
        baseInv.add("n");
        baseInv.add("ŋ");
        baseInv.add("j");
        baseInv.add("w");
        baseInv.add("s");
        baseInv.add("i");
        baseInv.add("u");
        baseInv.add("a");
    }

    /**
     * This method decides what secondary articulation to include. Aspiration is considered
     * as a type of secondary articulation.
     */
    private void chooseSecArts(Random chooser) {
        if (chooser.nextBoolean()) {
            secArts.add("ʰ");
        }
        if (chooser.nextInt() % 3 == 0) {
            secArts.add("ʷ");
        }
        if (chooser.nextInt() % (4 + secArts.size()) == 0) {
            secArts.add("ʲ");
        }
    }

    private String ipaToDescCons(String ipa) {
        int index = IPA.indexOf(ipa);
        HashMap<Integer, String> moas = new HashMap();
        moas.put(0, "Stop");
        moas.put(1, "Nasal");
        moas.put(2, "Trill");
        moas.put(3, "Tap");
        moas.put(4, "Fricative");
        moas.put(5, "Lateral Fricative");
        moas.put(6, "Approximant");
        moas.put(7, "Lateral Approximant");
        HashMap<Integer, String> poas = new HashMap<>();
        poas.put(0, "Bilabial");
        poas.put(1, "Labiodental");
        poas.put(2, "Dental");
        poas.put(3, "Alveolar");
        poas.put(4, "Post-Alveolar");
        poas.put(5, "Retroflex");
        poas.put(6, "Palatal");
        poas.put(7, "Velar");
        poas.put(8, "Uvular");
        poas.put(9, "Pharyngeal");
        poas.put(10, "Glottal");
        int counter = 0;
        while (counter * 22 <= index) {
            counter += 1;
        }
        counter -= 1;
        String manner = moas.get(counter);
        int counter2 = 0;
        while (counter2 * 2 <= index - counter * 22) {
            counter2 += 1;
        }
        counter2 -= 1;
        String place = poas.get(counter2);
        String voicing;
        if (counter2 % 2 == 0) {
            voicing = "voiceless";
        } else {
            voicing = "voiced";
        }
        String description = voicing + place + manner;
        return description;
    }
}
