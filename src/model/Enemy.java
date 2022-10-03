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
    /**
     * gets the points it substracts to the player if its victorious
     * @return points it substracts to the player if its victorious
     */
    public int getAttack() {
        return attack;
    }
    /**
     * gets the points it adds to the player if its defeated
     * @return points it adds to the player if its defeated
     */
    public int getLoot() {
        return loot;
    }
    /**
     * gets the type of enemy
     * @return type of enemy
     */
    public Type getType() {
        return type;
    }
    /**
     * gets the name of the enemy
     * @return name of the enemy
     */
	public String getName() {
        return name;
    }
    /**
     * sets the type of enemy to a new one
     * @param type new type of enemy
     */
    public void setType(Type type) {
        this.type = type;
    }


}