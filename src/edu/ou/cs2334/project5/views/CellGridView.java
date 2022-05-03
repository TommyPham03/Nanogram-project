package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.scene.layout.GridPane;

public class CellGridView extends GridPane{
	private static final String STYLE_CLASS = "cell-grid-view";
	private CellView [][] cellViews;
	
	public CellGridView(int numRows, int numCols, int cellLength) {
		//add into GridPane
		initCells(numRows, numCols, cellLength);
		getStyleClass().add(STYLE_CLASS);
	}
	
	public void initCells(int numRows, int numCols, int cellLength) {
		//clear the children of the view
		for(int index = 0; index < numRows; index++) {
			for(int j = 0; j < numCols; j++) {
				getCellView(numRows, numCols).getChildren().clear();
			}
		}	
				
		cellViews = new CellView [numRows] [numCols];
		
		//for loops to go through all the arrays
		for(int index = 0; index < numRows; ++index) {
			
			for(int j = 0; j < numCols; ++j) {
			//add into the gridpane and 2d array
			add(cellViews[numRows] [numCols], j, index);
			cellViews[index] [j] = new CellView(cellLength);
			//cellViews [numRows] [numCols];
			
			}
		}
		
	}
	
	public CellView getCellView(int rowIdx, int colIdx) {
		//get the cellView 
		return cellViews[rowIdx] [colIdx];
	}
	
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellViews[rowIdx][colIdx].setState(state);
	}
}
