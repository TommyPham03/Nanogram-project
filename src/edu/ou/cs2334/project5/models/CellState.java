package edu.ou.cs2334.project5.models;

public enum CellState {

	EMPTY,
	FILLED,
	MARKED;
	
	public static boolean toBoolean(CellState state) {
		//if it is filled return true
		if(state.equals(FILLED)) {
			return true;
		}
		//else return false
		return false;
	}
}
