package edu.ou.cs2334.project5.views;

import java.util.Arrays;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;
import javafx.geometry.Pos;
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
		
		int rowCluesHeight = topView(rowClues);
		topCluesView = new TopCluesView(rowClues, cellLength, rowCluesHeight);
		int rowCluesWidth = leftView(rowClues);
		leftCluesView = new LeftCluesView(rowClues, cellLength, rowCluesWidth);
		
		//set the borderpanes
		setLeft(leftCluesView);
		setTop(topCluesView);
		setCenter(cellGridView);
		
		//initialize Hbox with the load and reset buttons
		bottomHBox = new HBox(loadBtn, resetBtn);
		setBottom(bottomHBox);
		
	}
	//helper methods to get height and width
	/**
	 * helper method to get the longest row
	 * 
	 * @param rowClues the 2d array
	 * @return return the longest row
	 */
	public int leftView(int [][] rowClues) {//got code from https://stackoverflow.com/questions/34760736/get-max-length-of-row-and-column-in-java-two-dimensional-array
		return Arrays.stream(rowClues).map(row -> row.length).max(Integer::compare).get();
	}
	
	/**
	 * helper method to get the longest column
	 * @param rowClues the 2d array
	 * @return return the longest column
	 */
	public int topView(int [][] rowClues) {//got code from https://stackoverflow.com/questions/34760736/get-max-length-of-row-and-column-in-java-two-dimensional-array
		return Arrays.stream(rowClues).map(height -> height.length).max(Integer::compare).get();
	}
	
	/**
	 * method to initialize the bottomHBox variable
	 */
	public void initBottomHBox() {
		//create a new HBox and set it
		bottomHBox = new HBox();
		bottomHBox.setAlignment(Pos.CENTER);
		
		//create buttons
		loadBtn = new Button();
		resetBtn = new Button();
		
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
		NonogramModel nonogramModel = new NonogramModel(null, null);
		nonogramModel.getRowClues();
	}
	
	/**
	 * method to update the state of ColCluesView
	 * 
	 * @param colIdx the given column index
	 * @param solved the boolean variable to see if it is solved
	 */
	public void setColClueState(int colIdx, boolean solved) {
		
	}
	
	/**
	 * method to add the stye class
	 * 
	 * @param solved the boolean variable to see if it is solved
	 */
	public void setPuzzleState(boolean solved) {
		
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
		
	}
}
