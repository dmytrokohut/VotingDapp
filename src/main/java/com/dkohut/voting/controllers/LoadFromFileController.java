package com.dkohut.voting.controllers;

import java.io.IOException;
import java.util.logging.Logger;

import com.dkohut.voting.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class responsible for loading credentials to application.
 * 
 * @author Dmytro Kohut
 *
 */
public class LoadFromFileController {
	
	// TextFields
	@FXML private TextField passwordField;
	
	// Buttons
	@FXML private Button okButton;
	@FXML private Button cancelButton;
	
	private static final Logger logger = Logger.getLogger(LoadFromFileController.class.getName());
	

	public void showDialog() {
		Stage stage = new Stage();
		BorderPane pane;
		
		try {			
			pane = (BorderPane)FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/LoadFromFile.fxml"));
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.setTitle("Enter password");
			stage.setResizable(false);
			stage.show();		
			
		} catch(IOException | NullPointerException e) {
			logger.info("Window for credential loading not loaded\n" + e.getMessage() + "\n" + e.getStackTrace());
			e.getStackTrace();
		}
	}
	
	public void enterPassword(ActionEvent actionEvent) {
		String password = passwordField.getText();
		if(password.equals(""))
			return;
		
		Main.activeForm.loadCredentailsFile(password);
		cancel(actionEvent);
	}	
	
	public void cancel(ActionEvent actionEvent) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
}