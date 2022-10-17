package model;
import java.lang.Math;
import java.util.ArrayList;
/**
 * Model of a videogame class
 */
public class VideoGame {
    private String name;
    private Canvas canvas;
	private Player[] players;

	/**
	 * Constructor of the Videogame Class
	 * @param name name of the videogame
	 */
    public VideoGame(String name){
		canvas = new Canvas();
        this.name = name;
		players = new Player[20];
		createRandomEnemies();
		createRandomTreasures();
    }
	/**
	 * Add a player to the game
	 * @param newPlayer Player objecto to add to the game
	 * @return confirmation message
	 */
	public String addPlayer(String nickname, String name) {
		int pos = searchPlayerbyNick(nickname);
		String msj = "";
		
		if (pos==-1){
			pos = playerHasEmptyPos();
			if(pos!=-1){
				players[pos] = new Player(nickname, name, canvas.getLevels()[0]);
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
	 * Adds an enemy to a specific level
	 * @param levelid id of the level
	 * @param enemy Enemy object to be added
	 * @param type type of the enemy
	 * @return confirmation message
	 */
	public String addEnemyToLevel(String name, int attack, int loot, String levelid, int type) {
		int pos = searchLevelById(levelid);
		boolean isRepeated;
        int enemyPos;
		Enemy newEnemy = null;
        String msj ="";
        if (pos==-1){
            msj = "No se encontro el nivel";
        }else{
			isRepeated = canvas.getLevels()[pos].isEnemyRepeated(name);
			if (!isRepeated){
				enemyPos = canvas.getLevels()[pos].enemyHasEmptyPos();
            	if(enemyPos ==-1){
                	msj = "La capacidad de enemigos esta en su limite";
           		}else{
					newEnemy = new Enemy(name, attack, loot);
					newEnemy.setType(giveType(type));
                	canvas.getLevels()[pos].getEnemies()[enemyPos]=newEnemy;
					canvas.getLevels()[pos].calculateDifficulty();
                	msj = "Enemigo agregado";

            	}
			}else{
				msj = "El enemigo ya existe en este nivel";
			}
            
        }
        return msj;
	}

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
	 * Adds a treasure to a specific level
	 * @param levelid id of the level
	 * @param newTreasure Treasure object to add
	 * @return confirmarion message
	 */
	public String addTreasureToLevel(String levelid, String name, String url, int value) {
		int pos = searchLevelById(levelid);
		boolean isRepeated;
        int treasurePos;
        String msj ="";
		Treasure newTreasure = null;
        if (pos==-1){
            msj = "No se encontro el nivel";
        }else{
			isRepeated = canvas.getLevels()[pos].isTreasureRepeated(name);
			if (!isRepeated){
				treasurePos = canvas.getLevels()[pos].TreasureHasEmptyPos();
            	if(treasurePos ==-1){
                	msj = "La capacidad de enemigos esta en su limite";
           		}else{
					newTreasure = new Treasure(name, url, value);
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
	/**
	 * Adds a treasure to a specific level
	 * @param levelid id of the level
	 * @param newTreasure Treasure object to add
	 * @return confirmarion message
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
	/**
	 * Change the score of a specific player
	 * @param nick nickname of the player
	 * @param newScore new score to set
	 * @return confirmatio message
	 */
	public String changePlayerScore(String nick, int newScore) {
		String msj  = "";
		int pos = searchPlayerbyNick(nick);
		if(pos==-1){
			msj = "No se encontro al jugador";
		}else{
			newScore += players[pos].getScore();
			players[pos].setScore(newScore);
			msj = "Puntaje actualizado";
		}
		return msj;
	}
	/**
	 * Upgrades a player to a higher level if it has enough points
	 * @param nick nickname of the player
	 * @return confirmation message
	 */
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
						if (i<canvas.getLevels().length-1){
							players[pos].setCurrentlevel(canvas.getLevels()[i+1]);
							msj = "Felicidades el jugador avanzo hasta el nivel "+canvas.getLevels()[i+1].getId();
						}else{
							players[pos].setCurrentlevel(canvas.getLevels()[i]);
							msj = "Felicidades el jugador has superado todos lo niveles";
						}
						
					}
				}
				
			}
		}else{
			msj = "No se encontro al usuario";
		}
		return msj;
	}
	/**
	 * Shows the treasures and enemies from a specific level
	 * @param id id of the level
	 * @return Resumé of the enemies and treasures
	 */
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
	 * Gets all the diamonds from the treasures of all the levels and add them together
	 * @return total of diamonds in all the levels
	 */
	public String getAllTreasures(){
		int total = 0;
		String msj ="";
		for (int i = 0; i<canvas.getLevels().length; i++){
			total+=canvas.getLevels()[i].countTresuresValue();
		}
		return msj = "En total de los niveles hay "+total+" Diamantes";
	}

	/**
	 * Counts all the enemies in all the levels of a specific type
	 * @param type type of the enemy
	 * @return the count of the enemies of the specific type in every levevl
	 */
	public String getAllEnemiesType(int type){
		Type toSearch = giveType(type);
		int total = 0;
		String msj;
		for (int i = 0; i<canvas.getLevels().length; i++){
			total +=canvas.getLevels()[i].countEnemiesByType(toSearch);
			
		}
		return msj = "En total de los niveles hay "+total+" "+toSearch+"(s)";
	}
	/**
	 * Gets the mode treasure
	 * @return The treasure thas is found the most
	 */
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
	/**
	 * Gets the enemy that gives the most points to the player and the level where is located
	 * @return the enemy that gives the most points to the player and the level where is located
	 */
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
	/**
	 * Counts the consonants in the names of the enemies in all the levels
	 * @return count of consonants in the names of the enemies in al the levels
	 */
	public String getALLConsonants(){
		String msj = "";
		int contador = 0;
		String enemyName = "";
		String consonantes ="bcdfghjklmnpqrstvwxyz"; 
		for(int i = 0; i<canvas.getLevels().length; i++){
			if(canvas.getLevels()[0]!=null){}
				for(int j = 0; j<canvas.getLevels()[0].getEnemies().length; j++){
					if (canvas.getLevels()[i]!=null){
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
			
		
		}
		return msj ="En el juego los enemigos tienen un total de "+contador+" consonantes en sus nombres";
	}
	/**
	 * Calculates the top 5 players by its score
	 * @return the scoreboard of the top 5 Players by score
	 */
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
	 * Creates random enemies with names from https://names.drycodes.com/ and add them to random levels
	 */
	public void createRandomEnemies(){
		
		String[] dryNames = new String[]{"Mara_Grand","Bossk_Natasi","C-3PO_Cade","Han_Zam","Revan_PROXY","Grand_Kir","Watto_Bossk","Gilad_Jaina","Zayne_Watto","IG_Jabba","Mace_Kyle","Lando_Jarael","Grand_Bib","Anakin_Cad","Boba_Aayla","Shaak_Cade","Grand_Senator","Chewbacca_C-3PO","Senator_Nom","Galen_Ahsoka","Mission_Zam","Lando_Chewbacca","Qui-Gon_Wedge","Visas_R2-D2","Captain_Admiral"};
		int randAttack;
		int randLoot;
		String randomLvl;
		int randomType;
		Enemy[] dryEnemies = new Enemy[dryNames.length];
		for (int i = 0; i<dryNames.length; i++){
			randAttack = (int)Math.round(Math.random()*50)+10;
			randLoot = (int)Math.round(Math.random()*70)+1;
			dryEnemies[i] = new Enemy(dryNames[i], randAttack, randLoot);
		}
		for (int i = 0; i<dryNames.length; i++){
			randomLvl =((int)Math.round(Math.random()*9)+1)+"";
			randomType  =(int)Math.round(Math.random()*3)+1;
			addEnemyToLevel(dryEnemies[i], randomLvl, randomType);
		}

		
	}
	/**
	 * creates random treasures and adds the to random levels
	 */
	public void createRandomTreasures(){
		String url = "https://res.cloudinary.com/dnmlo67cy/image/upload/v1664742061/download_rigiyb.jpg";
		String[] dryNames = new String[50];
		int randDiamonds;
		String randomLvl;
		for (int i = 0; i<dryNames.length; i++){
			dryNames[i] = "Tesoro"+(i+1);
		}
		Treasure[] dryTreasures = new Treasure[dryNames.length];
		for (int i = 0; i<dryNames.length; i++){
			randDiamonds = (int)Math.round(Math.random()*50)+1;
			dryTreasures[i] = new Treasure(dryNames[i], url, randDiamonds);
		}
		for (int i = 0; i<dryNames.length; i++){
			randomLvl =((int)Math.round(Math.random()*9)+1)+"";
			addTreasureToLevel(randomLvl, dryTreasures[i]);
		}

		
	}



	/**
	 * Levels are generated ramdomly righ now but it will work later if its needed 
	 * @param Level level to bel added
	 * @return confirmation message
	 */
	public String addLevel(int Level) {
		// TODO - implement VideoGame.addLevel
		throw new UnsupportedOperationException();
	}
	/**
	 * Searches a player by its nick
	 * @param nick nickname of the player
	 * @return position of the player if it's found, else -1
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
	 * Searches a level by its id
	 * @param id Id of the leve
	 * @return position of the level if found, else -1
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
	/**
	 * Checks if the player array has and empty position
	 * @return position if its found, else -1
	 */
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

	/**
	 * gives a enum type depending of the interger choose 
	 * @param type interger choosen
	 * @return enum type
	 */
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

	/**
	 * gets the canvas object
	 * @return canvas object
	 */
    public Canvas getCanvas() {
        return canvas;
    }

	/**
	 * gets the name of the game
	 * @return name of the game
	 */
    public String getName() {
        return name;
    }




}


