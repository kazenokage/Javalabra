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
        this.palat.add(new Pala(alkuX,alkuY));
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
    
    public void liiku() {
            if(this.suunta == Suunta.YLOS) {
                int x = this.palat.get(palat.size()-1).getX();
                int y = this.palat.get(palat.size()-1).getY()-1;
                this.palat.add(new Pala(x,y));
            }
            if(this.suunta == Suunta.ALAS) {
                int x = this.palat.get(palat.size()-1).getX();
                int y = this.palat.get(palat.size()-1).getY()+1;
                this.palat.add(new Pala(x,y));
            }
            if(this.suunta == Suunta.VASEN) {
                int x = this.palat.get(palat.size()-1).getX()-1;
                int y = this.palat.get(palat.size()-1).getY();
                this.palat.add(new Pala(x,y));
            }
            if(this.suunta == Suunta.OIKEA) {
                int x = this.palat.get(palat.size()-1).getX()+1;
                int y = this.palat.get(palat.size()-1).getY();
                this.palat.add(new Pala(x,y));
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
    
}
