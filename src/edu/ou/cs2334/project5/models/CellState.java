package edu.ou.cs2334.project5.models;

public class CellState {

	CellState EMPTY;
	CellState FILLED;
	CellState MARKED;
	
	public static boolean toBoolean(CellState state) {
		//if it is filled return true
		if(state.FILLED.equals(true)) {
			return true;
		}
		//else return false
		return false;
	}
}
