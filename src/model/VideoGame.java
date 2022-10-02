package model;

import java.util.ArrayList;

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
		players = new Player[20];
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
	public String showTreasuresAndEnemys(String id){
		int pos = searchLevelById(id);
		String msj = "";
		if (pos !=-1){
			for (int i = 0; i<canvas.getLevels()[pos].getTreasures().length; i++){
				if(canvas.getLevels()[pos].getTreasures()[i]!=null){
					msj += canvas.getLevels()[pos].getTreasures()[i].getName()+" Diamantes: "+canvas.getLevels()[pos].getTreasures()[i].getValue()+", \n";
				}	
			}
			for (int i = 0; i<canvas.getLevels()[pos].getEnemies().length; i++){
				if(canvas.getLevels()[pos].getEnemies()[i]!=null){
					msj += canvas.getLevels()[pos].getEnemies()[i].getName()+" Ataque: "+canvas.getLevels()[pos].getEnemies()[i].getAttack()+" Botin "+canvas.getLevels()[pos].getEnemies()[i].getLoot()+", \n";
				}	
			}
		}else{
			msj ="No se encontro el nivel";
		}
		return msj;
	}

	/**
	 * 
	 * @param level
	 * @param Enemy
	 */
	public String addEnemyToLevel(Enemy enemy, String levelid, int type) {
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
					enemy.setType(giveType(type));
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

	public String calculateTop5() {
		String scoreboard = "Top 5 jugadores \n";	
		int min = 0;
		int max = 0;
		int pos = -1;
		ArrayList<Player> holdPlayers = new ArrayList<Player>();
		Player[] topPlayers = new Player[5];
		for (int i =0; i<players.length; i++){
			if (players[i]!=null){
				holdPlayers.add(players[i]);
			}	
		}
		for(int j = 0; j<topPlayers.length && holdPlayers.size()>0; j++){
			max = 0;
			for(int i=0; i<holdPlayers.size(); i++){
				min = holdPlayers.get(i).getScore();
				if(min>max){
					max = min;
					pos = i;
				}
			}
			if(pos!=-1){ 
				topPlayers[j] = holdPlayers.get(pos);
				scoreboard+="Puesto "+(j+1)+": "+holdPlayers.get(pos).getNickname()+" "+holdPlayers.get(pos).getScore()+" Puntos \n";
				holdPlayers.remove(pos);
			}
		}
		if (pos == -1){
			scoreboard = "No se encontraron jugadores";
		}
		return scoreboard;
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

	public String getAllTreasures(){
		int total = 0;
		String msj ="";
		for (int i = 0; i<canvas.getLevels().length; i++){
			total+=canvas.getLevels()[i].countTresuresValue();
		}
		return msj = "En todos los niveles hay un total de "+total+" Diamantes";
	}
	public String getAllEnemiesType(int type){
		Type toSearch = giveType(type);
		int total = 0;
		String msj;
		for (int i = 0; i<canvas.getLevels().length; i++){
			total +=canvas.getLevels()[i].countEnemiesByType(toSearch);
			
		}
		return msj = "En todos los niveles hay un total de "+total+" "+toSearch+"(s)";
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

	public String getModeTreasure() {
		String msj = "";
        ArrayList<int[]> treasureValues = new ArrayList<int[]>();
        int value = 0;
		boolean isAdded;
        int[] values = new int[]{0, 0};
		treasureValues.add(values);
		for(int i = 0; i<canvas.getLevels().length; i++){
			for(int j = 0; j<canvas.getLevels()[0].getTreasures().length; j++){
				if(canvas.getLevels()[i].getTreasures()[j]!=null){
					values = new int[]{canvas.getLevels()[i].getTreasures()[j].getValue(), 1};
					isAdded = true;
					for(int k = 0; k<treasureValues.size(); k++){
						if(treasureValues.get(k)[0]==values[0]){
							treasureValues.get(k)[1]+=1;
							isAdded = false;
						}
					}
					if (isAdded == true){
						treasureValues.add(values);	
					}
					
				}
			}
		}
			int[] max = treasureValues.get(0);
			int[] min;
			for(int i = 1; i<treasureValues.size(); i++){
				min = treasureValues.get(i)	;
				if(min[1]>max[1]){
					max = min;
				}
			}
        msj ="El tesoro que mas se repite en todos los niveles son los que contienen "+max[0]+" Diamantes \n"+
		"Total: "+max[1];
        return msj;
	}
	public String getMaxEnemy(){
		String msj="No se encontraron enemigos";
		int pos = -1;
		int enemyPos= -1;
		int max = 0;
		int min = 0;
		for(int i = 0; i<canvas.getLevels().length; i++){
			for(int j = 0; j<canvas.getLevels()[0].getEnemies().length; j++){
				
				if (canvas.getLevels()[i].getEnemies()[j]!=null){
					min = canvas.getLevels()[i].getEnemies()[j].getLoot();
					if(min > max){
						max = min;
						pos = i;
						enemyPos = j;
					}
				}
			}
		}
		if (enemyPos != -1 && pos != -1){
			msj = "El enemigo que mas puntos otorga es un "+canvas.getLevels()[pos].getEnemies()[enemyPos].getType()+" en el nivel "+(pos+1)+"\n"+
			"Puntos en total: "+canvas.getLevels()[pos].getEnemies()[enemyPos].getLoot();
		}
		return msj;
	}

	public String getALLConsonants(){
		String msj = "";
		int contador = 0;
		String enemyName = "";
		String consonantes ="bcdfghjklmnpqrstvwxyz"; 
		for(int i = 0; i<canvas.getLevels().length; i++){
			for(int j = 0; j<canvas.getLevels()[0].getEnemies().length; j++){
				if (canvas.getLevels()[i].getEnemies()[j]!=null){
					enemyName = canvas.getLevels()[i].getEnemies()[j].getName();
					for(int k = 0; k<consonantes.length(); k++){
						for(int l= 0; l<enemyName.length(); l++){
							if(enemyName.charAt(l)==consonantes.charAt(k)){
								contador++;
							}
						
						}
					}
				}
			}
		
		
		}
		return msj ="En el juego los enemigos tienen un total de "+contador+" consonantes en sus nombres";
	}
}


