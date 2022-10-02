

package model;

import java.lang.Math;

public class Treasure {

	private String name;

    private String url;
    private int value;
    private double Xpos;
	private double Ypos;

	public Treasure(String name, String url, int value) {
		this.name = name;
        this.url = url;
        this.value = value;
        Xpos =  (Math.random()*1279);
        Ypos =  (Math.random()*719);
        
	}   

    public int getValue() {
            return value;
        }
    public String getUrl() {
            return url;
        }
        public String getName() {
            return name;
        }
}