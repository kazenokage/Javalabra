
package tetris.peli;

/**
 * Pelin pisteidenlaskuun tarkoitettu luokka. 
 * 
 * @author tomminikkanen
 */

public class Pistelaskuri {
    private int pisteet;
    private int riveja;
    
    public Pistelaskuri() {
        pisteet = 0;
        riveja = 0;
    }
    
    public int getPisteet() {
        return pisteet;
    }
    
    public int getRivit() {
        return riveja;
    }
    
    public String getRivitMerkkijono() {
        return ""+riveja;
    }
    
    public void lisaaPisteita(int lisays) {
        pisteet += lisays;
    }
    
    public void lisaaRivi() {
        riveja++;
    }
    
    @Override
    public String toString() {
        return ""+pisteet;
    }
}
