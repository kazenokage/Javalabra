package tetris.domain;

import java.awt.Color;
/**
 * 
 * Kuvaa yksittäistä palaa, joista muodostetaan muotoja.
 * 
 * @author tomminikkanen
 * 
 */
public class Pala {

    private int x;
    private int y;
    private Color vari;
    /**
     * 
     * @param x palan x-koordinaatti
     * @param y palan y-koordinaatti
     * @param vari palan Color-tyyppinen värimäärittely
     * 
     * 
     */
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
    
    /**
     * 
     * Liikutetaan palasta haluuttuihin koordinaatteihin
     * 
     * @param xmuutos haluttu muutos palan x-koordinaattiin
     * @param ymuutos haluttu muutos palan y-koordinaattiin
     */
    public void liiku(int xmuutos, int ymuutos) {
        this.x += xmuutos;
        this.y += ymuutos;
    }

    
    /**
     * 
     * Tarkistetaan osuuko olion määrittelemä pala parametrina annettavaan palaan
     * 
     * @param pala
     * @return true, jos osuu, false jos ei osu
     */
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