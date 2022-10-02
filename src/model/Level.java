package model;

import java.util.ArrayList;

public class Level {

	private String id;

    private int reachPoints;

    private Difficulty difficulty;
    private Enemy[] enemies;
    private Treasure[] treasures;


    /**
	 * 
	 * @param id
	 * @param reachPoints
	 */
	public Level(String id, int reachPoints) {
		this.id = id;
        this.reachPoints = reachPoints;
        this.difficulty = Difficulty.indefinida;
        enemies = new Enemy[25];
        treasures = new Treasure[50];

	}

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
    public int countEnemiesLoot(){
        int loot = 0;
        for (int i = 0; i<enemies.length; i++){
            if (enemies[i] != null){
                loot+=enemies[i].getLoot();
            }
        }
        return loot;
    }
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

	public int countTreasuresTotal() {
        int value = 0;
        for (int i = 0; i<treasures.length; i++){
            if (treasures[i] != null){
                value++;
            }
        }
        return value;
	}

	public int countTresuresValue() {
		int value = 0;
        for (int i = 0; i<treasures.length; i++){
            if (treasures[i] != null){
                value+=treasures[i].getValue();
            }
        }
        return value;
	}

	public int[] getModeTreasure() {
        ArrayList<int[]> treasureValues = new ArrayList<int[]>();
        int value = 0;
        int[] values = new int[]{0, 0};
        for (int i = 0; i<treasures.length; i++){
            if (treasures[i] != null){
                value=treasures[i].getValue();
                for(int j = 0; j<treasureValues.size(); j++){
                    if (value==treasureValues.get(j)[0]){
                        treasureValues.get(j)[1]+=1;
                    }else{
                        values[0] = value;
                        values[1] = 1;
                        treasureValues.add(values);
                    }
                }
            }
        }
        int[] max = treasureValues.get(0);
        int[] min;
        for(int i = 1; i<treasureValues.size(); i++){
            min = treasureValues.get(i);
            if(min[1]>max[1]){
                max = min;
            }
        }
        return max;
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