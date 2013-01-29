package tetris.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.Suunta;

public class MuotoTest {

    Muoto testiMuoto;
    Muoto testiMuoto2;
    Muoto testiMuoto3;
    List<Muoto> testiMuodot;

    public MuotoTest() {
    }

    @Before
    public void setUp() {
        testiMuoto = new Muoto(0, 0, Suunta.ALAS);
        testiMuoto2 = new Muoto(0, 10, Suunta.ALAS);
        testiMuoto3 = new Muoto(0, 20, Suunta.ALAS);
        testiMuodot = new ArrayList<Muoto>();
        testiMuodot.add(testiMuoto2);
        testiMuodot.add(testiMuoto3);
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
    public void havaitaankoAlareuna() {
        for (int i = 0; i < 17; i++) {
            testiMuoto.liiku();
        }
        assertEquals(testiMuoto.osuuAlareunaan(), true);
    }
    
    @Test
    public void havaitaankoTormays() {
        assertEquals(testiMuoto.osuuMuotoihin(testiMuodot), true);
    }
    
    
}
