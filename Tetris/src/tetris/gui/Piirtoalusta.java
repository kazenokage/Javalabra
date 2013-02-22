package tetris.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tetris.domain.Pala;
import tetris.peli.Peli;

/**
 * Piirtoalusta tetrikselle.
 *
 * Hoitaa elementtien varsinaisen piirtämisen.
 *
 * @author tomminikkanen
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli tetrisPeli;
    Image taustakuva;
    Image verho;
    Font pisteFontti;

    public Piirtoalusta(Peli tetrisPeli) {
        this.tetrisPeli = tetrisPeli;
        taustakuva = new ImageIcon(this.getClass().getResource("/tetris/resurssit/bg.jpg")).getImage();
        verho = new ImageIcon(this.getClass().getResource("/tetris/resurssit/bg_curtain.png")).getImage();
        pisteFontti = new Font("Helvetica", Font.PLAIN, 18);
    }

    /**
     * Piirretään tausta, muodot ja pisteet.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(taustakuva, 0, 0, this);

        for (Pala pala : tetrisPeli.getStaattiset()) {
            g.setColor(pala.getVari());
            g.drawImage(pala.getKuva(), pala.getX(), pala.getY(), null);
        }

        for (Pala pala : tetrisPeli.getAktiivinenMuoto().getPalat()) {
            g.setColor(pala.getVari());
            g.drawImage(pala.getKuva(), pala.getX(), pala.getY(), null);
        }

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(pisteFontti);
        g2d.setColor(Color.GRAY);
        g2d.drawString(tetrisPeli.getPistelaskuri().toString(), 318, 85);
        g2d.drawString(tetrisPeli.getPistelaskuri().getRivitMerkkijono(), 318, 157);
        g2d.drawString(tetrisPeli.getPistelaskuri().getTasoMerkkijono(), 318, 229);

        if (tetrisPeli.gameOver()) {
            g.drawImage(verho, 0, 0, this);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawString("Your points: " + tetrisPeli.getPistelaskuri().getPisteet(), 80, 260);
            g2d.drawString("Current highscore: " + tetrisPeli.getParhaatPisteet(), 55, 290);
        }

    }

    @Override
    public void paivita() {
        super.repaint();
    }
}
