package edu.ou.cs2334.project5.presenters;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.NonogramView;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

/**
 * 
 * @author Tommy Pham
 *
 */
public class NonogramPresenter implements edu.ou.cs2334.project5.interfaces.Openable {
	
	private NonogramView view;
	private NonogramModel model;
	private int cellLength;
	private static final String DEFAULT_PUZZLE = "puzzles/space-invader.txt";
	
	public NonogramPresenter(int cellLength) throws IOException {
		//assign instance variables
		model = new NonogramModel(DEFAULT_PUZZLE);
		view = new NonogramView();
		initializePresenter();
		
	}
	
	public void initializePresenter() {
		
	}
	
	public void initializeView() {
		view.initialize(model.getRowClues(), model.getColClues(), cellLength);
		
		//if window is not null then size it to scene
		if(getWindow() != null) {
			getWindow().sizeToScene();
		}
	}
	
	public void bindCellViews() {
		
	}
	
	public void handleLeftClick(int rowIdx, int colIdx) {
		
	}
	
	public void handleRightClick(int rowIdx, int colIdx) {
		
	}
	
	public void updateCellState(int rowIdx, int colIdx, CellState state) {
		
	}
	
	public void synchronize() {
		
	}
	
	public void processVictory(){
		
	}
	
	public void removeCellViewMarks() {
		view.setCellState(model.getNumRows(), model.getNumCols(), CellState.EMPTY);
	}
	
	public void configureButtons() {
		
	}
	
	public void resetPuzzle() {
		model.resetCells();
		synchronize();
	}
	
	public Pane getPane() {
		return view;
	}
	
	public Window getWindow() {
		//try catch statement to catch exceptions
				try {
					//make a new window to be return
					Window window = view.getScene().getWindow();
					return window;
				}
				catch(NullPointerException e) {
					return null;
				}
		
	}
	
	public void open(File file) throws IOException {
		model = new NonogramModel(file);
		initializePresenter();
	}
}
