package tetris.peli;

/**
 * Pelin pisteidenlaskuun tarkoitettu luokka.
 *
 * @author tomminikkanen
 */
public class Pistelaskuri {

    private int pisteet;
    private int riveja;

    public Pistelaskuri() {
        pisteet = 0;
        riveja = 0;
    }

    public int getPisteet() {
        return pisteet;
    }

    public int getRivit() {
        return riveja;
    }

    /**
     * Kertoo tason, jolla peli tällä hetkellä on
     *
     * @return nykyinen taso
     */
    public int getTaso() {
        return (int) Math.floor(riveja / 10.0) + 1;
    }

    public String getRivitMerkkijono() {
        return "" + riveja;
    }

    public String getTasoMerkkijono() {
        return "" + getTaso();
    }

    /**
     * Lisää pisteitä annetun pistemäärän verran
     *
     * @param lisays
     */
    public void lisaaPisteita(int lisays) {
        pisteet += lisays;
    }

    /**
     * Lisää pisteitä valmiista riveistä. Pisteet ovat riippuvaisia tasosta, ja
     * rivien määrästä.
     *
     * @param riveja rivien lukumäärä
     */
    public void lisaaPisteitaRivista(int riveja) {
        if (riveja == 1) {
            lisaaPisteita(40 * (getTaso() + 1));
        } else if (riveja == 2) {
            lisaaPisteita(100 * (getTaso() + 1));
        } else if (riveja == 3) {
            lisaaPisteita(300 * (getTaso() + 1));
        } else if (riveja == 4) {
            lisaaPisteita(1200 * (getTaso() + 1));
        }
    }

    /**
     * Lisaa riveihin yhden valmiin rivin.
     */
    public void lisaaRivi() {
        riveja++;
    }

    /**
     * Nollaa pistelaskurin kokonaan
     */
    public void nollaa() {
        pisteet = 0;
        riveja = 0;
    }

    @Override
    public String toString() {
        return "" + pisteet;
    }
}
