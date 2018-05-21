import java.util.HashMap;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

/**
 * Case system: Choose from the following cases.
 * 1. Nominative
 * 2. Accusative
 * 3. Genitive
 * 4. Dative
 * 5. Ablative
 * 6. Locative
 * 7. Vocative
 * 8. Instrumental
 */
public class MorphologyGenerator {
    public HashMap<String, String> caseMorphology;
    public Random randomChooser;
    public List<String> phonemicInv;

    public MorphologyGenerator() {
        this.randomChooser = new Random(413);
        int inception = randomChooser.nextInt();
        int pilot = inception % 10;
        inception = inception / 10;
        HashMap<String, Integer> ph2Int = makeMap();
    }

    private HashMap makeMap() {
        HashMap<String, Integer> ph2Int = new HashMap<>();
        int index = 0;
        for (String phoneme : phonemicInv) {
            ph2Int.put(phoneme, index);
            index++;
        }
        return ph2Int;
    }

    public static void main(String[] args) {
        Random rTest = new Random(413);
        for (int i = 0; i < 10; i++) System.out.println(Math.abs(rTest.nextInt()));
    }
}
