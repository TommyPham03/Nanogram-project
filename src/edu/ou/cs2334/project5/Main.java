package edu.ou.cs2334.project5;

import java.io.IOException;

import edu.ou.cs2334.project5.presenters.NonogramPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * main class
 * @author Tommy Pham
 *
 */
public class Main extends Application {
	private static int IDX_CELL_SIZE = 0;
	private int DEFAULT_CELL_SIZE = 30;
	
	/**
	 * main constructor 
	 * 
	 * @param args string array
	 */
	public static void main(String [] args) {
		launch(args);
		
	}
	
	/**
	 * start method
	 * 
	 * @param primaryStage the stage of JavaFX
	 */
	public void start(Stage primaryStage) throws IOException {
		//make a cell size to add into the presenter
		int cell = Integer.parseInt(getParameters().getUnnamed().get(IDX_CELL_SIZE));
		
		//make a presenter and check what to add depending if it is empty or not
		NonogramPresenter presenter;
		if(getParameters().getUnnamed().isEmpty()) {
			 presenter = new NonogramPresenter(DEFAULT_CELL_SIZE);
		}
		else {
			presenter = new NonogramPresenter(cell);
		}
		
		//create a scene 
		Scene scene = new Scene(presenter.getPane());
		//add the style sheet
		scene.getStylesheets().add("/style.css");
		
		//set the scene
		primaryStage.setScene(scene);
		//set the title
		primaryStage.setTitle("name");
		//prevent the window from resizing
		primaryStage.setResizable(false);
		//show the window
		primaryStage.show();
	}

}
