package tetris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.Suunta;
import tetris.domain.Muoto;
import tetris.domain.MuotoKaantaja;
import tetris.peli.Peli;

/**
 * Tetriksen näppäimistönkuuntelija. Odottaa painallusta käyttäjältä, ja toimii painetun napin mukaisesti.
 *
 * @author tomminikkanen
 */
public class Nappaimistonkuuntelija implements KeyListener {

    private Peli peli;
    private Paivitettava paivitettava;
    private MuotoKaantaja kaantaja;

    public Nappaimistonkuuntelija(Peli peli, Paivitettava paivitettava) {
        this.peli = peli;
        this.paivitettava = paivitettava;
        this.kaantaja = new MuotoKaantaja();

    }

    /**
     * Liikuttaa aktiivista palasta jos mahdollista
     */
    public void liikuJosEiOsu() {
        if (!peli.getAktiivinenMuoto().osuuMuotoihin(peli.getStaattiset()) && !peli.getAktiivinenMuoto().osuuAlareunaan() && !peli.onkoTauko() && !peli.getAktiivinenMuoto().osuuReunaan()) {
            peli.getAktiivinenMuoto().liiku();
        }
    }
    
    /**
     * Kääntää aktiivista muotoa, jos se ei osu käännettäessä muihin muotoihin tai reunoihin
     * 
     * @param myotapaivaan käännetäänkö palaa myötäpäivään
     */
    public void kaannaJosEiOsu(boolean myotapaivaan) {
        Muoto kopio = peli.getAktiivinenMuoto().kopioiMuoto();
        kaantaja.kaannaMuoto(kopio, myotapaivaan);
        if (!kopio.osuuMuotoihin(peli.getStaattiset()) && !peli.getAktiivinenMuoto().meneeReunanYli(kopio)) {
            kaantaja.kaannaMuoto(peli.getAktiivinenMuoto(), myotapaivaan);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            peli.getAktiivinenMuoto().setSuunta(Suunta.VASEN);
            liikuJosEiOsu();
            peli.getAktiivinenMuoto().setSuunta(Suunta.ALAS);
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            peli.getAktiivinenMuoto().setSuunta(Suunta.OIKEA);
            liikuJosEiOsu();
            peli.getAktiivinenMuoto().setSuunta(Suunta.ALAS);
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            liikuJosEiOsu();
        } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
            kaannaJosEiOsu(true);
        } else if (ke.getKeyCode() == KeyEvent.VK_Z) {
            kaannaJosEiOsu(false);
        } else if (ke.getKeyCode() == KeyEvent.VK_X) {
            kaannaJosEiOsu(true);
        } else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            if (peli.gameOver()) {
                peli.aloitaAlusta();
            } else {
                peli.tauko();
            }
        }
        paivitettava.paivita();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (peli.getAktiivinenMuoto() != null) {
            peli.getAktiivinenMuoto().setSuunta(Suunta.ALAS);
        }
    }
}
