package edu.ou.cs2334.project5.views;

import java.util.Arrays;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * BorderPane class that display the row clues, column clues, and positions
 * @author Tommy Pham
 *
 */
public class NonogramView extends BorderPane{
	private static final String STYLE_CLASS = "nonogram-view";
	private static final String SOLVED_STYLE_CLASS = "nonogram-view-solved";
	private LeftCluesView leftCluesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox bottomHBox;
	private Button loadBtn;
	private Button resetBtn;
	
	/**
	 * NonogramView constructor
	 */
	public NonogramView() {
		//construct nonogramView instance
		getStyleClass().add(STYLE_CLASS);
	}
	
	/**
	 * method to initialize the view
	 * 
	 * @param rowClues the row clues
	 * @param colClues the column clues
	 * @param cellLength the cell length
	 */
	public void initialize(int [][] rowClues, int [][] colClues, int cellLength) {
		//set instance variables
		cellGridView = new CellGridView(rowClues.length, colClues.length, cellLength);
		topCluesView = new TopCluesView(colClues, cellLength, clueWidth(colClues));
		leftCluesView = new LeftCluesView(rowClues, cellLength, clueWidth(rowClues));
		
		//set the borderpanes
		setLeft(leftCluesView);
		//BorderPane.setAlignment(leftCluesView, Pos.BOTTOM_RIGHT);
		setTop(topCluesView);
		//BorderPane.setAlignment(topCluesView, Pos.BOTTOM_RIGHT);
		setAlignment(topCluesView, Pos.CENTER_RIGHT);
		setCenter(cellGridView);
		
		//initialize Hbox with the load and reset buttons
		initBottomHBox();
		setBottom(bottomHBox);
		
	}
	//helper methods to get height and width
	
	private int clueWidth(int[][] rowClues) {//got code from Ewin Green
		//make a variable to find the greatest length 
		int variable = 0;
		
		//make a nested for loop to go through and finding the largest length
        for (int index = 0; index < rowClues.length; ++index) {
            int count = 0;
            
            for (int one : rowClues[index]) {
                count++;
            }
            if (variable < count) {
                variable = count;
            }
        }
        //return the largest length
        return variable;
    }
	
	/**
	 * method to initialize the bottomHBox variable
	 */
	public void initBottomHBox() {
		//create a new HBox and set it
		bottomHBox = new HBox();
		bottomHBox.setAlignment(Pos.CENTER);
		
		//create buttons
		loadBtn = new Button("load");
		resetBtn = new Button("reset");
		
		//add the buttons to the HBox
		bottomHBox.getChildren().add(loadBtn);
		bottomHBox.getChildren().add(resetBtn);
	}
	
	/**
	 * cell view getter
	 * 
	 * @param rowIdx the given row index
	 * @param colIdx the given column index
	 * @return return the cell view
	 */
	public CellView getCellView(int rowIdx, int colIdx) {
		return cellGridView.getCellView(rowIdx, colIdx);
	}
	
	/**
	 * cell state setter
	 * 
	 * @param rowIdx the given row index
	 * @param colIdex the given column index
	 * @param state the state of the cell
	 */
	public void setCellState(int rowIdx, int colIdex, CellState state) {
		cellGridView.setCellState(rowIdx, colIdex, state);
	}
	
	/**
	 * method to update the state of the RowClueView
	 * @param rowIdx the given row index
	 * @param solved the boolean variable to see if it is solved
	 */
	public void setRowClueState(int rowIdx, boolean solved) {
		leftCluesView.setState(rowIdx, solved);
	}
	
	/**
	 * method to update the state of ColCluesView
	 * 
	 * @param colIdx the given column index
	 * @param solved the boolean variable to see if it is solved
	 */
	public void setColClueState(int colIdx, boolean solved) {
		topCluesView.setState(colIdx, solved);
	}
	
	/**
	 * method to add the stye class
	 * 
	 * @param solved the boolean variable to see if it is solved
	 */
	public void setPuzzleState(boolean solved) {
		//if it is solved then add in the solve style
		if(solved == true) {
			getStyleClass().add(SOLVED_STYLE_CLASS);
		}
		
		//otherwise remove all occurrences
		//getStyleClass().clear();
	}
	
	/**
	 * load button getter
	 * @return return the load button
	 */
	public Button getLoadButton() {
		return loadBtn;
		
	}
	
	/**
	 * reset button getter
	 * 
	 * @return return the reset button
	 */
	public Button getResetButton() {
		return resetBtn;
	}
	
	/**
	 * method to show victory alert
	 */
	public void showVictoryAlert() {
		//make a new alert
		Alert alert = new Alert(AlertType.INFORMATION, "Puzzle Solved");
		//set the content texts
		alert.setHeaderText("Congratulations!");
		alert.setContentText("You Win!");
		alert.setTitle("Puzzle Solved");
		
		//alert.setAlertType(AlertType.valueOf("You Win!"));
		
		//show it
		alert.show();
	}
}
