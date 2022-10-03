package model;

import java.util.ArrayList;

public class Level {

	private String id;

    private int reachPoints;

    private Difficulty difficulty;
    private Enemy[] enemies;
    private Treasure[] treasures;


    /**
	 * Constructor of Level class
	 * @param id id of the level
	 * @param reachPoints points to reach the level
	 */
	public Level(String id, int reachPoints) {
		this.id = id;
        this.reachPoints = reachPoints;
        this.difficulty = Difficulty.indefinida;
        enemies = new Enemy[25];
        treasures = new Treasure[50];

	}
    /**
     * Calculates the dificulty of the level depenging on the points that treasures and enemies give
     */
	public void calculateDifficulty() {
		Difficulty newDifficulty = Difficulty.indefinida;
        if(countEnemiesLoot()<countTresuresValue()){
            newDifficulty = Difficulty.bajo;
        }
        if(countEnemiesLoot()==countTresuresValue()){
            newDifficulty = Difficulty.medio;
        }
        if(countEnemiesLoot()>countTresuresValue()){
            newDifficulty = Difficulty.alto;
        }
        setDifficulty(newDifficulty);
	}
    /**
     * counts all the ponints the enemies give
     * @return total of the point the enemies in the level give
     */
    public int countEnemiesLoot(){
        int loot = 0;
        for (int i = 0; i<enemies.length; i++){
            if (enemies[i] != null){
                loot+=enemies[i].getLoot();
            }
        }
        return loot;
    }

    /**
     * counts the enemies by a specific type
     * @param type type of enemy
     * @return count of enemies of a specific type
     */
    
	public int countEnemiesByType(Type type) {
        int enemiesFound = 0;
        for (int i = 0; i<enemies.length; i++){
            if (enemies[i] != null){
                if (enemies[i].getType()==type){
                    enemiesFound++;

                }    
            }
        }
		return enemiesFound;
	}
    
    /**
     *Counts the total diamonds the treasures give 
     * @return count of the diamonds every treasure give
     */
	public int countTresuresValue() {
		int value = 0;
        for (int i = 0; i<treasures.length; i++){
            if (treasures[i] != null){
                value+=treasures[i].getValue();
            }
        }
        return value;
	}

	

    public int enemyHasEmptyPos(){
        int pos = -1;
        boolean isFree = false;
            for (int i=0; i<enemies.length && !isFree; i++){
                if (enemies[i] == null){
                    pos = i;
                    isFree = true;
                }
            }
        return pos;
        
    }

    public int TreasureHasEmptyPos(){
        int pos = -1;
        boolean isFree = false;
            for (int i=0; i<treasures.length && !isFree; i++){
                if (treasures[i] == null){
                    pos = i;
                    isFree = true;
                }
            }
        return pos;
        
    }


    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isEnemyRepeated(String name){
        boolean isRepeated = false;
            for(int i = 0; i<enemies.length && isRepeated; i++){
                if(enemies[i]!=null){
                    if(enemies[i].getName().equalsIgnoreCase(name)){
                        isRepeated = true;
                    }
                }
            }
        return isRepeated;
    }
    public boolean isTreasureRepeated(String name){
        boolean isRepeated = false;
            for(int i = 0; i<treasures.length && isRepeated; i++){
                if(treasures[i]!=null){
                    if(treasures[i].getName().equalsIgnoreCase(name)){
                        isRepeated = true;
                    }
                }
            }
        return isRepeated;
    }
    public String getId() {
        return id;
    }
    
	public Treasure[] getTreasures() {
        return treasures;
    }
    public Enemy[] getEnemies() {
        return enemies;
    }
    public int getReachPoints() {
        return reachPoints;
    }

}