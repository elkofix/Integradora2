
package model;
import java.lang.Math;


public class Enemy {

	private String name;
	private double attack;
	private double loot;


    private double Xpos;
	private double Ypos;
    private Type type;

	public Enemy(String name, int attack, int loot, int type) {
        this.name = name;
        this.attack = attack;
        this.loot = loot;
        this.type = giveType(type);
        Xpos =  (Math.random()*719);
        Ypos =  (Math.random()*1079);
	}

    public Type giveType(int type){
        Type newType = Type.indefinido;
        if(type == 1){
            newType = Type.ogro;
        }
        if(type == 2){
            newType = Type.abstracto;
        }
        if(type == 3){
            newType = Type.jefe;
        }
        if(type == 4){
            newType = Type.magico;
        }
    
        return newType;
    }

    public double getLoot() {
        return loot;
    }

}