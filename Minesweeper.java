//Jahesh Davodra
//300018359
//ITI 1121 -C
//Assingment 2

/**
 * The class <b>Mineseeper</b> launches the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class Minesweeper {

     // ADD YOUR INSTANCE VARIABLES HERE

   /**
     * <b>main</b> of the application. Creates the instance of  GameController 
     * and starts the game. If three parameters width, heigth,
     * number of mines are passed, they are used. 
     * Otherwise, a default value is used. Defaults values are also
     * used if the paramters are too small (minimum 10 for width,
     * 5 for heigth and 1 for number of mines).
     * Additionally, the maximum number of mines is capped at
     * width*heigth -1
     * 
     * @param args
     *            command line parameters
     */
     public static void main(String[] args) {
        int width   = 20;
        int heigth  = 12;
        int numberOfMines = 36;
 
        if (args.length == 3) {
            try{
                width = Integer.parseInt(args[0]);
                if(width<10){
                    System.out.println("Invalid argument, using default...");
                    width = 20;
                }
                heigth = Integer.parseInt(args[1]);
                if(heigth<5){
                    System.out.println("Invalid argument, using default...");
                    heigth = 12;
                }
                numberOfMines = Integer.parseInt(args[2]);
                if(numberOfMines<1){
                    System.out.println("Invalid argument, using default...");
                    numberOfMines = 36;
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid argument, using default...");
                width   = 20;
                heigth  = 12;
                numberOfMines = 36;
            }
        }
        if(numberOfMines >= width*heigth) {
            System.out.println("Too many mines: " + numberOfMines 
                + " mines on " + (width*heigth) + " spots. Using " 
                + (width*heigth - 1) + " instead. Good luck!");
            numberOfMines = (width*heigth - 1);
        }
		StudentInfo.display();
        GameController game = new GameController(width, heigth,numberOfMines);
    }


}
