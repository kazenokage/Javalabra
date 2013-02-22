package tetris.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PistelaskuriTest {
    
    private Pistelaskuri testiLaskuri;
    
    public PistelaskuriTest() {
    }
    
    @Before
    public void setUp() {
        testiLaskuri = new Pistelaskuri();
    }

    @Test
    public void pisteetOikeinLisayksenJalkeen() {
        testiLaskuri.lisaaPisteita(500);
        testiLaskuri.lisaaPisteita(200);
        assertEquals(testiLaskuri.getPisteet(),700);
    }
    
    @Test 
    public void riviMaaraOikein() {
        testiLaskuri.lisaaRivi();
        testiLaskuri.lisaaRivi();
        assertEquals(2, testiLaskuri.getRivit());
    }
    
    @Test
    public void tasoOikein() {
        for (int i = 0; i < 15; i++) {
            testiLaskuri.lisaaRivi();
        }
        assertEquals(2, testiLaskuri.getTaso());
    }

}
