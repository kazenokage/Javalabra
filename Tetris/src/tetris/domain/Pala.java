package tetris.domain;

import tetris.Vari;
import java.awt.Color;

public class Pala {

    private int x;
    private int y;
    private Color vari;

    public Pala(int x, int y, Color vari) {
        this.x = x;
        this.y = y;
        this.vari = vari;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public Color getVari() {
        return this.vari;
    }
    
    public void liiku(int xmuutos, int ymuutos) {
        this.x += xmuutos;
        this.y += ymuutos;
    }

    public boolean osuu(Pala pala) {
        if (this.x == pala.getX() && this.y == pala.getY()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}