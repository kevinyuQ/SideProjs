public class Vowel {
    private String height;
    private String frontness;
    private String roundedness;
    private String tenseness;
    private String nasality;
    private static final String ipaV = "iyɨʉɯu" +
            "ɪʏ000ʊ" +
            "eøɘɵɤo" +
            "00ə000" +
            "ɛœɜɞʌɔ" +
            "æ0ɐ000" +
            "aɶ00ɑɒ";

    public Vowel (String h, String fr, String round, String tense, String nas) {
        this.height = h;
        this.frontness = fr;
        this.roundedness = round;
        this.tenseness = tense;
        this.nasality = nas;
    }

    /**
     * Method for getting the height of a vowel.
     * @return the vowel's height
     */
    public String getHeight() {
        return height;
    }

    /**
     * Method for getting the frontness of a vowel.
     * @return the vowel's frontness
     */
    public String getFrontness() {
        return frontness;
    }

    /**
     * Method for getting the roundedness of a vowel.
     * @return vowel's roundedness
     */
    public String getRoundedness() {
        return roundedness;
    }

    /**
     * Method for getting the tenseness of a vowel.
     * @return vowel's tenseness
     */
    public String getTenseness() {
        return tenseness;
    }


    /**
     * Method for getting the nasality of a vowel.
     * @return vowel's nasality (oral or nasal)
     */
    public String getNasality() {
        return nasality;
    }
}
