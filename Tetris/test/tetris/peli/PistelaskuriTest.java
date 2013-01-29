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

}
