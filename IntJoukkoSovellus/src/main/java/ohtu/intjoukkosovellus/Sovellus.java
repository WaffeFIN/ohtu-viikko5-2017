package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class Sovellus {

    private static IntJoukko A, B, C;

    private static String luku(Scanner lukija) {
        String luettu = lukija.nextLine();
        return luettu;
    }

    private static IntJoukko mikaJoukko(Scanner lukija) {
        String luettu;
        luettu = luku(lukija);
        while (true) {
            switch (luettu) {
                case "A":
                case "a":
                    return A;
                case "B":
                case "b":
                    return B;
                case "C":
                case "c":
                    return C;
                default:
                    System.out.println("Virheellinen joukko! " + luettu);
                    System.out.print("Yritä uudelleen!");
                    luettu = luku(lukija);
                    break;
            }
        }
    }

    private static void lisaa(Scanner lukija) {
        int lisLuku;
        IntJoukko joukko;
        System.out.print("Mihin joukkoon? ");
        joukko = mikaJoukko(lukija);
        System.out.println("");
        System.out.print("Mikä luku lisätään? ");
        lisLuku = lukija.nextInt();
        joukko.lisaa(lisLuku);

    }

    private static void yhdiste(Scanner lukija) {
        IntJoukko aJoukko, bJoukko, c;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko(lukija);
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko(lukija);
        c = IntJoukko.yhdiste(aJoukko, bJoukko);
        System.out.println("A yhdiste B = " + c.toString());
    }

    private static void leikkaus(Scanner lukija) {
        IntJoukko aJoukko, bJoukko, c;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko(lukija);
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko(lukija);
        c = IntJoukko.leikkaus(aJoukko, bJoukko);
        System.out.println("A leikkaus B = " + c.toString());
    }

    private static void erotus(Scanner lukija) {
        IntJoukko aJoukko, bJoukko, c;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko(lukija);
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko(lukija);
        c = IntJoukko.erotus(aJoukko, bJoukko);
        System.out.println("A erotus B = " + c.toString());
    }

    private static void poista(Scanner lukija) {
        IntJoukko joukko;
        int lisLuku;
        System.out.print("Mistä joukosta? ");
        joukko = mikaJoukko(lukija);
        System.out.print("Mikä luku poistetaan? ");
        lisLuku = lukija.nextInt();
        joukko.poista(lisLuku);
    }

    private static void kuuluu(Scanner lukija) {
        IntJoukko joukko;
        int kysLuku;
        System.out.print("Mihin joukkoon? ");
        joukko = mikaJoukko(lukija);
        System.out.print("Mikä luku? ");
        kysLuku = lukija.nextInt();
        boolean kuuluuko = joukko.kuuluu(kysLuku);
        if (kuuluuko) {
            System.out.println(kysLuku + " kuuluu joukkoon ");
        } else {
            System.out.println(kysLuku + " ei kuulu joukkoon ");
        }
    }

    public static void main(String[] args) {
        A = new IntJoukko();
        B = new IntJoukko();
        C = new IntJoukko();

        tervehdi();

        Scanner lukija = new Scanner(System.in);
        while (true) {
            if (lueKomento(lukija))
                return;
        }
    }

    private static boolean lueKomento(Scanner lukija) {
        String luettu = lukija.nextLine().toLowerCase();
        switch (luettu) {
            case "lisää":
            case "li":
                lisaa(lukija);
                break;
            case "poista":
            case "p":
                poista(lukija);
                break;
            case "kuuluu":
            case "k":
                kuuluu(lukija);
                break;
            case "yhdiste":
            case "y":
                yhdiste(lukija);
                break;
            case "leikkaus":
            case "le":
                leikkaus(lukija);
                break;
            case "erotus":
            case "e":
                erotus(lukija);
                break;
            case "A":
                System.out.println(A);
                break;
            case "B":
                System.out.println(B);
                break;
            case "C":
                System.out.println(C);
                break;
            case "lopeta":
            case "quit":
            case "q":
                System.out.println("Lopetetaan, moikka!");
                return true;
            default:
                System.out.println("Virheellinen komento! " + luettu);
                tulostaKomennot();
                break;
        }
        tulostaKomennot();
        return false;
    }

    private static void tulostaKomennot() {
        System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");
    }

    private static void tervehdi() {
        System.out.println("Tervetuloa joukkolaboratorioon!");
        System.out.println("Käytössäsi ovat joukot A, B ja C.");
        System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e), leikkaus(le) ja lopetus(quit)(q).");
        System.out.println("Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.");
    }
}
