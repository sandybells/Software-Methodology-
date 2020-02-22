/*
 * Celine Demorre & Sandy Ghbrial 
 */
	
package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.*;


public class SongLib extends Application{
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/Scene.fxml"));
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = loader.load();
		File file = new File("SavedSongs.txt");
		
		Controller controller = loader.getController();
		controller.loadSong(file);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Song Library");
		primaryStage.show();
	
	}
	
	@Override
	public void stop() {
		File file = new File("SavedSongs.txt");
		
		Controller controller = loader.getController();
		controller.saveSong(file);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
