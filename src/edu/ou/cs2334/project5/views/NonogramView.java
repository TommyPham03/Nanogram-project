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

public class NonogramView extends BorderPane{
	private static final String STYLE_CLASS = "nonogram-view";
	private static final String SOLVED_STYLE_CLASS = "nonogram-view-solved";
	private LeftCluesView leftCluesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox bottomHBox;
	private Button loadBtn;
	private Button resetBtn;
	

	public NonogramView() {
		//construct nonogramView instance
		getStyleClass().add(STYLE_CLASS);
	}
	
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
	public int leftView(int [][] rowClues) {//got code from https://stackoverflow.com/questions/34760736/get-max-length-of-row-and-column-in-java-two-dimensional-array
		return Arrays.stream(rowClues).map(row -> row.length).max(Integer::compare).get();
	}
	
	public int topView(int [][] rowClues) {//got code from https://stackoverflow.com/questions/34760736/get-max-length-of-row-and-column-in-java-two-dimensional-array
		return Arrays.stream(rowClues).map(height -> height.length).max(Integer::compare).get();
	}
	
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
	
	public CellView getCellView(int rowIdx, int colIdx) {
		return cellGridView.getCellView(rowIdx, colIdx);
	}
	
	public void setCellState(int rowIdx, int colIdex, CellState state) {
		cellGridView.setCellState(rowIdx, colIdex, state);
	}
	
	public void setRowClueState(int rowIdx, boolean solved) {
		NonogramModel nonogramModel = new NonogramModel(null, null);
		nonogramModel.getRowClues();
		
		
		
	}
	
	public void setColClueState(int colIdx, boolean solved) {
		
	}
	
	public void setPuzzleState(boolean solved) {
		
	}
	
	public Button getLoadButton() {
		return loadBtn;
		
	}
	
	public Button getResetButton() {
		return resetBtn;
		
	}
	
	public void showVictoryAlert() {
		
	}
}
