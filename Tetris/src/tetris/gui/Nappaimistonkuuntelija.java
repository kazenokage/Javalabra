
package tetris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.Suunta;
import tetris.peli.Peli;

public class Nappaimistonkuuntelija implements KeyListener {
    
    private Peli peli;
    private Paivitettava paivitettava;
    
    public Nappaimistonkuuntelija(Peli peli, Paivitettava paivitettava) {
        this.peli = peli;
        this.paivitettava = paivitettava;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            peli.getAktiivinenMuoto().liikutaPaloja(-30, 0);
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            peli.getAktiivinenMuoto().liikutaPaloja(30,0);
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            peli.getAktiivinenMuoto().liikutaPaloja(0,30);
        } else if (ke.getKeyCode() == KeyEvent.VK_Z) {
            // kierra myötäpäivään
        } else if (ke.getKeyCode() == KeyEvent.VK_X) {
            // kierra vastapäivään
        }
        paivitettava.paivita();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        peli.getAktiivinenMuoto().setSuunta(Suunta.ALAS);
    }

}
