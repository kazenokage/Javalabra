package tetris.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tiedostojen, lähinnä highscore -listan käsittelyyn tarkoitettu luokka.
 *
 * @author tomminikkanen
 */
public class TiedostoKasittelija {
    
    private File highScoreList;

    public TiedostoKasittelija() {
        highScoreList = new File("highscore.txt");
        if (!highScoreList.exists()) {
            try {
                highScoreList.createNewFile();
            } catch (IOException ex) {
                System.out.println("Tiedostoa ei saatu luotua!");
            }
        }
    }
    
    
    /**
     * Lukee highscoret tiedostosta, ja palauttaa ne ArrayListinä.
     * 
     * @return ArrayList<Integer> luettu highscore -lista
     * @throws Exception 
     */
    public ArrayList<Integer> lueHighscore() throws Exception {
        ArrayList<Integer> pisteet = new ArrayList<>();
        Scanner lukija = new Scanner(highScoreList);
        while (lukija.hasNextLine()) {
            pisteet.add(Integer.parseInt(lukija.nextLine()));
        }
        return pisteet;
    }
    
    
    /**
     * Tallentaa halutut pisteet highscore-tiedostoon.
     * 
     * @param pisteet tallennettavat pisteet
     */
    public void tallennaHighscore(ArrayList<Integer> pisteet) {
        FileWriter kirjoittaja;
        try {
            kirjoittaja = new FileWriter(highScoreList);
            for (Integer piste : pisteet) {
                kirjoittaja.write(""+piste+"\n");
            }
            kirjoittaja.close();
        } catch (IOException ex) {
            System.out.println("Kirjoittajan luominen ei onnistunut.");
        }
    }
}
