public class Consonants {

    private boolean voiced;
    private String aspOrSec;
    private String poa;
    private boolean nasal;
    private String moa;

    /**
     * Constructor for Consonants class.
     * Consonants: detailed by voicing, aspiration/secondary articulation,
     * place of articulation, nasality, and manner of articulation,
     * e.g. voiceless unaspirated bilabial oral stop - p
     */
    public Consonants(boolean voiced, String aspOrSec, String poa, boolean nasal, String moa) {
        this.voiced = voiced;
        this.aspOrSec = aspOrSec;
        this.poa = poa;
        this.nasal = nasal;
        this.moa = moa;
    }

}
