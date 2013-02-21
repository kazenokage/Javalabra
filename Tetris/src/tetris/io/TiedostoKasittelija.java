package tetris.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TiedostoKasittelija {

    private FileWriter kirjoittaja;
    private File highScoreList;

    public TiedostoKasittelija() {  
            highScoreList = new File("highscore.txt");
    }

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

    public void tallennaHighscore(ArrayList<Integer> pisteet) {

    }
}
