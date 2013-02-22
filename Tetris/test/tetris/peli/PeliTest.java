package tetris.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.domain.MuotoGeneraattori;

public class PeliTest {

    Peli testiPeli;
    MuotoGeneraattori testiGeneraattori;

    public PeliTest() {
    }

    @Before
    public void setUp() {
        testiPeli = new Peli();
    }

    @Test
    public void kaynnistyykoPeli() {
        testiPeli.start();
        assertEquals(testiPeli.jatkuu(), true);
        assertEquals(testiPeli.isRunning(), true);
    }

    @Test
    public void onkoAktiivinenMuoto() {
        assertNotNull(testiPeli.getAktiivinenMuoto());
    }

    @Test
    public void tallentuukoMuodotOikein() {
        testiPeli.lisaaMuoto();
        testiPeli.lisaaMuoto();
        assertEquals(3, testiPeli.getKaikkiMuodot().size());
        assertEquals(8, testiPeli.getStaattiset().size());
    }

    @Test
    public void toimiikoPelinAloittaminenAlusta() {
        testiPeli.aloitaAlusta();
        assertEquals(0, testiPeli.getStaattiset().size());
        assertEquals(1, testiPeli.getKaikkiMuodot().size());
        assertEquals(true, testiPeli.jatkuu());
    }
}
