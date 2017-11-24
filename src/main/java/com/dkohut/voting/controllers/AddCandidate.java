package com.dkohut.voting.controllers;

import java.io.IOException;
import java.util.logging.Logger;

import com.dkohut.voting.Main;
import com.dkohut.voting.entity.Candidate;
import com.dkohut.voting.wrappers.Voting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * This class responsible for adding new candidates for voting.
 * 
 * @author Dmytro Kohut
 *
 */
public class AddCandidate {
	
	// TextField
	@FXML TextField nameField;
	
	// Button
	@FXML Button addButton;
	@FXML Button closeButton;
	
	private static final Logger logger = Logger.getLogger(AddCandidate.class.getName());
	

	/**
	 * This method show AddCandidate dialog window.
	 */
	public void showDialog() {
		Stage stage = new Stage();
		BorderPane pane;
		
		try {			
			pane = (BorderPane)FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/AddCandidate.fxml"));
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.setTitle("Add Candidate");
			stage.setResizable(false);
			stage.show();		
			
		} catch(IOException | NullPointerException e) {
			logger.info("Window for candidate adding not loaded\n" + e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	
	/**
	 * This method add new candidate into list of candidates.
	 * 
	 * @param ActionEvent actionEvent
	 */
	public void add(ActionEvent actionEvent) {
		Voting voting = Main.getVoting();
		Main.addCandidate(new Candidate(nameField.getText(), 0));
		
		try {
			voting.addCandidate(Main.stringToBytes32(nameField.getText())).send();
			nameField.setText("");			
			System.out.println("Candidate Added");
			
		} catch (Exception e) {
			logger.info("New candidate not added\n" + e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	/**
	 * This method close AddCandidate dialog window.
	 * 
	 * @param ActionEvent actionEvent
	 */
	public void close(ActionEvent actionEvent) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	
}
