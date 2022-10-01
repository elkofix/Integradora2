package model;

public class Canvas {

	private int Xlength;
    private int Ylength;


    public Canvas(int Xlength, int Ylength) {
		this.Xlength = Xlength;
        this.Ylength = Ylength;
	}

    public int getYlength() {
        return Ylength;
    }

    public int getXlength() {
        return Xlength;
    }
}