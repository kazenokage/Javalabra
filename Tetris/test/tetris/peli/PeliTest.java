package tetris.peli;

import java.awt.event.ActionEvent;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.Suunta;
import tetris.domain.Muoto;

public class PeliTest {
    
    Peli testiPeli;
    
    public PeliTest() {
    }
    
    @Before
    public void setUp() {
        testiPeli = new Peli();
    }

    @Test
    public void kaynnistyykoPeli() {
        testiPeli.start();
        assertEquals(testiPeli.jatkuu(),true);
        assertEquals(testiPeli.isRunning(),true);
    }

    @Test
    public void onkoAktiivinenMuoto() {
        assertNotNull(testiPeli.getAktiivinenMuoto());        
    }

    @Test
    public void tallentuukoMuodotOikein() {
        testiPeli.lisaaMuoto();
        testiPeli.lisaaMuoto();
        assertEquals(testiPeli.getKaikkiMuodot().size(),3);
    }
    
}
