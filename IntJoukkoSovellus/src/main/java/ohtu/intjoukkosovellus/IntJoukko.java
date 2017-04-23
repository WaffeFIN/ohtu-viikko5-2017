package ohtu.intjoukkosovellus;

public class IntJoukko {

    private final static int OLETUSKAPASITEETTI = 5;
    /**
     * Luotava uusi taulukko on näin paljon isompi kuin vanha.
     */
    private final static int OLETUSKASVATUS = 5;

    private int kasvatuskoko;
    private int[] ljono;
    private int alkioidenLkm;

    public IntJoukko() {
        this(OLETUSKAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0)
            throw new IllegalArgumentException("Kapasitteetti väärin");//:D
        if (kasvatuskoko < 0)
            throw new IllegalArgumentException("Kasvatuskoko väärin");//:D
        ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            ljono[0] = luku;
            alkioidenLkm++;
            return true;
        }
        if (kuuluu(luku))
            return false;
        lisaaLoppuun(luku);
        return true;
    }

    private void lisaaLoppuun(int luku) {
        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm == ljono.length)
            suurennaTaulukkoa();
    }

    private void suurennaTaulukkoa() {
        int[] uusi = new int[ljono.length + kasvatuskoko];
        kopioiTaulukko(ljono, uusi);
        ljono = uusi;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i])
                return true;
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = haeLuvunIndeksi(luku);
        if (kohta >= 0) {
            siirraTaulukonLoppuosaa(kohta);
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void siirraTaulukonLoppuosaa(int kohta) {
        int apu;
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            apu = ljono[j];
            ljono[j] = ljono[j + 1];
            ljono[j + 1] = apu;
        }
    }

    private int haeLuvunIndeksi(int luku) {
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                kohta = i; //:D
                break;
            }
        }
        return kohta;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0)
            return "{}";
        return luoMerkkijono();
    }

    private String luoMerkkijono() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += ljono[i];
            tuotos += ", ";
        }
        tuotos += ljono[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(ljono, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        lisaaJoukolle(a, x);
        lisaaJoukolle(b, x);
        return x;
    }

    private static void lisaaJoukolle(IntJoukko a, IntJoukko x) {
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j])
                    y.lisaa(bTaulu[j]);
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        lisaaJoukolle(a, z);
        poistaJoukolta(b, z);
        return z;
    }

    private static void poistaJoukolta(IntJoukko b, IntJoukko z) {
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
    }

}
