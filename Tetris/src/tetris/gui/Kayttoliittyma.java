package tetris.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.Dimension;
import tetris.peli.Peli;

/**
 * Tetriksen käyttöliittymä, luo tarvittavat komponentit.
 *
 * @author tomminikkanen
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Piirtoalusta alusta;
    private Peli tetrisPeli;

    public Kayttoliittyma(Peli peli) {
        this.tetrisPeli = peli;
    }

    /**
     * Luo uuden piirtoalustan ja lisää sen annettuun containeriin. Kytkee myös
     * näppäimistönkuuntelijan käyttöliittymään.
     *
     * @param container
     */
    public void luoKomponentit(Container container) {
        Piirtoalusta uusiAlusta = new Piirtoalusta(this.tetrisPeli);
        this.alusta = uusiAlusta;
        container.add(uusiAlusta);
        frame.addKeyListener(new Nappaimistonkuuntelija(tetrisPeli, alusta));
    }

    public Paivitettava getPaivitettava() {
        return alusta;
    }

    @Override
    public void run() {
        frame = new JFrame("Tetris");
        int leveys = 500;
        int korkeus = 600;

        frame.setPreferredSize(new Dimension(leveys, korkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        luoKomponentit(frame.getContentPane());

        frame.setVisible(true);
        frame.pack();

    }

    public JFrame getFrame() {
        return frame;
    }
}
