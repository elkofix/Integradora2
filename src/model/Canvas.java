package model;
import java.lang.Math;
public class Canvas {
    
	public static final int X_LENGTH = 1280;
    public static final int Y_LENGTH = 720;
    public static final int LEVELS_LENGTH = 10;
    private Level[] levels;

    public Canvas() {
        levels = new Level[LEVELS_LENGTH]; 
        for(int i = 0; i<LEVELS_LENGTH; i++){
            levels[i] = new Level(i+1+"", ((1+i)*10)+(int)(Math.round(Math.random()*6))); //INIT 10 LEVELS
        }
	}

    /**
     * gets the levels array
     * @return levels array
     */
    public Level[] getLevels() {
        return levels;
    }
}