package tetris.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import tetris.Suunta;
import tetris.domain.Muoto;
import tetris.domain.MuotoGeneraattori;
import tetris.domain.Pala;
import tetris.gui.Paivitettava;

public class Peli extends Timer implements ActionListener {

    private List<Muoto> muodot;
    private List<Muoto> staattiset;
    private int leveys;
    private int korkeus;
    private boolean jatkuu;
    private int nopeus;
    private Pistelaskuri laskuri;
    private Muoto aktiivinenMuoto;
    private int sykli = 0;
    private Paivitettava paivitettava;
    private MuotoGeneraattori generaattori;

    public Peli() {
        super(1000, null);
        this.muodot = new ArrayList<Muoto>();
        this.staattiset = new ArrayList<Muoto>();
        this.jatkuu = true;
        this.nopeus = 1000;
        this.laskuri = new Pistelaskuri();
        this.generaattori = new MuotoGeneraattori();

        lisaaMuoto();

        addActionListener(this);
        setInitialDelay(1000);
    }

    public boolean jatkuu() {
        return jatkuu;
    }

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

    public void liikutaAktiivista() {
        if (aktiivinenMuoto.osuuAlareunaan() || aktiivinenMuoto.osuuMuotoihin(staattiset)) {
            laskuri.lisaaPisteita(100);
            lisaaMuoto();
        } else {
            aktiivinenMuoto.liiku();
        }
    }

    public boolean tarkistaRivi() {
        return false;
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

        System.out.println("Peli käy! (sykli #" + sykli + ", kesto(nopeus) " + nopeus + " ms)");

    }
}
