package edu.ou.cs2334.project5.models;

/**
 * Enum used to represent the state of the NonogramModel grid
 * @author Tommy Pham
 *
 */
public enum CellState {

	/**
	 * empty state
	 */
	EMPTY, 
	/**
	 * filled state
	 */
	FILLED, 
	/**
	 * marked state
	 */
	MARKED;
	
	/**
	 * Helper method to return the boolean value of the state
	 * 
	 * @param state the state the cell is in
	 * @return true or false depending if the state is empty or filled
	 */
	public static boolean toBoolean(CellState state) {
		//if it is filled return true
		if(state.equals(FILLED)) {
			return true;
		}
		//else return false
		return false;
	}
}
