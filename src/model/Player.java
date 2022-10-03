package model;



public class Player {

	private String nickname;
	private int score;
	private String name;
	private int lifes;
	private Level currentlevel;


	/**
	 * Constructor of Player class
	 * @param nickname nickname of the player
	 * @param name name of the player
	 * @param curreLevel level where the player starts
	 */
	public Player(String nickname, String name, Level curreLevel) {
        this.nickname = nickname;
        this.name = name;
        score = 10;
        lifes = 5;
		this.currentlevel = curreLevel;
	}
	/**
	 * set the current level to a new one
	 * @param currentlevel new Level to be set
	 */
	public void setCurrentlevel(Level currentlevel) {
		this.currentlevel = currentlevel;
	}
	/**
	 * gets the current leveñ
	 * @return current Level
	 */
	public Level getCurrentlevel() {
		return currentlevel;
	}
	/**
	 * gets the nickname of the player
	 * @return nickname of the player
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * gets the score of the player
	 * @return score of the player
	 */
	public int getScore() {
		return score;
	}
	/**
	 * set the score of the player to a new one
	 * @param score new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

}
