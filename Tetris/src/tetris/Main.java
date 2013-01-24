package tetris;

import javax.swing.SwingUtilities;
import tetris.domain.*;
import tetris.peli.Peli;
import tetris.gui.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Käynnistetään Tetris...");
        Peli uusiTetris = new Peli();

        Kayttoliittyma tetrisGui = new Kayttoliittyma();
        SwingUtilities.invokeLater(tetrisGui);

        uusiTetris.start();

        System.out.println(uusiTetris.getAktiivinenMuoto());


    }
}
