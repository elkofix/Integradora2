package model;

import java.net.Socket;

public class Player {

	private String nickname;
	private int score;
	private String name;
	private int lifes;
	private Level currentlevel;
    private int goal;

	/**
	 * 
	 * @param nickname
	 * @param name
	 */
	public Player(String nickname, String name) {
        this.nickname = nickname;
        this.name = name;
        score = 10;
        lifes = 5;
        
	}

}
