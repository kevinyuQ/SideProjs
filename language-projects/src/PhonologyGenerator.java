import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PhonologyGenerator {
    boolean hasVoiced;
    boolean hasVLAsp;
    boolean hasVoicedAsp;
    boolean hasSecArt; // if true, decide which secondary articulation to have
    List<String> secArts;
    List<String> extraPOAs;
    boolean morePOA; // more places of articulation are added (e.g. retroflex)
    boolean moreMOA; // more manners of articulation are added (e.g. trill)
    List<String> phInv;

    /**
     * Creates a phonemic inventory by choosing from the phonemes in the
     * International Phonetic Alphabet.
     * @return a list containing all of the phonemes for this constructed
     * language.
     */
    public List<String> makePhonemicInv() {
        decide();
        List<String> phInv= new ArrayList<String>(10);
        /* First choose stops; for this conlang, stops must include
           p, t, and k. */
        addStops(phInv);
        return phInv;
    }

    /**
     * This method decides what characteristics the language will have, e.g.
     * whether it has voiced consonants, whether it has aspirated stops, what
     * secondary articulation it has, etc.
     */
    public void decide() {
        Random chooser = new Random(413);
        hasVoiced = chooser.nextBoolean();
        hasVLAsp = ((chooser.nextInt() % 3) == 0); // 1/3 chance
        if (hasVoiced) {
            hasVoicedAsp = ((chooser.nextInt() % 10) == 0);
        }
        hasSecArt = ((chooser.nextInt() % 4) == 0);
        if (hasSecArt) {
            if (chooser.nextBoolean()) {
                secArts.add("ʰ");
            }
            if (chooser.nextBoolean()) {
                secArts.add("ʷ");
            }
            if ((chooser.nextInt() % secArts.size() + 2) == 0) {
                secArts.add("ʲ");
            }
            if ((chooser.nextInt() % secArts.size() + 4) == 0) {
                secArts.add("ˤ");
                phInv.add("ʕ");
            }
        }
    }

    /**
     * Destructively mutates phInv by adding in series of stop consonants.
     * @param phInv
     */
    private void addStops(List<String> phInv) {
        HashMap<String, String> extraStops = new HashMap<>();
        extraStops.put("Retroflex", "ʈ");
        extraStops.put("Palatal", "c");
        extraStops.put("Uvular", "q");
        extraStops.put("Glottal", "ʔ");
        phInv.add("p");
        phInv.add("t");
        phInv.add("k");
        for (String secA : secArts) {
            for (int i = 0; i < 3; i++) {
                phInv.add(phInv.get(i) + secA);
            }
        }
        for (String poa : extraPOAs) {
            for (int i = 0; i < 3; i++) {
                phInv.add(extraStops.get(poa));
                for (String secA : secArts) {
            }
        }
    }
}
