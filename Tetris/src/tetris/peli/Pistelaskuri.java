
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
    
    public int getTaso() {
        return (int)Math.floor(riveja/10.0)+1;
    }
    
    public String getRivitMerkkijono() {
        return ""+riveja;
    }
    
    public String getTasoMerkkijono() {
        return ""+getTaso();
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
