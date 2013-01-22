package tetris.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.Dimension;

public class Kayttoliittyma implements Runnable {
    
    private JFrame frame;
    
    public Kayttoliittyma() {
        
    }
    
    @Override
    public void run() {
        frame = new JFrame("Matopeli");
        int leveys = 400;
        int korkeus = 700;
        
        frame.setPreferredSize(new Dimension(leveys, korkeus));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
        // luoKomponentit(frame.getContentPane());
 
        frame.pack();
        frame.setVisible(true);
    }

    
    
}
