package tetris.domain;

/**
 * Muotojen kääntämiseen tarkoitettu luokka. Pitää sisällään tiedon muotojen eri
 * asennoista.
 *
 * @author tomminikkanen
 */
public class MuotoKaantaja {

    public int[][][] kaantoMatriisi;

    public MuotoKaantaja() {
        this.kaantoMatriisi = new int[][][]{
            {
                {30, -60, 30, -30, 30, 0, 30, 30},
                {-30, 30, 0, 30, 30, 30, 60, 30},
                {60, -30, 60, 0, 60, 30, 60, 60},
                {-60, 60, -30, 60, 0, 60, 30, 60}
            },
            {
                {30, 0, 60, 0, 60, 30, 60, 60},
                {-30, 60, 0, 60, 30, 60, 30, 30},
                {0, -30, 0, 0, 0, 30, 30, 30},
                {0, 0, 0, 30, 30, 0, 60, 0}
            },
            {
                {0, -30, 30, -30, 0, 0, 0, 30},
                {0, 0, 30, 0, 60, 0, 60, 30},
                {60, 0, 60, 30, 60, 60, 30, 60},
                {-30, 30, -30, 60, 0, 60, 30, 60}
            },
            {
                {0, 0, 0, 30, 30, 30, 30, 60},
                {30, 0, 60, 0, 0, 30, 30, 30},
                {0, 0, 0, 30, 30, 30, 30, 60},
                {30, 0, 60, 0, 0, 30, 30, 30}
            },
            {
                {60, 0, 30, 30, 60, 30, 30, 60},
                {-30, 0, 0, 0, 0, 30, 30, 30},
                {60, 0, 30, 30, 60, 30, 30, 60},
                {-30, 0, 0, 0, 0, 30, 30, 30}
            },
            {
                {0, 30, -30, 60, 0, 60, 30, 60},
                {0, -30, 0, 0, 0, 30, 30, 0},
                {0, 0, 30, 0, 60, 0, 30, 30},
                {30, 30, 60, 0, 60, 30, 60, 60}
            },
            {
                {0, -30, 30, -30, 0, 0, 30, 0},
                {0, -30, 30, -30, 0, 0, 30, 0},
                {0, -30, 30, -30, 0, 0, 30, 0},
                {0, -30, 30, -30, 0, 0, 30, 0}
            }
        };
    }

    /**
     * Muuttaa halutun muodon asentoa (muuttujaa).
     *
     * @param kaannettava Muoto, jonka asentoa halutaan muuttaa.
     * @param myotaPaivaan Käännetäänkö myötäpäivään (true) vai vastapäivään
     * (false).
     */
    private void muutaAsento(Muoto kaannettava, boolean myotaPaivaan) {
        if (myotaPaivaan) {
            if (kaannettava.getAsento() == 3) {
                kaannettava.setAsento(0);
            } else {
                kaannettava.setAsento(kaannettava.getAsento() + 1);
            }
        } else if (!myotaPaivaan) {
            if (kaannettava.getAsento() == 0) {
                kaannettava.setAsento(3);
            } else {
                kaannettava.setAsento(kaannettava.getAsento() - 1);
            }
        }
    }

    /**
     * Kääntää muotoa siirtämällä kaikkia muodon palasia kääntömatriisin
     * mukaisesti.
     *
     * @param kaannettava Muoto jonka paloja käännetään.
     * @param myotaPaivaan Käännetäänkö myötäpäivään (true) vai vastapäivään
     * (false).
     */
    public void kaannaMuoto(Muoto kaannettava, boolean myotaPaivaan) {
        muutaAsento(kaannettava, myotaPaivaan);
        int kiintoX = etsiKiintoPiste(kaannettava)[0];
        int kiintoY = etsiKiintoPiste(kaannettava)[1];
        int j = 0;
        for (Pala pala : kaannettava.getPalat()) {
            pala.setXY(kiintoX + kaantoMatriisi[kaannettava.getTyyppi()][kaannettava.getAsento()][j], kiintoY + kaantoMatriisi[kaannettava.getTyyppi()][kaannettava.getAsento()][j + 1]);
            j += 2;
        }
    }

    /**
     *
     * Etsii muodon palojen pienimmän x- ja pienimmän y-koordinaatin.
     *
     * @param tarkistettava Muoto jonka koordinaatteja tarkastellaan.
     * @return Palautetaan taulukko jossa pienimmät x- ja y-koordinaatit.
     */
    private int[] etsiKiintoPiste(Muoto tarkistettava) {
        int pieninX = tarkistettava.getPalat().get(0).getX();
        int pieninY = tarkistettava.getPalat().get(0).getY();
        for (Pala pala : tarkistettava.getPalat()) {
            if (pala.getX() < pieninX) {
                pieninX = pala.getX();
            }
            if (pala.getY() < pieninY) {
                pieninY = pala.getY();
            }
        }
        return new int[]{pieninX, pieninY};
    }
}
