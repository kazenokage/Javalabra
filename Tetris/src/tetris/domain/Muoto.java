package tetris.domain;

import java.util.ArrayList;
import java.util.List;
import tetris.Suunta;

public class Muoto {

    private List<Pala> palat;
    private Suunta suunta;

    public Muoto(int alkuX, int alkuY, Suunta alkusuunta) {
        this.palat = new ArrayList<>();
        this.palat.add(new Pala(alkuX, alkuY));
        this.palat.add(new Pala(alkuX, alkuY + 30));
        this.palat.add(new Pala(alkuX, alkuY + 60));
        this.palat.add(new Pala(alkuX, alkuY + 90));
        this.suunta = alkusuunta;
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
            liikutaPaloja(0, 30);
        }
        if (this.suunta == Suunta.VASEN) {
            liikutaPaloja(-30, 0);
        }
        if (this.suunta == Suunta.OIKEA) {
            liikutaPaloja(30, 0);
        }

    }

    public boolean osuuMuotoihin(List<Muoto> muodot) {
        for (Muoto tarkistettava : muodot) {
            for (Pala tarkistettavaPala : tarkistettava.getPalat()) {
                if (this.osuu(tarkistettavaPala)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean osuu(Pala pala) {
        for (Pala muotoPala : this.palat) {
            if (muotoPala.osuu(pala)) {
                return true;
            }
        }
        return false;
    }

    public boolean osuuReunaan() {
        for (Pala tarkistettava : palat) {
            if ((tarkistettava.getX()) <= 0
                    || (tarkistettava.getX() + 60) >= 300
                    || (tarkistettava.getY() + 60) >= 600) {
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
