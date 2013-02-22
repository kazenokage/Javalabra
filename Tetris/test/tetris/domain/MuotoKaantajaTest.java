package tetris.domain;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.Suunta;

public class MuotoKaantajaTest {
    
    private MuotoKaantaja testiKaantaja;
    private Muoto testiMuoto;
    private Pala pala1;
    private Pala pala2;
    private Pala pala3;
    private Pala pala4;
    
    public MuotoKaantajaTest() {
    }
    
    @Before
    public void setUp() {
        testiKaantaja = new MuotoKaantaja();
        testiMuoto = new Muoto(Suunta.ALAS,0,0);
        pala1 = new Pala(0,-30,Color.CYAN,null);
        pala2 = new Pala(0,0,Color.CYAN,null);
        pala3 = new Pala(0,30,Color.CYAN,null);
        pala4 = new Pala(0,60,Color.CYAN,null);
        testiMuoto.lisaaPala(pala1);
        testiMuoto.lisaaPala(pala2);
        testiMuoto.lisaaPala(pala3);
        testiMuoto.lisaaPala(pala4);
    }

    @Test
    public void kaannaMuotoaTest() {
        testiKaantaja.kaannaMuoto(testiMuoto, true);
        assertEquals(-30, pala1.getX());
        assertEquals(0, pala1.getY());
        assertEquals(0, pala2.getX());
        assertEquals(0, pala2.getY());
        assertEquals(30, pala3.getX());
        assertEquals(0, pala3.getY());
        assertEquals(60, pala4.getX());
        assertEquals(0, pala4.getY());
    }
}
