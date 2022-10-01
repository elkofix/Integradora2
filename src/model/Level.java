package model;

import java.security.PublicKey;

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

	public Difficulty calculateDifficulty() {
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
        return newDifficulty;
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
	public int countEnemiesByType() {
		// TODO - implement Level.countEnemiesByType
		throw new UnsupportedOperationException();
	}

	public int countTreasuresTotal() {
		// TODO - implement Level.countTreasuresTotal
		throw new UnsupportedOperationException();
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

	public int getModeTreasure() {
		// TODO - implement Level.getModeTreasure
		throw new UnsupportedOperationException();
	}

}