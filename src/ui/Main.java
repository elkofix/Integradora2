package ui;

import java.util.Scanner;
import model.VideoGame;
import model.*;

public class Main{

    private VideoGame videoGame;
    private Scanner reader;
    public static void main(String[] args) {
        Main main = new Main();
        int option = 0;
        do{
          option = main.getOptionShowMenu();
          main.executeOption(option);
        }while(option!=0);
    }

    public Main(){
       videoGame = new VideoGame("Nico's Adventure");
       reader = new Scanner(System.in);
    }
    public int getOptionShowMenu(){ 
        int option = 0;
        System.out.println("<<Bienvenido a "+videoGame.getName()+" >> \n"+
        "1. Crear jugador \n"+
        "2. Registra enemigo a un nivel \n"+
        "3. Registra tesoro a un nivel \n"+
        "4. Modificar el puntaje de un jugador \n"+
        "5. Subir de nivel a un jugador \n"+
        "6. Informar los tesoros y enemigos de un nivel dado \n"+
        "7. Informar valor de los tesoros en los niveles \n"+
        "8. Informar cantidad un tipo de enemigo en los niveles \n"+
        "9. Informar el tesoro que mas se repite \n"+
        "10. Informar el enemigo que da mas puntos y su nivel \n"+  
        "11. Informar la cantidad de consonantes encontradas en los nombres de los enemigos del juego \n"+
        "12. Informar el top 5 de jugadores segun su puntaje \n"+
        "0. Salir");
        option = reader.nextInt();

        return option;
    }
    public void executeOption(int option){ 
        String name;
        String nick;
        int attack;
        int loot;
        String id;
        String url;
        int type = 0;
        switch(option){
            case 1:
                System.out.println("Inserte su nickname");
                nick = reader.next();
                System.out.println("Inserte su nombre");
                name = reader.next();
                System.out.println(videoGame.addPlayer(nick, name));
            break;

            case 2: 
                
                do{
                    System.out.println("1. Ogro \n"+
                    "2. Abstracto \n"+
                    "3. Jefe \n"+
                    "4. Magico \n");
                    System.out.println("Digite el numero de tipo de enemigo");
                    type = reader.nextInt();
                    if(type<1 || type>4){
                        System.out.println("Opcion invalida");
                    }
                }while(type<1 || type>4);
                System.out.println("Inserte el numero del nivel");
                id = reader.next();
                System.out.println("Inserte el nombre del enemigo");
                name = reader.next();
                System.out.println("Inserte los puntons que le QUITA al jugador");
                attack = reader.nextInt();
                System.out.println("Inserte los puntons que le AUMENTA al jugador");
                loot = reader.nextInt();
                System.out.println(videoGame.addEnemyToLevel(name.toLowerCase(), attack, loot, id, type)); 
                break;

            case 3:
                System.out.println("Inserte el numer del nivel");
                id = reader.next();
                System.out.println("Inserte el nombre del tesoro");
                name = reader.next();
                System.out.println("Inserte el nombre la url de la imagen del tesoro");
                url = reader.next();
                System.out.println("Inserte cuantos diamantes tendra este tesoro");
                loot = reader.nextInt();
                System.out.println(videoGame.addTreasureToLevel(id, name, url, loot));   
            break;

            case 4:
                System.out.println("Inserte el nickname del jugador");
                nick = reader.next();
                System.out.println("Inserte el puntaje a sumarle");
                loot = reader.nextInt();
                System.out.println(videoGame.changePlayerScore(nick, loot));

            break;
            case 5:
                System.out.println("Inserte el nickname del jugador");
                nick = reader.next();
                System.out.println(videoGame.upgradePlayer(nick))   ;
            break;
            case 6:
                System.out.println("Inserte el numero del nivel");
                id = reader.next();
                System.out.println(videoGame.showTreasuresAndEnemys(id));
            break;
            case 7:
                System.out.println(videoGame.getAllTreasures());
            break;
            case 8:
                do{
                    System.out.println("1. Ogro \n"+
                    "2. Abstracto \n"+
                    "3. Jefe \n"+
                    "4. Magico \n");
                    System.out.println("Inserte el numero de tipo de enemigo");
                    type = reader.nextInt();
                    if(type<1 || type>4){
                        System.out.println("Opcion invalid");
                    }
                }while(type<1 || type>4);
                System.out.println(videoGame.getAllEnemiesType(type));    
                    
            break;
            case 9:
                System.out.println(videoGame.getModeTreasure());
            break;
            case 10:
                System.out.println(videoGame.getMaxEnemy());
            break;
            case 11:
                System.out.println(videoGame.getALLConsonants());
            break;
            case 12:
                System.out.println(videoGame.calculateTop5());
            break;
            case 0:
                System.out.println("Bye uwu");
            break;
            default:
                System.out.println("Opcion invalida");

            break;
        }

    }



}//end of class