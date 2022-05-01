package edu.ou.cs2334.project5.handlers;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * 
 * This class represent a general handler involving file selection
 * 
 * @author Tommy Pham
 *
 *
 */
public abstract class AbstractBaseHandler {
	protected Window window;
	protected FileChooser fileChooser;
	
	protected AbstractBaseHandler(Window window, FileChooser fileChooser) {
		//assign the instance variables
		this.window = window;
		this.fileChooser = fileChooser;
	}
}