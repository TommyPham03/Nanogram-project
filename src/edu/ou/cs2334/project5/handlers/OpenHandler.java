package edu.ou.cs2334.project5.handlers;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.interfaces.Openable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * 
 * This class extends the AbstraBaseHandler class and is used to show an open dialog
 * @author Tommy Pham
 *
 *
 */
public class OpenHandler extends AbstractBaseHandler implements EventHandler<ActionEvent> {

	private Openable opener;
	
	/**
	 * OpenHandler constructor 
	 * 
	 * @param window the window variable
	 * @param fileChooser the file variable
	 * @param opener the openable variable
	 */
	public OpenHandler(Window window, FileChooser fileChooser, Openable opener) {
		//set instance variables
		super(window, fileChooser);
		this.opener = opener;
	}
	
	/**
	 * This class handles the file chooser variable to show an open dialog
	 * 
	 * @param event the ActionEvent Variable
	 */
	public void handle(ActionEvent event) {
		//show the open dialogue
		File dialog = fileChooser.showOpenDialog(window);
		
		//if not null then call the open method
		if(dialog != null) {
			try {
				opener.open(dialog);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}