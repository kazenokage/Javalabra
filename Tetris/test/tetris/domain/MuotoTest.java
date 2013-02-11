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
    MuotoGeneraattori generaattori;

    public MuotoTest() {
    }

    @Before
    public void setUp() {
        generaattori = new MuotoGeneraattori();
        testiMuoto = generaattori.luoUusi(0, 0);
        testiMuoto2 = generaattori.luoUusi(0, 10);
        testiMuoto3 = generaattori.luoUusi(0, 20);
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
        assertEquals(false, testiMuoto.osuuAlareunaan());
    }
    
    @Test
    public void havaitaankoTormays() {
        assertEquals(false,testiMuoto.osuuMuotoihin(testiMuodot));
    }
    
    
}
