package tetris.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;
import tetris.domain.*;
import tetris.gui.Paivitettava;
import tetris.io.TiedostoKasittelija;

/**
 *
 * Tetriksen pääluokka, pyörittää peliä
 *
 * @author tomminikkanen
 */
public class Peli extends Timer implements ActionListener {

    private List<Muoto> muodot;
    private List<Pala> staattiset;
    private boolean jatkuu;
    private boolean tauko;
    private boolean gameOver;
    private int nopeus;
    private Pistelaskuri laskuri;
    private Muoto aktiivinenMuoto;
    private Paivitettava paivitettava;
    private MuotoGeneraattori generaattori;
    private TiedostoKasittelija tiedostoKasittelija;
    private ArrayList<Integer> highScoret;

    public Peli() {
        super(100, null);
        this.muodot = new ArrayList<>();
        this.staattiset = new ArrayList<>();
        this.jatkuu = true;
        this.nopeus = 100;
        this.laskuri = new Pistelaskuri();
        this.generaattori = new MuotoGeneraattori();
        this.tiedostoKasittelija = new TiedostoKasittelija();
        try {
            this.highScoret = tiedostoKasittelija.lueHighscore();
        } catch (Exception ex) {
            System.out.println("Highscorelistaa ei saatu avattua /:");
        }

        lisaaMuoto();

        addActionListener(this);
        setInitialDelay(100);
    }

    /**
     *
     * Kertoo jatkuuko peli
     *
     * @return True, jos peli jatkuu, False jos ei.
     */
    public boolean jatkuu() {
        return jatkuu;
    }

    public boolean gameOver() {
        return gameOver;
    }

    public void setGameOver() {
        this.gameOver = true;
        stop();
    }

    /**
     * Nollaa pelin parametrit ja aloittaa sen alusta.
     */
    public void aloitaAlusta() {
        this.jatkuu = true;
        this.tauko = false;
        this.gameOver = false;
        this.laskuri.nollaa();
        this.aktiivinenMuoto = null;
        this.muodot.clear();
        this.staattiset.clear();
        lisaaMuoto();
        start();
    }

    /**
     * Siirtää pelin tauolle.
     */
    public void tauko() {
        if (tauko) {
            tauko = false;
            start();
        } else {
            tauko = true;
            stop();
        }
    }

    public boolean onkoTauko() {
        return tauko;
    }

    /**
     * Luo uuden muodon, lisää sen muotoihin ja tekee siitä aktiivisen.
     */
    public void lisaaMuoto() {
        Muoto uusiMuoto = generaattori.luoUusi(120, 0);
        muodot.add(uusiMuoto);
        if (aktiivinenMuoto != null) {
            for (Pala pala : aktiivinenMuoto.getPalat()) {
                staattiset.add(pala);
            }
            if (aktiivinenMuoto.meneeYlarajanYli()) {
                lopetaPeli();
            }
        }
        aktiivinenMuoto = uusiMuoto;

    }

    /**
     * Lopettaa käynnissä olevan pelin.
     */
    public void lopetaPeli() {
        jatkuu = false;
        gameOver = true;
        highScoret.add(laskuri.getPisteet());
        tiedostoKasittelija.tallennaHighscore(highScoret);
    }

    /**
     * palauttaa aktiivisen muodon
     *
     * @return aktiivinen Muoto
     */
    public Muoto getAktiivinenMuoto() {
        if (aktiivinenMuoto != null) {
            return aktiivinenMuoto;
        }
        return null;
    }

    /**
     * Hakee highscore-listan parhaat pisteet.
     *
     * @return Parhaat pisteet
     */
    public int getParhaatPisteet() {
        Collections.sort(this.highScoret);
        Collections.reverse(this.highScoret);
        return highScoret.get(0);
    }

    public List<Muoto> getKaikkiMuodot() {
        return muodot;
    }

    public List<Pala> getStaattiset() {
        return staattiset;
    }

    public Pistelaskuri getPistelaskuri() {
        return laskuri;
    }

    /**
     * Liikuttaa aktiivista muotoa, jos mahdollista.
     */
    public void liikutaAktiivista() {
        if (aktiivinenMuoto.osuuAlareunaan() || aktiivinenMuoto.osuuMuotoihin(staattiset)) {
            laskuri.lisaaPisteita(aktiivinenMuoto.getPalat().size());
            lisaaMuoto();
            tarkistaRivit();
        } else {
            aktiivinenMuoto.liiku();
        }
    }

    /**
     * Tarkistaa onko rivejä täynnä. Jos rivi on täysi, poistetaan se,
     * tiputetaan yläpuolella olevia rivejä, ja lisätään pisteitä.
     */
    public void tarkistaRivit() {
        ArrayList<Integer> taydetRivit = new ArrayList<>();
        for (int rivi = 19; rivi >= 0; rivi--) {
            int palaLaskuri = 0;
            for (Pala pala : staattiset) {
                if (pala.getY() == rivi * 30) {
                    palaLaskuri++;
                }
            }
            if (palaLaskuri == 10) {
                taydetRivit.add(rivi);
            }
        }
        if (!taydetRivit.isEmpty()) {
            for (Integer tyhjennettava : taydetRivit) {
                laskuri.lisaaPisteitaRivista(taydetRivit.size());
                laskuri.lisaaRivi();
                if (laskuri.getRivit() % 10 == 0) {
                    kasvataNopeutta();
                }
                poistaRivi(tyhjennettava * 30);

            }
            Collections.reverse(taydetRivit);
            for (Integer tiputettava : taydetRivit) {
                tiputaRiveja((tiputettava * 30));
            }
            paivitettava.paivita();
        }
    }

    /**
     * Kasvattaa pelin nopeutta yhden askeleen verran
     */
    public void kasvataNopeutta() {
        if (this.nopeus >= 100) {
            this.nopeus -= 100;
            setDelay(this.nopeus);
        }
    }

    /**
     *
     * Käytetään kaikkien tietyn linjan yläpuolella olevien palojen
     * tiputtamiseen alaspäin.
     *
     * @param mistaYlospain Mistä y-koordinaatista ylöspäin olevat palat
     * tiputetaan alaspäin.
     */
    public void tiputaRiveja(int mistaYlospain) {
        for (Pala pala : staattiset) {
            if (pala.getY() < mistaYlospain) {
                pala.liiku(0, 30);
            }
        }
    }

    /**
     * Poistaa kaikki palat joiden y-koordinaatti vastaa parametrina annettua
     * y-koordinaattia.
     *
     * @param mikaY missä y-koordinaatissa poistettavat palat sijaitsevat
     */
    public void poistaRivi(int mikaY) {
        for (Pala pala : etsiPoistettavatPalat(mikaY)) {
            staattiset.remove(pala);
        }
    }

    /**
     * Etsii kaikki palat, jotka sijaitsevat annetussa y-koordinaatissa.
     *
     * @param mikaY Haluttu y-koordinaatti
     * @return lista paloista
     */
    public List<Pala> etsiPoistettavatPalat(int mikaY) {
        List<Pala> palautettava = new ArrayList<>();
        for (Pala pala : staattiset) {
            if (pala.getY() == mikaY) {
                palautettava.add(pala);
            }
        }
        return palautettava;
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jatkuu) {
            liikutaAktiivista();
            paivitettava.paivita();
        } else if (gameOver) {
            paivitettava.paivita();
        }

    }
}
