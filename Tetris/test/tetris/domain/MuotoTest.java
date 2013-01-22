package tetris.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.Suunta;


public class MuotoTest {
    
    Muoto testiMuoto;
    
    public MuotoTest() {
    }
    
    @Before
    public void setUp() {
        testiMuoto = new Muoto(0,0,Suunta.ALAS);
    }
    
    @Test
    public void alkuSuuntaOikein() {
        assertEquals(testiMuoto.getSuunta(), Suunta.ALAS);
    }

    @Test
    public void suuntaOikeinMuutoksenJalkeen() {
        testiMuoto.setSuunta(Suunta.VASEN);
        assertEquals(testiMuoto.getSuunta(), Suunta.VASEN);
    }

    @Test
    public void onkoKokoOikea() {
        assertEquals(testiMuoto.getKoko(), 4);
    }
    
    @Test
    public void testLiiku() {
        // testataan liikkuuko oikein
    }

    @Test
    public void testOsuu() {
        // testataan osumiset
    }
}
