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
        switch(option){
            case 1:
                System.out.println("Inserte su nickname");
                nick = reader.next();
                System.out.println("Inserte su nombre");
                name = reader.next();
                System.out.println(videoGame.addPlayer(new Player(nick, name, videoGame.getCanvas().getLevels()[0])));
            break;

            case 2: 
                int type = 0;
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
                System.out.println("Inserte el id del nivel");
                id = reader.next();
                System.out.println("Inserte el nombre del enemigo");
                name = reader.next();
                System.out.println("Inserte los puntons que le QUITA al jugador");
                attack = reader.nextInt();
                System.out.println("Inserte los puntons que le AUMENTA al jugador");
                loot = reader.nextInt();
                System.out.println(videoGame.addEnemyToLevel(new Enemy(name, attack, loot, type), id)); 
                break;

            case 3:
                System.out.println("Inserte el id del nivel");
                id = reader.next();
                System.out.println("Inserte el nombre del tesoro");
                name = reader.next();
                System.out.println("Inserte el nombre la url de la imagen del tesoro");
                url = reader.next();
                System.out.println("Inserte los puntons que le AUMENTA al jugador");
                loot = reader.nextInt();
                System.out.println(videoGame.addTreasureToLevel(id, new Treasure(name, url, loot) ));   
            break;

            case 4:
                System.out.println("Inserte el nickname del jugador");
                nick = reader.next();
                System.out.println("Inserte el nuevo puntaje");
                loot = reader.nextInt();
                System.out.println(videoGame.changePlayerScore(nick, loot));

            break;
            case 5:
                System.out.println("Inserte el nickname del jugador");
                nick = reader.next();
                System.out.println(videoGame.upgradePlayer(nick))   ;
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