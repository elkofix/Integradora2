package model;
import java.lang.Math;
public class Canvas {

	private int Xlength;
    private int Ylength;
    private Level[] levels;



    public Canvas(int Xlength, int Ylength) {
		this.Xlength = Xlength;
        this.Ylength = Ylength;
        levels = new Level[10];
        for(int i = 0; i<levels.length; i++){
            levels[i] = new Level(i+1+"", ((1+i)*10)+(int)(Math.round(Math.random()*6)));
        }
	}

    public int getYlength() {
        return Ylength;
    }

    public int getXlength() {
        return Xlength;
    }

    
    public Level[] getLevels() {
        return levels;
    }
}