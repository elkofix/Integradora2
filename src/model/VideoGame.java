package model;

public class VideoGame {
    private String name;
    private Canvas canvas;
	private Player[] players;


    public Canvas getCanvas() {
        return canvas;
    }

    public String getName() {
        return name;
    }

    public VideoGame(String name){
        this.name = name;
		players = new Player[10];
        canvas = new Canvas(1280, 720);
    }

    public String upgradePlayer(String nick) {
		String msj = "";
		int playerScore = 0;
		int pos = searchPlayerbyNick(nick);
		if (pos != -1){
			playerScore = players[pos].getScore();
			if(players[pos].getCurrentlevel().getReachPoints()>playerScore){
				msj = "Este jugador no puede avanzar de nivel aun";
			}else{
				for(int i = 0; i<canvas.getLevels().length; i++){
					if(playerScore>canvas.getLevels()[i].getReachPoints()){
						players[pos].setCurrentlevel(canvas.getLevels()[i+1]);
						msj = "Felicidades el jugador avanzo hasta el nivel "+canvas.getLevels()[i+1].getId();
					}
				}
				
			}
		}else{
			msj = "No se encontro al usuario";
		}
		return msj;
	}
	public String changePlayerScore(String nick, int newScore) {
		String msj  = "";
		int pos = searchPlayerbyNick(nick);
		if(pos==-1){
			msj = "No se encontro al jugador";
		}else{
			players[pos].setScore(newScore);
			msj = "Puntaje actualizado";
		}
		return msj;
	}

	/**
	 * 
	 * @param Level
	 */
	public String addLevel(int Level) {
		// TODO - implement VideoGame.addLevel
		throw new UnsupportedOperationException();
	}

	public String addPlayer(Player newPlayer) {
		int pos = searchPlayerbyNick(newPlayer.getNickname());
		String msj = "";

		if (pos==-1){
			pos = playerHasEmptyPos();
			if(pos!=-1){
				players[pos] = newPlayer;
				msj = "Jugador agregado";
			}else{
				msj = "Nuestra capacidad de jugadores ya esta en su limite";
			}
			
		}else{
			msj = "El nickname ya exise, usa otro";
		}
		return msj;
	}

	/**
	 * 
	 * @param level
	 * @param Enemy
	 */
	public String addEnemyToLevel(Enemy enemy, String levelid) {
		int pos = searchLevelById(levelid);
		boolean isRepeated;
        int enemyPos;
        String msj ="";
        if (pos==-1){
            msj = "No se encontro el nivel";
        }else{
			isRepeated = canvas.getLevels()[pos].isEnemyRepeated(enemy.getName());
			if (!isRepeated){
				enemyPos = canvas.getLevels()[pos].enemyHasEmptyPos();
            	if(enemyPos ==-1){
                	msj = "La capacidad de enemigos esta en su limite";
           		}else{
                	canvas.getLevels()[pos].getEnemies()[enemyPos]=enemy;
					canvas.getLevels()[pos].calculateDifficulty();
                	msj = "Enemigo agregado";

            	}
			}else{
				msj = "El enemigo ya existe en este nivel";
			}
            
        }
        return msj;
	}

	/**
	 * 
	 * @param level
	 * @param Treasure
	 */
	public String addTreasureToLevel(String levelid, Treasure newTreasure) {
		int pos = searchLevelById(levelid);
		boolean isRepeated;
        int treasurePos;
        String msj ="";
        if (pos==-1){
            msj = "No se encontro el nivel";
        }else{
			isRepeated = canvas.getLevels()[pos].isTreasureRepeated(newTreasure.getName());
			if (!isRepeated){
				treasurePos = canvas.getLevels()[pos].TreasureHasEmptyPos();
            	if(treasurePos ==-1){
                	msj = "La capacidad de enemigos esta en su limite";
           		}else{
                	canvas.getLevels()[pos].getTreasures()[treasurePos]=newTreasure;
					canvas.getLevels()[pos].calculateDifficulty();
                	msj = "Tesoro agregado";

            	}
			}else{
				msj = "El tesoro ya existe en este nivel";
			}
            
        }
        return msj;
	}

	public Player[] calculateTop5() {
		// TODO - implement VideoGame.calculateTop5
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nick
	 */
	public int searchPlayerbyNick(String nick) {
		int pos = -1;
		boolean isFound = false;
		for (int i =0; i<players.length && !isFound; i++){
			if(players[i]!=null){
				if(players[i].getNickname().equalsIgnoreCase(nick)){
					isFound = true;
					pos = i;
				}
			}
		}
		return pos;
	}

	/**
	 * 
	 * @param Level
	 */
	public int searchLevelById(String id) {
        int pos = -1;
        boolean isfound = false;
		for(int i = 0; i<canvas.getLevels().length; i++){
            if(canvas.getLevels()[i]!=null){
                if (canvas.getLevels()[i].getId().equals(id) && !isfound){
                    pos = i;
                    isfound = true;
                }
            }

        }
        return pos;
	}
	public int playerHasEmptyPos(){
		int pos =-1;
		boolean isFree = false;
		for(int i = 0; i<players.length && !isFree; i++){
			if (players[i]==null){
				isFree = true;
				pos = i;
			}
		}
		return pos;
	}

}

