import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimplePhonology {
    private static final List<String> baseInv = new ArrayList<>();
    private Double SEED;
    private List<String> secArts;


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

    /**
     * This method applies secondary articulation to all stops.
     */
    private void applySecArtsStops() {
        for (Consonant c : consonantInv) {
            if (c.getPoa().equals("Bilabial")) {
                
            }
        }

    }
    public static void main(String[] args) {
        int seed = Integer.parseInt(args[0]);
    }
}
