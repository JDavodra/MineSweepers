import java.util.Random;
//Jahesh Davodra
//300018359
//ITI 1121 -C
//Assingment 2
/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {


     // ADD YOUR INSTANCE VARIABLES HERE
	 private java.util.Random generator;
	 private int heigthOfGame;
	 private DotInfo[][] model;
	 private int numberOfMines;
	 private int numberOfSteps;
	 private int numberUncovered;
	 private int widthOfGame;

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigth, int numberOfMines) {
        
    // ADD YOU CODE HERE
		generator = new Random();
		widthOfGame = width;
		heigthOfGame = heigth;
		this.numberOfMines = numberOfMines;
		reset();
		

    }


 
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){
		
        
    // ADD YOU CODE HERE
		numberUncovered = 0;
		numberOfSteps = 0;
		model = new DotInfo[heigthOfGame][widthOfGame];
		int w = 0;
		int h = 0;
		int neigh = 0;
		for (int j= 0; j < heigthOfGame; j ++){
			for(int i = 0; i < widthOfGame; i++){
				model[j][i] = new DotInfo(i,j);
			}
		}
		for (int i = 0; i < numberOfMines; i ++){
			w = generator.nextInt(widthOfGame-1);
			h = generator.nextInt(heigthOfGame -1);
			while(model[h][w].isMined() == true){
				w = generator.nextInt(widthOfGame-1);
				h = generator.nextInt(heigthOfGame -1);
			}
			model[h][w].setMined();
		}
		for (int j= 0; j < heigthOfGame; j ++){
			
			for(int i = 0; i < widthOfGame; i++){
				neigh = 0;
				for (int k = j-1; k < j+2; k++){
					for(int l = i-1; l < i+2; l++){
						if((k >= 0 && k < heigthOfGame) && (l >= 0 && l < widthOfGame)){
							if(model[k][l].isMined()){
								neigh ++;
							}
						}
					}
				}
				model[j][i].setNeighbooringMines(neigh);
				
			}
		
			
		}
	}	


    


    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth(){
        
    // ADD YOU CODE HERE
		return heigthOfGame;

    }

    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){
        
    // ADD YOU CODE HERE
		return widthOfGame;

    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
        
    // ADD YOU CODE HERE
		return model[j][i].isMined();

    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
        
    // ADD YOU CODE HERE
		return model[j][i].hasBeenClicked();

    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
        
    // ADD YOU CODE HERE
		return (model[j][i].getNeighbooringMines() == 0);

    }
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){
        
    // ADD YOU CODE HERE
		return (model[j][i].isCovered() == false);

    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
        
    // ADD YOU CODE HERE
		return model[j][i].getNeighbooringMines();

    }


    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
        
    // ADD YOU CODE HERE
		model[j][i].uncover();
		numberUncovered ++;

    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
        
    // ADD YOU CODE HERE
		model[j][i].click();

    }
     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
        
    // ADD YOU CODE HERE
		for (int j = 0; j < heigthOfGame; j++){
			for (int i = 0; i < widthOfGame; i++){
				model[j][i].uncover();
				numberUncovered++;
			}
		}

    }

 

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
        
    // ADD YOU CODE HERE
		return numberOfSteps;

    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
        
    // ADD YOU CODE HERE
		return model[j][i];

    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
     public void step(){
        
    // ADD YOU CODE HERE
		numberOfSteps ++;

    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        
    // ADD YOU CODE HERE
		return (numberUncovered == ((widthOfGame * heigthOfGame) - (numberOfMines)));
	

    }


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
        
    // ADD YOU CODE HERE
		return "Yes";
		

    }
}
