package tetris.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tetris.domain.Muoto;
import tetris.domain.Pala;
import tetris.peli.Peli;

/**
 * Piirtoalusta tetrikselle
 *
 * @author tomminikkanen
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli tetrisPeli;
    Image taustakuva;
    Image pala_purppura;

    public Piirtoalusta(Peli tetrisPeli) {
        this.tetrisPeli = tetrisPeli;
        taustakuva = new ImageIcon(this.getClass().getResource("../bg.jpg")).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(taustakuva, 0, 0, this);

        for (Muoto muoto : this.tetrisPeli.getKaikkiMuodot()) {
            for (Pala pala : muoto.getPalat()) {
                g.setColor(pala.getVari());
                g.drawImage(pala.getKuva(), pala.getX(), pala.getY(), null);
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
