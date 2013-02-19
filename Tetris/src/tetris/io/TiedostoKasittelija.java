package tetris.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TiedostoKasittelija {

    private FileWriter kirjoittaja;
    private File tiedosto;

    public TiedostoKasittelija() {
        tiedosto = new File("highscore.txt");
        try {
            kirjoittaja = new FileWriter(tiedosto);
        } catch (IOException ex) {
            System.out.println("Tiedostoa ei saatu avattua /:");
        }
    }

    public ArrayList<Integer> lueHighscore() throws Exception {
        Scanner lukija = new Scanner(tiedosto);
        ArrayList<Integer> palautettava = new ArrayList<>();
        while (lukija.hasNextLine()) {
            palautettava.add(Integer.parseInt(lukija.nextLine()));
        }
        lukija.close();
        return palautettava;
    }

    public void tallennaHighscore(ArrayList<Integer> pisteet) {
        System.out.println(pisteet);
        Collections.sort(pisteet);
        for (Integer integer : pisteet) {
            try {
                kirjoittaja.write(integer + "\n");
            } catch (IOException ex) {
                System.out.println("Tiedoston kirjoittaminen ei onnistunut /:");
            }
        }
        try {
            kirjoittaja.close();
        } catch (IOException ex) {
            System.out.println("Tiedoston sulkeminen ei onnistunut /:");
        }
    }
}
