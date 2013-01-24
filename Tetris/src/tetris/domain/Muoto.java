package tetris.domain;

import java.util.ArrayList;
import java.util.List;
import tetris.Suunta;

public class Muoto {

    private List<Pala> palat;
    private Suunta suunta;
    private int pituus;

    public Muoto(int alkuX, int alkuY, Suunta alkusuunta) {
        this.palat = new ArrayList<>();
        this.palat.add(new Pala(alkuX, alkuY));
        this.palat.add(new Pala(alkuX, alkuY + 10));
        this.palat.add(new Pala(alkuX, alkuY + 20));
        this.palat.add(new Pala(alkuX, alkuY + 20));
        this.suunta = alkusuunta;
        this.pituus = 3;
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

    public List<Pala> getPalat() {
        return this.palat;
    }
    
    public void liikutaPaloja(int xmuutos, int ymuutos) {
        for (Pala liikutettava : this.getPalat()) {
            liikutettava.liiku(xmuutos, ymuutos);
        }
    }
    
    public void liiku() {
        if (this.suunta == Suunta.ALAS) {
            liikutaPaloja(0,10);
        }
        if (this.suunta == Suunta.VASEN) {
            liikutaPaloja(-10,0);
        }
        if (this.suunta == Suunta.OIKEA) {
            liikutaPaloja(10,0);
        }

    }

    public boolean osuu(Pala pala) {
        for (Pala muotoPala : this.palat) {
            if (muotoPala.osuu(pala)) {
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
