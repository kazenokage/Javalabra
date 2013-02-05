package tetris.domain;

import java.awt.Color;
import java.util.Random;
import tetris.Suunta;

/**
 * Tetriksessä käytettyjen muotojen luontiin tarkoitettu luokka.
 * 
 * @author tomminikkanen
 */

public class MuotoGeneraattori {
    
    private int[][] muodot;
    private Random random;
    private Color[] varit;
    
    public MuotoGeneraattori() {
        random = new Random();
        muodot = new int[][]{
            {0,0,0,30,0,60,0,90},
            {-30,0,0,0,0,30,0,60},
            {0,0,30,0,0,30,0,60},
            {-30,-30,-30,0,0,0,0,30},
            {30,-30,0,0,30,0,0,30},
            {0,-30,-30,0,0,0,30,0},
            {0,-30,30,-30,0,0,30,0}
        };
        varit = new Color[]{Color.CYAN,Color.ORANGE,Color.BLUE,Color.GREEN,Color.RED,Color.MAGENTA,Color.YELLOW};
    }
    /**
     * Luo uuden satunnaisen muodon käyttämällä luokassa määriteltyjä vaihtoehtoja
     * @param alkux muodon alku x-koordinaatti
     * @param alkuy muodon alku y-koordinaatti
     * @return 
     */
    public Muoto luoUusi(int alkux, int alkuy) {
        int muoto = random.nextInt(muodot.length);
        Muoto luotava = new Muoto(Suunta.ALAS);
        int j = 0;
        for (int i = 0; i < 4; i++) {
            Pala pala = new Pala(alkux+muodot[muoto][j],alkuy+muodot[muoto][j+1], varit[muoto]);
            luotava.lisaaPala(pala);
            j+=2;
        }
        return luotava;
    }
    
}
