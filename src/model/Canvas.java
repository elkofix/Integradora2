package model;
import java.lang.Math;
public class Canvas {

	private int Xlength;
    private int Ylength;
    private Level[] levels;

    /**
     * Constructor of canvas class
     * @param Xlength Xpixels
     * @param Ylength Ypixels
     */
    public Canvas(int Xlength, int Ylength) {
		this.Xlength = Xlength;
        this.Ylength = Ylength;
        levels = new Level[10];
        for(int i = 0; i<levels.length; i++){
            levels[i] = new Level(i+1+"", ((1+i)*10)+(int)(Math.round(Math.random()*6)));
        }
	}
    /**
     * gets de Ypixels
     * @return Ypixels
     */
    public int getYlength() {
        return Ylength;
    }
    /**
     * get de Xpixels
     * @return Xpixels
     */
    public int getXlength() {
        return Xlength;
    }

    /**
     * gets the levels array
     * @return levels array
     */
    public Level[] getLevels() {
        return levels;
    }
}