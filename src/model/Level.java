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

	
    /**
     * checks if the enemies array has an empty position
     * @return position if is found, else -1
     */
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
    /**
     * checks if the treasures array has an empty position
     * @return position if is found, else -1
     */
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
    /**
     * Checks if a enemy already exists in the level
     * @param name name of the enemy
     * @return if it's found true, else false
     */
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
    /**
     * Checks if a treasure already exists in the level
     * @param name name of the treasure
     * @return if it's found true, else false
     */
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
    /**
     * sets the difficulty to a new one
     * @param difficulty new difficulty
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * gets the id of the level
     * @return id of the level
     */
    public String getId() {
        return id;
    }
    /**
     * get the treasures array
     * @return treasures array
     */
	public Treasure[] getTreasures() {
        return treasures;
    }
    /**
     * get the enemies array
     * @return enemies array
     */
    public Enemy[] getEnemies() {
        return enemies;
    }
    /**
     * gets the points needed to pass the level
     * @return points needed to pass the level
     */
    public int getReachPoints() {
        return reachPoints;
    }

}