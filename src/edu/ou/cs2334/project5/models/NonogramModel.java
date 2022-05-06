package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * This class encapsualtes the rules and states of the nonogram game 
 * @author Tommy Pham
 *
 */
public class NonogramModel {

	private static final String DELIMITER = " ";
	private static final int IDX_NUM_ROWS = 0;
	private static final int IDX_NUM_COLS = 1;

	private int[][] rowClues;
	private int[][] colClues;
	private CellState[][] cellStates;
	
	/**
	 * NonogramModel array constructor
	 * 
	 * @param rowClues array of rows
	 * @param colClues array of columns
	 */
	public NonogramModel(int[][] rowClues, int[][] colClues) {
		//set instance variables
		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}

	/**
	 * NonogramModel file constructor
	 * 
	 * @param file the given file 
	 * @throws IOException throw an IOException
	 */
	public NonogramModel(File file) throws IOException {
		// Number of rows and columns
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String header = reader.readLine();
		String[] fields = header.split(DELIMITER);
		int numRows = Integer.parseInt(fields[IDX_NUM_ROWS]);
		int numCols = Integer.parseInt(fields[IDX_NUM_COLS]);
		
		// Initialize cellStates.
		cellStates = initCellStates(numRows, numCols);
		
		//Read in row clues.
		rowClues = readClueLines(reader, numRows);
		//Read in column clues.
		colClues = readClueLines(reader, numCols);
		// Close reader
		reader.close();
	}

	/**
	 * NonogramModel string constructor
	 * 
	 * @param filename the given string 
	 * @throws IOException throws an IOException
	 */
	public NonogramModel(String filename) throws IOException {
		this(new File(filename));
	}

	/**
	 * numRows getter
	 * 
	 * @return return the number of rows
	 */
	public int getNumRows() {
		return rowClues.length;
	}

	/**
	 * numCols getter
	 * 
	 * @return return the number of colunms
	 */
	public int getNumCols() {
		return colClues.length;
	}
	
	/**
	 * cell state getter
	 * 
	 * @param rowIdx the given row index
	 * @param colIdx the given column index
	 * @return return the cell state
	 */
	public CellState getCellState(int rowIdx, int colIdx) {
		//return the state of the cell with the given row and col
		return cellStates[rowIdx][colIdx];
	}
	
	/**
	 * Cell state as boolean getter
	 * 
	 * @param rowIdx the given row index
	 * @param colIdx the given column index
	 * @return return the cell state as a boolean
	 */
	public boolean getCellStateAsBoolean(int rowIdx, int colIdx) {
		//get the cell state
		CellState state = getCellState(rowIdx, colIdx);
		//turn it into a boolean and return
		return CellState.toBoolean(state);
	}
	
	/**
	 * Cell state setter
	 * 
	 * @param rowIdx the given row index
	 * @param colIdx the given column index
	 * @param state the state of the cell
	 * @return return true or false depending on if the puzzle is solved
	 */
	public boolean setCellState(int rowIdx, int colIdx, CellState state) {
		//check if it is null or if its already solved and return false
		if(isSolved() || getCellState(rowIdx, colIdx) == state || state == null) {
			return false;
		}
		//set the cell state
		cellStates[rowIdx][colIdx] = state;
		//return the boolean 
		return true;
		
	}
	
	/**
	 * row clues getter
	 * 
	 * @return return deep copy of row clues
	 */
	public int [][] getRowClues(){
		//return a deep copy of row clues
		return deepCopy(rowClues);
	}
	
	/**
	 * col clues getter
	 * 
	 * @return return deep copy of colClues
	 */
	public int [][] getColClues(){
		//return a deep copy of col clues
		return deepCopy(colClues);
	}
	
	/**
	 * row clue getter
	 * 
	 * @param rowIdx the given row index
	 * @return return a copy array of row clue
	 */
	public int [] getRowClue(int rowIdx){
		//return a copy
		return Arrays.copyOf(rowClues[rowIdx], rowClues[rowIdx].length);
	}
	
	/**
	 * column clue getter
	 * 
	 * @param colIdx the given column index
	 * @return return a copy array of column clue
	 */
	public int [] getColClue(int colIdx){
		//return a copy
		return Arrays.copyOf(colClues[colIdx], colClues[colIdx].length);
	}
	
	/**
	 * method to check if row is solved
	 * 
	 * @param rowIdx the given row index
	 * @return return true or false depending on if the row is solved
	 */
	public boolean isRowSolved(int rowIdx) {
		//if it is equal to each other return true
		//if it is true return true
		if(Arrays.equals(rowClues[rowIdx], projectCellStatesRow(rowIdx))) {
			return true;
		}
		//else return false
		return false;
	}
	
	/**
	 * method to check if column is solved
	 * 
	 * @param colIdx the given column index
	 * @return return true or false depending on if the column is solved
	 */
	public boolean isColSolved(int colIdx) {
		//check to see if it is equal or not on whether if it is solved
		return Arrays.equals(colClues[colIdx], projectCellStatesCol(colIdx));
	}
	
	/**
	 * method to check if the puzzle is solved
	 * 
	 * @return return true or false depending on if the puzzle is solve
	 */
	public boolean isSolved() {
		//make a boolean value to set to true or false
		boolean solve = true;
		//for loops to go through the array to see if any is false
		for(int index = 0; index < getNumRows(); ++index) {
			if(isRowSolved(index) == false) {
				solve = false;
			}
		}
		
		for(int index = 0; index < getNumCols(); ++index) {
			if(isColSolved(index) == false) {
				solve = false;
			}
		}
		
		//if puzzle is solved return false
		if(solve == false) {
			return false;
		}
		
		// else return true
		return true;
	}

	/**
	 * method to set all the cells to empty
	 */
	public void resetCells() {//got help from Jacob
		//make a nested for loop to go through all the rows and columns
		for(int row = 0; row < getNumRows(); ++row) {
			for(int col = 0; col < getNumCols(); ++col) {
				cellStates[row][col] = CellState.EMPTY;
				}
			}
	}
	
	/**
	 * This method is used to project an array of row or columns
	 * 
	 * @param cells cell array
	 * @return return the nonogram numbers of the given array of cell states
	 */
	public static List<Integer> project(boolean [] cells){
		//make a new list to return
		List<Integer> list = new ArrayList<Integer>();
		//make a number variable to add to the list
		int num = 0; 
		
		//make a for loop to go through the cells length
		for(int index = 0; index < cells.length; ++index) {
			//if the variable at the index is true then increment the count
			if(cells[index] == true) {
				++num;
			}
			//if it is false then then add the counter in and reset the counter to 0
			else if(cells[index] == false) {
				//only adds in if the counter is greater than 0
				//else there would be a list of 0s 
				if(num > 0) {
				list.add(num);
				num = 0;
				}
			}
		}
		//add in the last true variable if there is one
		if(num != 0) {
			list.add(num);
		}
		//if nothing gets add then add in a 0 to show all false
		if(list.size() == 0) {
			list.add(0);
		}
		//return the list
		return list;
	}
	/**
	 * This method is used to project the row
	 * 
	 * @param rowIdx the given row index
	 * @return return the projection of the row at the given index
	 */
	public int [] projectCellStatesRow (int rowIdx){
		int numCols = colClues.length;
		//make a array with the instance variable
		boolean [] array = new boolean[numCols];
		//loop through it 
		for(int index = 0; index < numCols; ++index) {
			//set the index of the array to the cell state at that index
			array[index] = getCellStateAsBoolean(rowIdx, index);
		}
		
		
		//make an int array and a list with the project method
		List<Integer> list = project(array);
		int [] arrays = new int[list.size()];

		
		//turn the values into 0s and 1s depending on true or false
		for(int index = 0; index < arrays.length; ++index) {
			arrays[index] = list.get(index);
		}
		//call the project method to get the projection and return
		return arrays;
	}
	
	/**
	 * This method is used to project the column
	 * 
	 * @param colIdx the given column index
	 * @return return the projection of the column with the given index
	 */
	public int [] projectCellStatesCol(int colIdx){
		int numRows = rowClues.length;
		//make a array with the instance variable
				boolean [] array = new boolean[numRows];
				//loop through it 
				for(int index = 0; index < numRows; ++index) {
					//set the index of the array to the cell state at that index
					array[index] = getCellStateAsBoolean(index, colIdx);
					//array[index] = false;
				}
				//make an int array and a list with the project method
				List<Integer> list = project(array);
				int [] arrays = new int[list.size()];

				
				//turn the values into 0s and 1s depending on true or false
				for(int index = 0; index < arrays.length; ++index) {
					arrays[index] = list.get(index);
				}
				//call the project method to get the projection and return
				return arrays;
	}
	
	

	// This is implemented for you
	private static CellState[][] initCellStates(int numRows, int numCols) {
		// Create a 2D array to store numRows * numCols elements
		CellState[][] cellStates = new CellState[numRows][numCols];
		
		// Set each element of the array to empty
		for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
			for (int colIdx = 0; colIdx < numCols; ++colIdx) {
				cellStates[rowIdx][colIdx] = CellState.EMPTY;
			}
		}
		
		// Return the result
		return cellStates;
	}
	
	private static int[][] deepCopy(int[][] array) {//got code from https://stackoverflow.com/questions/1564832/how-do-i-do-a-deep-copy-of-a-2d-array-in-java
		//if its null return null
		if (array == null) {
	        return null;
	    }
		//make a 2d array and read everything in as a copy
	    final int[][] array2 = new int[array.length][];
	    for (int i = 0; i < array.length; i++) {
	        array2[i] = Arrays.copyOf(array[i], array[i].length);
	    }
	    //return the deep copy
	    return array2;
		
	}
	
	// This method is implemented for you. You need to figure out how it is useful.
	private static int[][] readClueLines(BufferedReader reader, int numLines)
			throws IOException {
		// Create a new 2D array to store the clues
		int[][] clueLines = new int[numLines][];
		
		// Read in clues line-by-line and add them to the array
		for (int lineNum = 0; lineNum < numLines; ++lineNum) {
			// Read in a line
			String line = reader.readLine();
			
			// Split the line according to the delimiter character
			String[] tokens = line.split(DELIMITER);
			
			// Create new int array to store the clues in
			int[] clues = new int[tokens.length];
			for (int idx = 0; idx < tokens.length; ++idx) {
				clues[idx] = Integer.parseInt(tokens[idx]);
			}
			
			// Store the processed clues in the resulting 2D array
			clueLines[lineNum] = clues;
		}
		
		// Return the result
		return clueLines;
	}
	
}
