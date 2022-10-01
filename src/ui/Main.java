package ui;

import java.util.Scanner;

import model.VideoGame;

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
        "3. Modificar el puntaje de un jugador \n"+
        "4. Subir de nivel a un jugador");
        option = reader.nextInt();

        return option;
    }
    public void executeOption(int option){ 
     
        switch(option){
            case 1:

            break;

            case 2: 

            break;

            case 3:

            break;

            case 4:

            break;

            default:

            break;
        }

    }



}//end of class