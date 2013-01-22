
package tetris.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import tetris.Suunta;
import tetris.domain.Muoto;

public class Peli extends Timer implements ActionListener {
    
    private List<Muoto> muodot;
    private int leveys;
    private int korkeus;
    private boolean jatkuu;
    private int nopeus;
    
    private int sykli = 0;
    
    
    public Peli() {
        super(1000,null);
        this.muodot = new ArrayList<Muoto>();
        this.jatkuu = true;
        this.nopeus = 1000;
        
        addActionListener(this);
        setInitialDelay(1000);
    }
    
    public boolean jatkuu() {
        return jatkuu;
    }
    
    public void lisaaMuoto() {
        muodot.add(new Muoto(50,50,Suunta.ALAS));
    }
    
    public boolean tarkistaRivi() {
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        sykli++;
        
        if (!jatkuu) {
            System.out.println("Game over!");
            return;
        }
        
        System.out.println("Peli käy! (sykli #"+sykli+", kesto "+nopeus+" ms)");
        
        
    }
    
}
