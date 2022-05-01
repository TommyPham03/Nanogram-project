package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class NonogramView {
	private static final String STYLE_CLASS = "nonogram-view";
	private static final String SOLVED_STYLE_CLASS = "nonogram-view-solved";
	private LeftCluesView leftCluesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox bottomHBox;
	private Button loadBtn;
	private Button resetBtn;
	

	public NonogramView() {
		
	}
	
	public void initialize(int [][] rowClues, int [][] colClues, int cellLength) {
		
	}
	
	public void initBottomHBox() {
		
	}
	
	public CellView getCellView(int rowIdx, int colIdx) {
		
	}
	
	public void setCellState(int rowIdx, int colIdex, CellState state) {
		
	}
	
	public void setRowClueState(int rowIdx, boolean solved) {
		
	}
	
	public void setColClueState(int colIdx, boolean solved) {
		
	}
	
	public void setPuzzleState(boolean solved) {
		
	}
	
	public Button getLoadButton() {
		
	}
	
	public Button getResetButton() {
		
	}
	
	public void showVictoryAlert() {
		
	}
}
