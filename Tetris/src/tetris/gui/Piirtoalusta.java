package tetris.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
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
        g.fill3DRect(0,0,300,600,false);
        
        for (Muoto muoto : this.tetrisPeli.getKaikkiMuodot()) {
            for (Pala pala : muoto.getPalat()) {
                g.setColor(pala.getVari());
                g.fill3DRect(pala.getX(), pala.getY(), 30, 30, true);
                
            }
        }
        g.setColor(Color.GRAY);
        g.drawString(tetrisPeli.getPistelaskuri().toString(), 10, 20);

    }

    @Override
    public void paivita() {
        super.repaint();
    }
}
