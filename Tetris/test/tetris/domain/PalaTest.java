package tetris.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalaTest {
    
    Pala testiPala;
    Pala toinenPala;
    
    public PalaTest() {
    }
    
    @Before
    public void setUp() {
        testiPala = new Pala(10,10);
    }
    
    @Test
    public void onkoOikeinGetX() {
        assertEquals(testiPala.getX(),10);
    }

    @Test
    public void onkoOikeinGetY() {
        assertEquals(testiPala.getY(),10);
    }

    @Test
    public void testataanOsuuko() {
        toinenPala = new Pala(10,10);
        assertEquals(testiPala.osuu(toinenPala),true);
        
        toinenPala = new Pala(20,20);
        assertEquals(testiPala.osuu(toinenPala),false);
    }

    @Test
    public void ToStringOikeassaMuodossa() {
        assertEquals(testiPala.toString(),"(10,10)");
    }
}
