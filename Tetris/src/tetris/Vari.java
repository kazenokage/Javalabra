package tetris;

public enum Vari {
    
    MUSTA(0,0,0),
    VALKOINEN(255,255,255);
    
    private final int r;
    private final int g;
    private final int b;
    
    Vari(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
}
