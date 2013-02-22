package tetris.domain;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import tetris.Suunta;

/**
 * Tetriksessä käytettyjen muotojen luontiin tarkoitettu luokka. Pitää sisällään
 * tiedon mahdollisista muodoista, niiden palojen sijainnista sekä väristä.
 *
 * @author tomminikkanen
 */
public class MuotoGeneraattori {

    private int[][] muodot;
    private Random random;
    private Color[] varit;
    private Image[] palaKuvat;

    /**
     * Konstruktori määrittelee muotomatriisin ja värit/kuvat paloille.
     */
    public MuotoGeneraattori() {
        random = new Random();
        muodot = new int[][]{
            {0, -30, 0, 0, 0, 30, 0, 60}, // I
            {-30, -30, 0, -30, 0, 0, 0, 30}, // L 
            {0, 0, 30, 0, 0, 30, 0, 60}, // J
            {-30, -30, -30, 0, 0, 0, 0, 30}, // S
            {30, -30, 0, 0, 30, 0, 0, 30}, // Z 
            {0, -30, -30, 0, 0, 0, 30, 0}, // T
            {0, -30, 30, -30, 0, 0, 30, 0} // O
        };
        varit = new Color[]{Color.CYAN, Color.ORANGE, Color.BLUE, Color.GREEN, Color.RED, Color.MAGENTA, Color.YELLOW};

        Image syaani = new ImageIcon(getClass().getResource("/tetris/resurssit/pala_syaani.png")).getImage();
        Image oranssi = new ImageIcon(getClass().getResource("/tetris/resurssit/pala_oranssi.png")).getImage();
        Image sininen = new ImageIcon(getClass().getResource("/tetris/resurssit/pala_sininen.png")).getImage();
        Image vihrea = new ImageIcon(getClass().getResource("/tetris/resurssit/pala_vihrea.png")).getImage();
        Image punainen = new ImageIcon(getClass().getResource("/tetris/resurssit/pala_punainen.png")).getImage();
        Image purppura = new ImageIcon(getClass().getResource("/tetris/resurssit/pala_purppura.png")).getImage();
        Image keltainen = new ImageIcon(getClass().getResource("/tetris/resurssit/pala_keltainen.png")).getImage();
        palaKuvat = new Image[]{syaani, oranssi, sininen, vihrea, punainen, purppura, keltainen};
    }

    /**
     * Luo uuden satunnaisen muodon käyttämällä luokassa määriteltyjä
     * vaihtoehtoja
     *
     * @param alkux muodon alku x-koordinaatti
     * @param alkuy muodon alku y-koordinaatti
     * @return
     */
    public Muoto luoUusi(int alkux, int alkuy) {
        int muoto = random.nextInt(muodot.length);
        Muoto luotava = new Muoto(Suunta.ALAS, muoto, 0);
        int j = 0;
        for (int i = 0; i < muodot[muoto].length / 2; i++) {
            Pala pala = new Pala(alkux + muodot[muoto][j], alkuy + muodot[muoto][j + 1], varit[muoto], palaKuvat[muoto]);
            luotava.lisaaPala(pala);
            j += 2;
        }
        return luotava;
    }
}
