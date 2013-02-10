package tetris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.Suunta;
import tetris.domain.MuotoKaantaja;
import tetris.peli.Peli;

/**
 * Tetriksen näppäimistönkuuntelija
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
        if (!peli.getAktiivinenMuoto().osuuMuotoihin(peli.getStaattiset()) && !peli.getAktiivinenMuoto().osuuAlareunaan()) {
            peli.getAktiivinenMuoto().liiku();
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
            kaantaja.kaannaMuoto(peli.getAktiivinenMuoto(), true);
        } else if (ke.getKeyCode() == KeyEvent.VK_Z) {
            kaantaja.kaannaMuoto(peli.getAktiivinenMuoto(), false);
        } else if (ke.getKeyCode() == KeyEvent.VK_X) {
            kaantaja.kaannaMuoto(peli.getAktiivinenMuoto(), true);
        }
        paivitettava.paivita();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        peli.getAktiivinenMuoto().setSuunta(Suunta.ALAS);
    }
}
