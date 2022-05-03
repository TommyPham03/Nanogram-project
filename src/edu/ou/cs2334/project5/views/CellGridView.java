package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.scene.layout.GridPane;

/**
 * class that is a GridPane
 * @author Tommy Pham
 *
 */
public class CellGridView extends GridPane{
	private static final String STYLE_CLASS = "cell-grid-view";
	private CellView [][] cellViews;
	
	/**
	 * CellGridView constructor
	 * 
	 * @param numRows the number of rows
	 * @param numCols the number of columns
	 * @param cellLength the cell length
	 */
	public CellGridView(int numRows, int numCols, int cellLength) {
		//add into GridPane
		initCells(numRows, numCols, cellLength);
		getStyleClass().add(STYLE_CLASS);
	}
	
	/**
	 * method to intialize the cells of the view
	 * 
	 * @param numRows the number of rows
	 * @param numCols the number of columns
	 * @param cellLength the cell length
	 */
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
	
	/**
	 * cellview getter 
	 * 
	 * @param rowIdx the given row index
	 * @param colIdx the given column index
	 * @return return the CellView using the given indexes
	 */
	public CellView getCellView(int rowIdx, int colIdx) {
		//get the cellView 
		return cellViews[rowIdx] [colIdx];
	}
	
	/**
	 * cellstate setter
	 * 
	 * @param rowIdx the given row index
	 * @param colIdx the given column index
	 * @param state the state of the cell
	 */
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellViews[rowIdx][colIdx].setState(state);
	}
}
