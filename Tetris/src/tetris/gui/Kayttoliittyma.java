package tetris.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.Dimension;
import tetris.peli.Peli;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Piirtoalusta alusta;
    private Peli tetrisPeli;

    public Kayttoliittyma() {
        // tehdään joo.
    }

    public void luoKomponentit(Container container) {
        Piirtoalusta uusiAlusta = new Piirtoalusta(this.tetrisPeli);
        this.alusta = uusiAlusta;
        container.add(uusiAlusta);
    }

    @Override
    public void run() {
        frame = new JFrame("Tetris");
        int leveys = 400;
        int korkeus = 700;

        frame.setPreferredSize(new Dimension(leveys, korkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
