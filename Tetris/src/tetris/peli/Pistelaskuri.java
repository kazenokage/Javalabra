
package tetris.peli;

/**
 * Pelin pisteidenlaskuun tarkoitettu luokka. 
 * 
 * @author tomminikkanen
 */

public class Pistelaskuri {
    private int pisteet;
    
    public Pistelaskuri() {
        pisteet = 0;
    }
    
    public int getPisteet() {
        return pisteet;
    }
    
    public void lisaaPisteita(int lisays) {
        pisteet += lisays;
    }
    
    @Override
    public String toString() {
        return ""+pisteet;
    }
}
