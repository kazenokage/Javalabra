
package tetris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.Suunta;

public class Nappaimistonkuuntelija implements KeyListener {
    
    public Nappaimistonkuuntelija() {
        
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {

        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {

        } else if (ke.getKeyCode() == KeyEvent.VK_UP) {

        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {

        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
