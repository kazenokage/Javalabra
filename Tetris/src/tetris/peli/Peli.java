package tetris.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import tetris.domain.Muoto;
import tetris.domain.MuotoGeneraattori;
import tetris.domain.Pala;
import tetris.gui.Paivitettava;

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
    private int nopeus;
    private Pistelaskuri laskuri;
    private Muoto aktiivinenMuoto;
    private int sykli = 0;
    private Paivitettava paivitettava;
    private MuotoGeneraattori generaattori;

    public Peli() {
        super(1000, null);
        this.muodot = new ArrayList<>();
        this.staattiset = new ArrayList<>();
        this.jatkuu = true;
        this.nopeus = 1000;
        this.laskuri = new Pistelaskuri();
        this.generaattori = new MuotoGeneraattori();

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
            laskuri.lisaaPisteita(100);
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
        System.out.println("Kutsuttiin tarkistusta!");
        int lahtoY = 0;
        ArrayList<Integer> taydetRivit = new ArrayList<>();
        for (int rivi = 19; rivi >= 0; rivi--) {
            int palaLaskuri = 0;
            for (Muoto muoto : staattiset) {
                for (Pala pala : muoto.getPalat()) {
                    if (pala.getY() == rivi*30) {
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
                tiputaRiveja((tyhjennettava*30)+60);
            }
        }
    }
    
    public void tiputaRiveja(int mistaYlospain) {
        System.out.println("Kutsuttiin rivien tipauttamista riviltä "+mistaYlospain+" lähtien!");
        for (Muoto muoto : staattiset) {
            for (Pala pala : muoto.getPalat()) {
                if (pala.getY()<mistaYlospain) {
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
        sykli++;

        if (!jatkuu) {
            System.out.println("Game over!");
            return;
        }

        liikutaAktiivista();
        paivitettava.paivita();

        // System.out.println("Peli käy! (sykli #" + sykli + ", kesto(nopeus) " + nopeus + " ms)");

    }
}
