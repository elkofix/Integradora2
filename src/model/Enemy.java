package model;
import java.lang.Math;


public class Enemy {

	private String name;
    private int attack;
	private int loot;
    private double Xpos;
	private double Ypos;
    private Type type;



    public Enemy(String name, int attack, int loot) {
        this.name = name;
        this.attack = attack;
        this.loot = loot;
        type = Type.indefinido;
        Xpos =  (Math.random()*1279);
        Ypos =  (Math.random()*719);
	}

    public int getLoot() {
        return loot;
    }

    public Type getType() {
        return type;
    }
    
	public String getName() {
        return name;
    }
    	

    public void setType(Type type) {
        this.type = type;
    }


}