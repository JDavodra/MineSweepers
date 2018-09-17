import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;
//Jahesh Davodra
//300018359
//ITI 1121 -C
//Assingment 2

/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE
	private GameModel gameModel;
	private GameView gameView;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    public GameController(int width, int height, int numberOfMines) {

    // ADD YOU CODE HERE
		gameModel = new GameModel(width, height, numberOfMines);
		gameView = new GameView(gameModel, this);
		gameView.update();

    }


    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        
    // ADD YOU CODE HERE
		if (e.getSource() instanceof DotButton){
			DotButton src = (DotButton) e.getSource();
			play(src.getColumn(), src.getRow());
		} else if (e.getSource() instanceof JButton){
			JButton src = (JButton) e.getSource();
			if (src.getText().equals("Quit")){
				System.exit(0);
			} else{
				reset();
			}
		}

    }

    /**
     * resets the game
     */
    private void reset(){

    // ADD YOU CODE HERE
		gameModel.reset();
		gameView.update();

    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){

    // ADD YOU CODE HERE
		if(gameModel.isCovered(width, heigth)){
			gameModel.step();
			gameModel.uncover(width, heigth);
			if(gameModel.isMined(width, heigth)){
				gameModel.click(width, heigth);
				gameModel.uncoverAll();
				gameView.update();
				// pop up gui YOU LOST
				Object[] options = {"Quit","Play again"};
				String message = "Aouch, you lost in " + Integer.toString(gameModel.getNumberOfSteps()) + " steps! Would you like to play again?";
				int answer = JOptionPane.showOptionDialog(gameView, message,"Boom", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (answer == JOptionPane.YES_OPTION) {
					System.exit(0);
					
				} else if (answer == JOptionPane.NO_OPTION) {
					// User clicked NO.
					reset();
					
				}
			} else {
				if(gameModel.isBlank(width,heigth)){
					clearZone(gameModel.get(width,heigth));				
				}
				gameView.update();
				if(gameModel.isFinished()){
					gameModel.uncoverAll();
					gameView.update();
					// pop up gui YOU WON
					Object[] options = {"Quit","Play again"};
					String message = "Congratulations, you won in " + Integer.toString(gameModel.getNumberOfSteps()) + " steps! Would you like to play again?";
					int answer = JOptionPane.showOptionDialog(gameView, message,"Won", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					if (answer == JOptionPane.YES_OPTION) {
						System.exit(0);
					
					} else if (answer == JOptionPane.NO_OPTION) {
					// User clicked NO.
						reset();
					
					}
				}
				
			}
		}
    }

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
    private void clearZone(DotInfo initialDot) {


    // ADD YOU CODE HERE
		GenericArrayStack <DotInfo> a;
		a = new GenericArrayStack<DotInfo>(gameModel.getWidth() * gameModel.getHeigth());
		a.push(initialDot);
		DotInfo b;
		while(!(a.isEmpty())){
			b = a.pop();
				for (int k = b.getY()-1; k < b.getY()+2; k++){
					for(int l = b.getX()-1; l < b.getX()+2; l++){
						if((k >= 0 && k < gameModel.getHeigth()) && (l >= 0 && l < gameModel.getWidth())){
							if(!gameModel.isMined(l,k) && gameModel.isCovered(l,k)){
								gameModel.uncover(l,k);
								if(gameModel.isBlank(l,k)){
									a.push(gameModel.get(l,k));
								}
							}
						}
					}
				}
				
				
		}
		
			
	}
			
}

    




