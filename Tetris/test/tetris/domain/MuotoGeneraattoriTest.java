package tetris.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomminikkanen
 */
public class MuotoGeneraattoriTest {
    
    MuotoGeneraattori testiGeneraattori;
    
    public MuotoGeneraattoriTest() {
    }
    
    @Before
    public void setUp() {
        testiGeneraattori = new MuotoGeneraattori();
    }

    @Test
    public void luodaankoUusiPalaOikein() {
        Muoto testiMuoto = testiGeneraattori.luoUusi(0, 120);
        assertEquals(4,testiMuoto.getPalat().size());
        
    }
}
