import java.util.HashMap;

public class Consonant {
    private String voicing;
    private String poa;
    private String moa;
    private static final String IPA =
            "pb0000td00ʈɖcɟkgqɢ00ʔ0" +
                    "m0ɱ000n000ɳ0ɲ0ŋ0ɴ00000" +
                    "ʙ00000r000ɽ00000000000"  +
                    "ɸβfvθðszʃʒʂʐçʝxɣχʁħʕhɦ" +
                    "000000ɬɮ00000000000000" +
                    "000ʋ000ɹ000ɻ0j0ɰ000000" +
                    "0000000l000ɭ0ʎ0ʟ000000";
                    /*"iyɨʉɯu" +
                    "ɪʏ000ʊ" +
                    "eøɘɵɤo" +
                    "00ə000" +
                    "ɛœɜɞʌɔ" +
                    "æ0ɐ000" +
                    "aɶ00ɑɒ";*/

    public Consonant(String ipa) {
        String description = ipaConsToDesc(ipa);
        String[] descriptors = description.split(description);
        this.voicing = descriptors[0];
        this.poa = descriptors[1];
        this.moa = descriptors[2];
    }

    public Consonant(String voicing, String poa, String moa) {
        this.voicing = voicing;
        this.poa = poa;
        this.moa = moa;
    }

    /**
     * Method for getting the voicing (voiced or voiceless) of a consonant.
     * @return consonant's voicing
     */
    public String getVoicing() {
        return voicing;
    }

    /**
     * Method for getting a consonant's place of articulation.
     * @return consonant's place of articulation
     */
    public String getPoa() {
        return poa;
    }

    /**
     * Method for getting a consonant's manner of articulation.
     * @return consonant's manner of articulation
     */
    public String getMoa() {
        return moa;
    }

    private String ipaConsToDesc(String ipaC) {
        int index = IPA.indexOf(ipaC);
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
