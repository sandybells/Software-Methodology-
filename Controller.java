/*
 * Celine Demorre & Sandy Ghbrial 
 */

package controller;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import song.Songs;
import javafx.fxml.*;
import javafx.beans.value.ChangeListener;

public class Controller {

	@FXML
	private Button applyButton, addbutton, deletebutton, editbutton, addCancel, editCancel, addingButton;
	@FXML
	TextField enteredname, enteredartist, enteredalbum, enteredyear, songDetailName, detailArtist, detailAlbum, detailYear;

	private ObservableList<Songs> list = FXCollections.observableArrayList();

	@FXML
	ListView<Songs> songList = new ListView<Songs>(list);
	
	
	
	

	String method = ""; // use this for the confirm method

	public void start(Stage mainStage) throws IOException {
		
		
	//	songList.getSelectionModel().select(0);
	//	songList.getSelectionModel().selectedIndexProperty().addListener(arg0);

		// load the text in here

	}

	public void addingSongPressed() {
		
		
		//User cant edit or delete while adding, one action at a time.
		editbutton.setDisable(true);
		deletebutton.setDisable(true);

		enteredname.setVisible(true);
		enteredartist.setVisible(true);
		enteredalbum.setVisible(true);
		enteredyear.setVisible(true);

		addCancel.setVisible(true);
		addbutton.setVisible(false);
		addingButton.setVisible(true);
	}
	

	public void addPressed() {
		
		


		String name = enteredname.getText();
		String artist = enteredartist.getText();
		String album = enteredalbum.getText();
		String year = enteredyear.getText();

		if (name.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error Adding Song");
			alert.setContentText("You cannot add a song without a name!");
			alert.showAndWait();

		} else if (artist.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error Adding Song");
			alert.setContentText("You cannot add a song without an artist!");
			alert.showAndWait();
		} else if (!year.isEmpty() && !yearValid(year)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error Adding Song");
			alert.setContentText("You did not add a correct year!");
			alert.showAndWait();
		} else {

			if (inList(name, artist)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error Adding Song");
				alert.setContentText("You already added this song!");
				alert.showAndWait();
			} else {

				Songs addedsong = new Songs(name, artist, album, year);
				method = "add";
				if (confirm(method)) {
					// user wants to proceed with adding the song

					list.add(addedsong);
					songList.setItems(list.sorted());
					songList.getSelectionModel().select(addedsong);
					songDetailName.setText(addedsong.getName());
					songDetailName.setEditable(false);
					detailArtist.setText(addedsong.getArtist());
					detailArtist.setEditable(false);
					detailArtist.setFocusTraversable(false);

					detailAlbum.setText(addedsong.getAlbum());
					detailAlbum.setEditable(false);
					detailYear.setText(addedsong.getYear());
					detailYear.setEditable(false);
					
					
				}
			}
			
			editbutton.setDisable(false);
			deletebutton.setDisable(false);

			addbutton.setVisible(true);
			addingButton.setDisable(false);
			addingButton.setVisible(false);
			enteredname.setVisible(false);
			enteredname.clear();
			enteredartist.setVisible(false);
			enteredartist.clear();
			enteredalbum.setVisible(false);
			enteredalbum.clear();
			enteredyear.setVisible(false);
			enteredyear.clear();
			addCancel.setVisible(false);
		}

	}
	
	

	public boolean confirm(String method) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Are you sure you would like to " + method + " this song?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	public void addCancelled() {
		
		
		editbutton.setDisable(false);
		deletebutton.setDisable(false);

		enteredname.setVisible(false);
		enteredname.clear();
		enteredartist.setVisible(false);
		enteredartist.clear();
		enteredalbum.setVisible(false);
		enteredalbum.clear();
		enteredyear.setVisible(false);
		enteredyear.clear();

		addCancel.setVisible(false);
		addbutton.setVisible(true);
		addingButton.setVisible(false);
	}

	public Boolean inList(String songname, String artist) {
		for (Songs song : list) {
			if (song.getName().toLowerCase().equals(songname.toLowerCase())
					&& song.getArtist().toLowerCase().equals(artist.toLowerCase())) {
				return true;
			}
		}

		return false;
	}

	public Boolean yearValid(String year) {

		try {
			int numyear = Integer.parseInt(year);

			if (numyear > 2020) {
				return false;
			} else {
				return true;
			}
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

//	public void deletePressed() {
//		deletebutton.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent event) {
//				if (songList.getItems().isEmpty()) {
//					return;
//				}
//
//			}
//		});
//	}
	

	public void deletePressed() {
//		if(songList.getItems().isEmpty()) {
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("Error");
//			alert.setContentText("You cannot delete from an empty list!");
//			alert.showAndWait();
//		} 
		if (!(songList.getItems().isEmpty())){
			Songs deleteSong = songList.getSelectionModel().getSelectedItem();
			int deleteIndex = songList.getSelectionModel().getSelectedIndex();
			
			method = "delete";
			if(confirm(method)) {
				//user wants to delete song
				
				list.remove(deleteSong);
				
				if (list.isEmpty()) {
					songDetailName.clear();;
					detailArtist.clear();
					detailAlbum.clear();
					detailYear.clear();
					return;
					
				} else if(list.size() == deleteIndex) {
					songList.getSelectionModel().select(deleteIndex - 1);
				
				} else {
					songList.getSelectionModel().select(deleteIndex + 1);
					
				}

				songList.setItems(list.sorted());
				Songs newSong = songList.getSelectionModel().getSelectedItem();
				songDetailName.setText(newSong.getName());
				detailArtist.setText(newSong.getArtist());
				detailAlbum.setText(newSong.getAlbum());
				detailYear.setText(newSong.getYear());
				
			}
			
			
		}
		
	}
	
	public void editDelete() {
		if(songList.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setContentText("You cannot delete from an empty list!");
			alert.showAndWait();
		} else {
			Songs deleteSong = songList.getSelectionModel().getSelectedItem();
			int deleteIndex = songList.getSelectionModel().getSelectedIndex();
			
			
			
				
				list.remove(deleteSong);
				
				if (list.isEmpty()) {
					songDetailName.clear();;
					detailArtist.clear();
					detailAlbum.clear();
					detailYear.clear();
					return;
					
				} else if(list.size() == deleteIndex) {
					songList.getSelectionModel().select(deleteIndex - 1);
				
				} else {
					songList.getSelectionModel().select(deleteIndex + 1);
					
				}

			
			
			
			
		}
		
	}

	public void editPressed() {
		
		if (songList.getItems().isEmpty()) {
			return;
		}

		songDetailName.setEditable(true); // this will have to be set false at the end of the edit method!!
	
		detailArtist.setEditable(true);

		detailAlbum.setEditable(true);
		detailYear.setEditable(true);
		
		addbutton.setDisable(true);
		deletebutton.setDisable(true);

		
		
				editbutton.setVisible(false);
				editCancel.setVisible(true);
				//editbutton.setText("Apply changes");
				applyButton.setVisible(true);
			
		
	}
	public void applyPressed() {
		
		

	
		
		
		
		String name = songDetailName.getText();
		String artist = detailArtist.getText();
		String album = detailAlbum.getText();
		String year = detailYear.getText();
		
		
	
		
		
		//check here if song is already in the list 
		if (name.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error Editing Song");
			alert.setContentText("You cannot add a song without a name!");
			alert.showAndWait();

		} else if (artist.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error Editing Song");
			alert.setContentText("You cannot add a song without an artist!");
			alert.showAndWait();
		} else if (!year.isEmpty() && !yearValid(year)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error Editing Song");
			alert.setContentText("You did not add a correct year!");
			alert.showAndWait();
		} else {

			if (inList(name, artist)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error Editing Song");
				alert.setContentText("This song already exists!");
				alert.showAndWait();
			} else {

			//	Songs addedsong = new Songs(name, artist, album, year);
				method = "edit";
				if (confirm(method)) {
					// user wants to proceed with edting the song
					
					Songs selected = songList.getSelectionModel().getSelectedItem();
					
					selected.setName(name); 
					selected.setArtist(artist); 
					selected.setAlbum(album);
					selected.setYear(year); 
					
					editDelete(); //delete old version 

					list.add(selected);
					songList.setItems(list.sorted());
					songList.getSelectionModel().select(selected);
					

					songDetailName.setText(selected.getName());
					detailArtist.setText(selected.getArtist());
					detailAlbum.setText(selected.getAlbum());
					detailYear.setText(selected.getYear());
					
					
					songDetailName.setEditable(false); //this will have to be set false at the end of the edit method!!
					
					detailArtist.setEditable(false);

					detailAlbum.setEditable(false);
					detailYear.setEditable(false);
					
					editCancel.setVisible(false);
					editbutton.setVisible(true);
					applyButton.setVisible(false);
					
					addbutton.setDisable(false);
					deletebutton.setDisable(false);
					
					detailArtist.setFocusTraversable(false);

					
					
				}
			}
		}
		
		

	//	Songs newSong = songList.getSelectionModel().getSelectedItem(); //whats it selecting now 
		
		

		
		
		
	}
	
	public void editCancelPressed() {
		
		applyButton.setVisible(false);
		editCancel.setVisible(false);
		editbutton.setVisible(true);
		
		addbutton.setDisable(false);
		deletebutton.setDisable(false);
		

		
		songDetailName.setEditable(false); //this will have to be set false at the end of the edit method!!
		detailArtist.setEditable(false);
		detailAlbum.setEditable(false);
		detailYear.setEditable(false);
		
		
	}
	

		
		public void listClicked() {
			
			if (   !(songList.getItems().isEmpty())   ) {
				Songs newSong = songList.getSelectionModel().getSelectedItem();
				songDetailName.setText(newSong.getName());
				detailArtist.setText(newSong.getArtist());
				detailAlbum.setText(newSong.getAlbum());
				detailYear.setText(newSong.getYear());
				
			}
		
		}
		
	
	
		
	


		

	
}
