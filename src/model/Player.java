package model;



public class Player {

	private String nickname;
	private int score;
	private String name;
	private int lifes;
	private Level currentlevel;


	/**
	 * 
	 * @param nickname
	 * @param name
	 */
	public Player(String nickname, String name, Level curreLevel) {
        this.nickname = nickname;
        this.name = name;
        score = 10;
        lifes = 5;
		this.currentlevel = curreLevel;
	}

	public void setCurrentlevel(Level currentlevel) {
		this.currentlevel = currentlevel;
	}

	public Level getCurrentlevel() {
		return currentlevel;
	}
	public String getNickname() {
		return nickname;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
