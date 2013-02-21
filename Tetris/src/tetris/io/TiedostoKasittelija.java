package tetris.io;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Tiedostojen, lähinnä highscore -listan käsittelyyn tarkoitettu luokka.
 *
 * @author tomminikkanen
 */
public class TiedostoKasittelija {

    private FileWriter kirjoittaja;
    private File highScoreList;

    public TiedostoKasittelija() {
        highScoreList = new File("highscore.txt");
    }
    /**
     * Lukee highscoret tiedostosta, ja palauttaa ne ArrayListinä.
     * 
     * @return ArrayList<Integer> luettu highscore -lista
     * @throws Exception 
     */
    public ArrayList<Integer> lueHighscore() throws Exception {
        Scanner lukija = new Scanner(highScoreList);
        System.out.println(highScoreList.isFile());
        System.out.println(highScoreList.length());
        System.out.println("Yritetään lukea:");
        while (lukija.hasNextLine()) {
            System.out.println("Seuraava rivi on:");
            System.out.println(lukija.nextLine());
        }
        return null;
    }
    
    
    /**
     * Tallentaa halutut pisteet highscore-tiedostoon.
     * 
     * @param pisteet tallennettavat pisteet
     */
    public void tallennaHighscore(ArrayList<Integer> pisteet) {
    }
}
