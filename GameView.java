import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//Jahesh Davodra
//300018359
//ITI 1121 -C
//Assingment 2
/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

     // ADD YOUR INSTANCE VARIABLES HERE
	 private DotButton[][] board;
	 private GameModel gameModel;
	 private GameController gameController;
	 private JButton reset;
	 private JButton quit;
	 private JLabel nbreOfStepsLabel;
	 private JPanel mainSec;
	 private JPanel information;
	 

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {
        
    // ADD YOU CODE HERE
		super("MineSweeper iti -- the ITI 1121 version");
		this.gameModel = gameModel;
		this.gameController = gameController;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		mainSec = new JPanel();
		mainSec.setLayout(new GridLayout(gameModel.getHeigth(), gameModel.getWidth(),0,0));
		board = new DotButton [gameModel.getHeigth()][gameModel.getWidth()];
		for (int i = 0; i < gameModel.getHeigth(); i ++){
			for(int j = 0; j < gameModel.getWidth(); j ++){
				board[i][j] = new DotButton(j, i, 11);
				board[i][j].addActionListener(gameController);
				mainSec.add(board[i][j]);
			}
		}
		
		add(mainSec, BorderLayout.CENTER);
		information = new JPanel();
		information.setBackground(Color.WHITE);
		reset = new JButton("Reset");
		quit = new JButton("Quit");
		reset.addActionListener(gameController);
		quit.addActionListener(gameController);
		nbreOfStepsLabel = new JLabel("Number of Steps: " + Integer.toString(gameModel.getNumberOfSteps()));
		information.add(nbreOfStepsLabel);
		information.add(reset);
		information.add(quit);
		add(information, BorderLayout.SOUTH);
		setSize((gameModel.getWidth() * 28) + 100, (gameModel.getHeigth() * 28) + 100);
		setVisible(true);
		
		
		
		
		

    }

    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
        
    // ADD YOU CODE HERE
		for (int i = 0; i < gameModel.getHeigth(); i ++){
			for(int j = 0; j < gameModel.getWidth(); j ++){
				board[i][j].setIconNumber(getIcon(j,i));
				
			}
		}
		nbreOfStepsLabel.setText("Number of Steps: " + Integer.toString(gameModel.getNumberOfSteps()));

    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){
        
    // ADD YOU CODE HERE
				if (gameModel.isCovered(i,j)){
					return 11;
				} else{
					if(gameModel.isMined(i,j) && gameModel.hasBeenClicked(i,j)){
						return 10;
					} else{
						int mines = gameModel.getNeighbooringMines(i,j);
						if(gameModel.isMined(i,j)){
							return 9;
						}else if (mines == 1){
							return 1;
						}else if (mines == 2){
							return 2;
						}else if(mines == 3){
							return 3;
						}else if(mines == 4){
							return 4;
						}else if(mines == 5){
							return 5;
						}else if (mines == 6){
							return 6;
						}else if (mines == 7){
							return 7;
						}else if(mines == 8){
							return 8;
						}else{
							return 0;
						}
					}
				}
			}
}




