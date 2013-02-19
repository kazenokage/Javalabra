package tetris.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;
import tetris.domain.Muoto;
import tetris.domain.MuotoGeneraattori;
import tetris.domain.Pala;
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
    private List<Muoto> staattiset;
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

    public Peli() throws Exception {
        super(1000, null);
        this.muodot = new ArrayList<>();
        this.staattiset = new ArrayList<>();
        this.jatkuu = true;
        this.nopeus = 1000;
        this.laskuri = new Pistelaskuri();
        this.generaattori = new MuotoGeneraattori();
        this.tiedostoKasittelija = new TiedostoKasittelija();
        this.highScoret = tiedostoKasittelija.lueHighscore();

        lisaaMuoto();

        addActionListener(this);
        setInitialDelay(1000);
    }

    /**
     *
     * Kertoo jatkuuko peli
     *
     * @return
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
     * Luo uuden muodon, lisää sen muotoihin ja tekee siitä aktiivisen
     */
    public void lisaaMuoto() {
        Muoto uusiMuoto = generaattori.luoUusi(120, 0);
        muodot.add(uusiMuoto);
        if (aktiivinenMuoto != null) {
            staattiset.add(aktiivinenMuoto);
            if (aktiivinenMuoto.meneeYlarajanYli()) {
                jatkuu = false;
                gameOver = true;
                highScoret.add(laskuri.getPisteet());
                tiedostoKasittelija.tallennaHighscore(highScoret);
            }
        }
        aktiivinenMuoto = uusiMuoto;

    }

    public Muoto getAktiivinenMuoto() {
        if (aktiivinenMuoto != null) {
            return aktiivinenMuoto;
        }
        return null;
    }

    public int getParhaatPisteet() {
        Collections.sort(this.highScoret);
        Collections.reverse(this.highScoret);
        return highScoret.get(0);
    }

    public List<Muoto> getKaikkiMuodot() {
        return muodot;
    }

    public List<Muoto> getStaattiset() {
        return staattiset;
    }

    public Pistelaskuri getPistelaskuri() {
        return laskuri;
    }

    /**
     * Liikuttaa aktiivista muotoa, jos mahdollista
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
     * Tarkistaa onko rivi täynnä
     *
     * @return
     */
    public void tarkistaRivit() {
        ArrayList<Integer> taydetRivit = new ArrayList<>();
        for (int rivi = 0; rivi < 20; rivi++) {
            int palaLaskuri = 0;
            for (Muoto muoto : staattiset) {
                for (Pala pala : muoto.getPalat()) {
                    if (pala.getY() == rivi * 30) {
                        palaLaskuri++;
                    }
                }
            }
            if (palaLaskuri == 10) {
                taydetRivit.add(rivi);
            }
        }
        if (!taydetRivit.isEmpty()) {
            for (Integer tyhjennettava : taydetRivit) {
                laskuri.lisaaPisteita(1000);
                laskuri.lisaaRivi();
                if (laskuri.getRivit() % 10 == 0) {
                    kasvataNopeutta();
                }
                tiputaRiveja((tyhjennettava * 30) + 60);
            }
        }
    }

    /**
     * Kasvattaa pelin nopeutta yhden askeleen verran
     */
    public void kasvataNopeutta() {
        if (this.nopeus >= 100) {
            this.nopeus -= 100;
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
        for (Muoto muoto : staattiset) {
            for (Pala pala : muoto.getPalat()) {
                if (pala.getY() < mistaYlospain) {
                    pala.liiku(0, 30);
                }
            }
        }
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
