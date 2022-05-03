package edu.ou.cs2334.project5;

import java.io.IOException;

import edu.ou.cs2334.project5.presenters.NonogramPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private static int IDX_CELL_SIZE = 0;
	private int DEFAULT_CELL_SIZE = 30;
	
	public void main(String [] args) {
		launch(args);
		
	}
	
	public void start(Stage primaryStage) throws IOException {
		int defaults = Integer.parseInt(getParameters().getUnnamed().get(DEFAULT_CELL_SIZE));
		int cell = Integer.parseInt(getParameters().getUnnamed().get(IDX_CELL_SIZE));
		
		//make a presenter
		NonogramPresenter presenter = new NonogramPresenter(DEFAULT_CELL_SIZE);
		
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
