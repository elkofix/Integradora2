

package model;

import java.lang.Math;

public class Treasure {

	private String name;
    private String url;
    private int value;
    private double Xpos;
	private double Ypos;

    /**
     * Constructor of Treasure class
     * @param name name of the treasure
     * @param url url of the treasure img
     * @param value diamonds in the treasure
     */
	public Treasure(String name, String url, int value) {
		this.name = name;
        this.url = url;
        this.value = value;
        Xpos =  (Math.random()*1279)+1;
        Ypos =  (Math.random()*719)+1;
        
	}   
    /**
     * gets the diamonds in the treasure
     * @return diamons in the treasure
     */
    public int getValue() {
            return value;
        }
    /**
     * gets the url image of the treasure
     * @return url of the treasure image
     */
    public String getUrl() {
            return url;
    }
    /**
     * Gets the name of the Treasure
     * @return name of the treasure
     */
    public String getName() {
        return name;
    }
}