package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonogramModel {

	private static final String DELIMITER = " ";
	private static final int IDX_NUM_ROWS = 0;
	private static final int IDX_NUM_COLS = 1;

	private int[][] rowClues;
	private int[][] colClues;
	private CellState[][] cellStates;
	
	public NonogramModel(int[][] rowClues, int[][] colClues) {
		// TODO: Implement deepCopy. 
		
		// This is simple, and you should not ask about this on Discord.
		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}

	public NonogramModel(File file) throws IOException {
		// Number of rows and columns
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String header = reader.readLine();
		String[] fields = header.split(DELIMITER);
		int numRows = Integer.parseInt(fields[IDX_NUM_ROWS]);
		int numCols = Integer.parseInt(fields[IDX_NUM_COLS]);

		// TODO: Initialize cellStates.
		// This is simple, and you should not ask about this on Discord.
		cellStates = initCellStates(getNumRows(), getNumCols());
		
		// TODO: Read in row clues.
		// This is simple, and you should not ask about this on Discord.
		readClueLines(reader, rowClues.length);
		// TODO: Read in column clues.
		// This is simple, and you should not ask about this on Discord.
		readClueLines(reader,colClues.length);
		// Close reader
		reader.close();
	}

	public NonogramModel(String filename) throws IOException {
		// TODO: Fix this constructor
		// This is simple, and you should not ask about this on Discord.
		this(new File(filename));
	}
	
	// TODO: Add more TODOs
	
	/* Helper methods */
	private int getNumRows() {
		return rowClues.length;
	}

	private int getNumCols() {
		return colClues.length;
	}
	
	public CellState getCellState(int rowIdx, int colIdx) {
		//return the state of the cell with the given row and col
		return cellStates[rowIdx][colIdx];
	}
	
	public boolean getCellStateAsBoolean(int rowIdx, int colIdx) {
		//get the cell state
		CellState state = getCellState(rowIdx, colIdx);
		//turn it into a boolean and return
		return CellState.toBoolean(state);
	}
	
	public boolean setCellState(int rowIdx, int colIdx, CellState state) {
		
	}
	
	public int [][] getRowClues(){
		//return a deep copy of row clues
		return deepCopy(rowClues);
	}
	
	public int [][] getColClues(){
		//return a deep copy of col clues
		return deepCopy(colClues);
	}
	
	public int [] getRowClue(int rowIdx){
		//return a copy
		return Arrays.copyOf(rowClues[rowIdx], rowClues[rowIdx].length);
	}
	
	public int [] getColClue(int colIdx){
		//return a copy
		return Arrays.copyOf(colClues[colIdx], colClues[colIdx].length);
	}
	
	public boolean isRowSolved(int rowIdx) {
		return Arrays.equals(rowClues[rowIdx], projectCellStatesRow(rowIdx))
	}
	
	public boolean isColSolved(int colIdx) {
		return Arrays.equals(colClues[colIdx], projectCellStatesCol(colIdx))
	}
	
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
		return false;
	}

	public void resetCells() {
		//for loop to go through all the cell states and set them to false
		for(int index = 0; index < cellStates.length; ++index) {
			cellStates[index] = CellState.EMPTY;
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
	public List<Integer> projectRow (int rowIdx){
		int numCols = colClues.length;
		//make a array with the instance variable
		boolean [] array = new boolean[numCols];
		//loop through it 
		for(int index = 0; index < numCols; ++index) {
			//set the index of the array to the cell state at that index
			array[index] = getCell(rowIdx, index);
		}
		
		//call the project method to get the projection and return
		return project(array);
	}
	
	/**
	 * This method is used to project the column
	 * 
	 * @param colIdx the given column index
	 * @return return the projection of the column with the given index
	 */
	public List<Integer> projectCol(int colIdx){
		int numRows = rowClues.length;
		//make a array with the instance variable
				boolean [] array = new boolean[numRows];
				//loop through it 
				for(int index = 0; index < numRows; ++index) {
					//set the index of the array to the cell state at that index
					array[index] = getCell(index, colIdx);
				}
				
				//call the project method to get the projection and return
				return project(array);
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
	
	// TODO: Implement this method
	private static int[][] deepCopy(int[][] array) {//got code from https://stackoverflow.com/questions/1564832/how-do-i-do-a-deep-copy-of-a-2d-array-in-java
		// You can do this in under 10 lines of code. If you ask the internet
		// "how do I do a deep copy of a 2d array in Java," be sure to cite
		// your source.
		// Note that if we used a 1-dimensional array to store our arrays,
		// we could simply use Arrays.copyOf directly without this helper
		// method.
		// Do not ask about this on Discord. You can do this on your own. :)
		if (array == null) {
	        return null;
	    }

	    final int[][] array2 = new int[array.length][];
	    for (int i = 0; i < array.length; i++) {
	        array2[i] = Arrays.copyOf(array[i], array[i].length);
	    }
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
