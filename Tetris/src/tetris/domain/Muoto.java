package tetris.domain;

import java.util.ArrayList;
import java.util.List;
import tetris.Suunta;

/**
 * Tetriksessä käytettävä muoto, koostuu Paloista.
 *
 * Muotoja liikutellaan pelissä, ja niitä generoidaan
 * MuotoGeneraattori-luokalla.
 *
 * @author tomminikkanen
 */
public class Muoto {

    private List<Pala> palat;
    private Suunta suunta;
    private int muotoTyyppi;
    private int asento;

    public Muoto(Suunta alkusuunta, int tyyppi, int asento) {
        this.palat = new ArrayList<>();
        this.suunta = alkusuunta;
        this.muotoTyyppi = tyyppi;
        this.asento = asento;
    }

    /**
     * Lisätään haluttu pala osaksi muotoa.
     *
     * @param pala lisättävä Pala.
     */
    public void lisaaPala(Pala pala) {
        this.palat.add(pala);
    }

    public Suunta getSuunta() {
        return this.suunta;
    }

    public void setSuunta(Suunta suunta) {
        this.suunta = suunta;
    }

    public int getKoko() {
        return this.palat.size();
    }

    public int getTyyppi() {
        return this.muotoTyyppi;
    }

    public int getAsento() {
        return this.asento;
    }

    public void setAsento(int asento) {
        this.asento = asento;
    }

    public List<Pala> getPalat() {
        return this.palat;
    }

    /**
     * Liikuttaa kaikkia muodon paloja haluttuun suuntaan.
     *
     * @param xmuutos haluttu x-koordinaatin muutos
     * @param ymuutos haluttu y-koordinaatin muutos
     */
    public void liikutaPaloja(int xmuutos, int ymuutos) {
        for (Pala liikutettava : this.getPalat()) {
            liikutettava.liiku(xmuutos, ymuutos);
        }
    }

    /**
     * Kutsuu liikutaPaloja-metodia oikeilla parametreilla, suunnasta riippuen.
     */
    public void liiku() {
        if (this.suunta == Suunta.ALAS) {
            liikutaPaloja(0, 30);
        }
        if (this.suunta == Suunta.VASEN) {
            liikutaPaloja(-30, 0);
        }
        if (this.suunta == Suunta.OIKEA) {
            liikutaPaloja(30, 0);
        }

    }

    /**
     * Tekee muodosta haamukopion jota voidaan käyttää törmäystesteissä.
     *
     * @return Kopioitu Muoto
     */
    public Muoto kopioiMuoto() {
        Muoto kopioitu = new Muoto(Suunta.ALAS, this.muotoTyyppi, this.asento);
        kopioitu.palat.clear();
        for (Pala pala : this.palat) {
            kopioitu.palat.add(new Pala(pala.getX(), pala.getY(), pala.getVari(), pala.getKuva()));
        }
        return kopioitu;
    }

    /**
     * Tarkistaa osuuko muoto parametrina annetun listan muotoihin.
     *
     * @param palat Palat joihin olion muotoa verrataan.
     * @return True, jos osuu muotoihin, False, jos ei
     */
    public boolean osuuMuotoihin(List<Pala> palat) {
        Muoto kopio = kopioiMuoto();
        kopio.setSuunta(this.suunta);
        kopio.liiku();
        for (Pala tarkistettavaPala : palat) {
            if (kopio.osuu(tarkistettavaPala)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa osuuko annettu Pala olion omiin paloihin.
     *
     * @param pala
     * @return True, jos pala osuu omiin paloihin, False, jos ei.
     */
    public boolean osuu(Pala pala) {
        for (Pala muotoPala : this.palat) {
            if (muotoPala.osuu(pala)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa meneekö yksikään annetun muodon paloista sivureunojen yli.
     *
     * @return True, jos annettu muoto menee reunan yli, False, jos ei.
     */
    public boolean meneeReunanYli(Muoto muoto) {
        for (Pala tarkistettava : muoto.getPalat()) {
            if ((tarkistettava.getX()) < 0 || (tarkistettava.getX()) >= 300) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tekee oliosta kopion, ja tarkistaa osuuko kopio reunoihin.
     *
     * @return True, jos osuu reunaan, False jos ei.
     */
    public boolean osuuReunaan() {
        Muoto kopio = kopioiMuoto();
        kopio.setSuunta(this.suunta);
        kopio.liiku();

        if (meneeReunanYli(kopio)) {
            return true;
        }
        return false;
    }

    /**
     * Tarkistaa osuuko yksikään muodon paloista alareunaan.
     *
     * @return True, jos osuu alareunaan, False jos ei.
     */
    public boolean osuuAlareunaan() {
        for (Pala tarkistettava : palat) {
            if ((tarkistettava.getY() + 31) >= 600) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa jääkö joku muodon osista ylärajan yläpuolelle
     *
     * @return True, jos jää, False, jos ei.
     */
    public boolean meneeYlarajanYli() {
        for (Pala tarkistettava : palat) {
            if ((tarkistettava.getY() < 0)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Paloja " + this.palat.size();
    }
}
