package tetris;

import javax.swing.SwingUtilities;
import tetris.domain.*;
import tetris.peli.Peli;
import tetris.gui.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Käynnistetään Tetris...");
        Peli uusiTetris = new Peli();

        Kayttoliittyma tetrisGui = new Kayttoliittyma(uusiTetris);
        SwingUtilities.invokeLater(tetrisGui);
        
        while (tetrisGui.getPaivitettava() == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
        
        uusiTetris.setPaivitettava(tetrisGui.getPaivitettava());       
        uusiTetris.start();


    }
}
