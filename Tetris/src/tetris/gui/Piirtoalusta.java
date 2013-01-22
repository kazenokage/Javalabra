package tetris.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.domain.Muoto;
import tetris.domain.Pala;
import tetris.peli.Peli;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli tetrisPeli;
    
    public Piirtoalusta(Peli tetrisPeli) {
        this.tetrisPeli = tetrisPeli;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        // testipala
        g.fill3DRect(10 * 10, 10 * 10, 20, 20, true);
        
//        for (Muoto muoto : this.tetrisPeli.getKaikkiMuodot()) {
//            for (Pala pala : muoto.getPalat()) {
//                g.fill3DRect(pala.getX() * 10, pala.getY() * 10, 10, 10, true);
//            }
//        }
        g.drawString("Pisteet: n/a", 10, 20);

    }

    @Override
    public void paivita() {
        super.repaint();
    }
}
