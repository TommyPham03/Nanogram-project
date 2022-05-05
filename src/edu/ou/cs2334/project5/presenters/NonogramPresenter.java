package edu.ou.cs2334.project5.presenters;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.handlers.OpenHandler;
import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.CellView;
import edu.ou.cs2334.project5.views.NonogramView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Class to connect the graphical view and model data
 * @author Tommy Pham
 *
 */
public class NonogramPresenter implements Openable {
	
	private NonogramView view;
	private NonogramModel model;
	private int cellLength;
	private static final String DEFAULT_PUZZLE = "puzzles/space-invader.txt";
	
	/**
	 * Nonogrampresenter constructor
	 * 
	 * @param cellLength the cell length
	 * @throws IOException throw an IOException
	 */
	public NonogramPresenter(int cellLength) throws IOException {
		//assign instance variables
		this.cellLength = cellLength;
		model = new NonogramModel(DEFAULT_PUZZLE);
		view = new NonogramView();
		initializePresenter();
		
	}
	
	/**
	 * method to initialize the presenter
	 */
	public void initializePresenter() {
		//make sure stuff are done
		initializeView();
		bindCellViews();
		synchronize();
		configureButtons();
		
	}
	
	/**
	 * method to initialize the view
	 */
	public void initializeView() {
		view.initialize(model.getRowClues(), model.getColClues(), cellLength);
		
		//if window is not null then size it to scene
		if(getWindow() != null) {
			getWindow().sizeToScene();
		}
	}
	
	/**
	 * method to bind the cell views through adding EventHandlers
	 */
	public void bindCellViews() {//Got code from Ewan Green
		
		//make a nested for loop to go through all the varaibles
		for(int index = 0; index < model.getNumRows(); index++) {
			for(int j = 0; j < model.getNumCols(); j++) {
				//make a view view for every variable
				//CellView views = view.getCellView(index, j);
				
				int one = index;
				int two = j;
				
				//add to MouseButton
				view.getCellView(index, j).setOnMouseClicked(new EventHandler<MouseEvent>() {//got help from Daniel Yowell on how to do this part
					public void handle(MouseEvent event) {
					if(event.getButton() == MouseButton.PRIMARY) {
						handleLeftClick(one, two);
					}
					else if(event.getButton() == MouseButton.SECONDARY) {
						handleRightClick(one, two);
					}
					}
				});
			}
		}
		
	}
	
	/**
	 * method to handle left click
	 * 
	 * @param rowIdx the given row index
	 * @param colIdx the given column index
	 */
	public void handleLeftClick(int rowIdx, int colIdx) {//got help from Ewan Green
		boolean variable = model.getCellStateAsBoolean(rowIdx, colIdx);
		//if it is filled set it to empty
		if(variable == true) {
			updateCellState(rowIdx, colIdx, CellState.EMPTY);
		}
		//if empty set to filled
		updateCellState(rowIdx, colIdx, CellState.FILLED);
	}
	
	/**
	 * method to handle right click
	 * 
	 * @param rowIdx the given row index
	 * @param colIdx the given column index
	 */
	public void handleRightClick(int rowIdx, int colIdx) {//got help from Ewan Green
		//make a state variable to check
		CellState state = model.getCellState(rowIdx, colIdx);
		//if it is empty set it to marked
		if(state == CellState.EMPTY) {
			updateCellState(rowIdx, colIdx, CellState.MARKED);
		}
		//if it is filled set it to marked
		else if(state == CellState.FILLED) {
			updateCellState(rowIdx, colIdx, CellState.MARKED);
		}
		//if marked set to empty
		updateCellState(rowIdx, colIdx, CellState.EMPTY);
	}
	
	/**
	 * method to update the model, view, and show player's victory
	 * 
	 * @param rowIdx the given row index
	 * @param colIdx the given column index
	 * @param state the state of the cell
	 */
	public void updateCellState(int rowIdx, int colIdx, CellState state) {
		
		
		//update the model 
		CellState states = model.getCellState(rowIdx, colIdx);
		model.setCellState(rowIdx, colIdx, state);
		
		//check to see if the view is changed
		if(states != model.getCellState(rowIdx, colIdx)) {
		view.setCellState(rowIdx, colIdx, state);
		view.setRowClueState(rowIdx, model.isRowSolved(rowIdx));
		view.setColClueState(colIdx, model.isColSolved(colIdx));
		}
		
		//if it is solved show the victory
		if(model.isSolved() == true) {
			processVictory();
		}
		
	}
	
	/**
	 *method to synchronize the state of the model and the view
	 */
	public void synchronize() {//Got help from Ewan Green
		//make a nested for loop to sycnhcronize the cell views with the cell states
		for(int row = 0; row < model.getNumRows(); ++row) {
			for(int column = 0; column < model.getNumCols(); ++column) {
				//make a state to put inside the setCellState method
				CellState state = model.getCellState(row, column);
				view.setCellState(row, column, state);
			}
		}
		
		
		//set the rows and columns
		for(int index = 0; index < model.getNumRows(); ++index) {
			view.setRowClueState(index, model.isRowSolved(index));
		}
		
		for(int index = 0; index < model.getNumCols(); ++index) {
			view.setColClueState(index, model.isColSolved(index));
		}
		
		//set the puzzle state
		view.setPuzzleState(model.isSolved());
		
		//if it is solved show the victory
		if(model.isSolved() == true) {
			processVictory();
		}
		
	}
	
	/**
	 * method to handle player victory
	 */
	public void processVictory(){
		//remove marks from cell view
		removeCellViewMarks();
		//show victory alert
		view.showVictoryAlert();
	}
	
	/**
	 * method to remove all marks from the view
	 */
	public void removeCellViewMarks() {
		//nested for loop to go through all the elements
		for(int index = 0; index < model.getNumRows(); ++ index) {
			for(int j = 0; j < model.getNumCols(); ++j) {
				//if it is marked then set it to empty
				if(model.getCellState(index, j) == CellState.MARKED)
				view.setCellState(index, j, CellState.EMPTY);
			}
		}
	}
	
	/**
	 * method to set the actions for the load and reset buttons
	 */
	public void configureButtons() {
		//set event handler for load
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getLoadButton().setOnAction(new OpenHandler(getWindow(), fileChooser, this));
		
	
		view.getResetButton().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				resetPuzzle();
			}
			
		});
	}
	
	/**
	 * method to clear the model
	 */
	public void resetPuzzle() {
		model.resetCells();
		synchronize();
	}
	
	/**
	 * pane getter
	 * 
	 * @return return the pane
	 */
	public Pane getPane() {
		return view;
	}
	
	/**
	 * window getter
	 * 
	 * @return return the window
	 */
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
	
	/**
	 * method to open the file
	 */
	public void open(File file) throws IOException {
		model = new NonogramModel(file);
		initializePresenter();
	}
}
