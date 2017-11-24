package com.dkohut.voting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.dkohut.voting.controllers.AddCandidate;
import com.dkohut.voting.controllers.LoadContractController;
import com.dkohut.voting.controllers.LoadCredentialsController;
import com.dkohut.voting.entity.Candidate;
import com.dkohut.voting.wrappers.Voting;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


/**
 * This class give to users capability of voting for candidate, but only one time.
 * 
 * @author Dmytro Kohut
 *
 */
public class Main extends Application {

	// Button
	@FXML Button voteButton;
	
	// TableView
	@FXML TableView<Candidate> candidatesTable;
	
	// TableColumn
	@FXML TableColumn<Candidate, String> nameColumn;
	@FXML TableColumn<Candidate, Integer> votesColumn;
	
	// MenuItem
	@FXML MenuItem addCandidate;	
	@FXML MenuItem deployMenuItem;
	

	private static Web3j WEB3J;	
	private static Credentials CREDENTIALS;	
	private static Voting VOTING;	
	private static ObservableList<Candidate> candidateList = FXCollections.observableArrayList();	
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	

	public static void main(String[] args) {
		WEB3J = Web3j.build(new HttpService("http://localhost:8545"));		
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		Stage stage = new Stage();
		BorderPane pane;
		
		try {			
			pane = (BorderPane)FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/Main.fxml"));
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.setTitle("Main Form");
			stage.setResizable(false);
			stage.show();		
			
		} catch(IOException | NullPointerException e) {
			logger.info("Main window not loaded\n" + e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	
	/**
	 * This method responsible for contract deploying.
	 * 
	 * @param ActionEvent actionEvent
	 */
	public void deployContract(ActionEvent actionEvent) {		
		try {			
			VOTING = Voting
					.deploy(WEB3J, CREDENTIALS, Voting.GAS_PRICE, Voting.GAS_LIMIT)
					.send();
			
			System.out.println("Contract deployed");
			setTableRecords();
			
		} catch (Exception e) {
			logger.info("Contract not deployed\n" + e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	
	public void loadContract(ActionEvent actionEvent) {
		LoadContractController controller = new LoadContractController();
		controller.showDialog();
		logger.info("End of loadContract");
	}
	
	
	/**
	 * This method executes voting for the chosen candidate in TableView.
	 * 
	 * @param ActionEvent actionEvent
	 */
	public void vote(ActionEvent ActionEvent) {
		Candidate selectedCandidate = (Candidate) candidatesTable.getSelectionModel().getSelectedItem();
		try {			
			VOTING.voteForCandidate(stringToBytes32(selectedCandidate.getName())).send();
			
			Uint8 result = VOTING
					.votesReceived(stringToBytes32(selectedCandidate.getName()))
					.send();		
			selectedCandidate.setVotes(result.getValue().intValue());
			candidatesTable.refresh();
			
			System.out.println("Voted");
			
		} catch (Exception e) {
			logger.info("You can't vote more than 1 time\n" + e.getMessage() + "\n" + e.getStackTrace());
		}
	}
		
	
	/**
	 * This method open new dialog window for candidate adding.
	 * 
	 * @param ActionEvent actionEvent
	 */
	public void addCandidate(ActionEvent actionEvent) {
		AddCandidate addCandidate = new AddCandidate();
		addCandidate.showDialog();
	}
	
	
	public void openLoadCredentials() {
		LoadCredentialsController controller = new LoadCredentialsController();
		controller.showDialog();
	}
	
	
	/**
	 * This method load existing in network contract.
	 * 
	 * @param String contractHash
	 */
	public static void setContract(String contractHash) {
		try {
			VOTING = Voting
					.load(contractHash, WEB3J, CREDENTIALS, Voting.GAS_PRICE, Voting.GAS_LIMIT);
			
			DynamicArray<Bytes32> response = VOTING
					.getCandidateList()
					.send();
			
			for(Bytes32 item : response.getValue()) {
				Uint8 votesOfCandidate = VOTING
						.votesReceived(item)
						.send();
				
				candidateList.add(new Candidate(new String(item.getValue()), votesOfCandidate.getValue().intValue()));
			}
			
			System.out.println("Contract loaded");
			
		} catch (Exception e) {
			logger.info("Contract not loaded\n" + e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	
	public static void setCredentials(String privateKey) {
		CREDENTIALS = Credentials.create(privateKey);
	}
	
	
	/**
	 * This method convert String type variable into Bytes32 variable. 
	 * 
	 * @param String string
	 * @return Bytes32
	 */
	public static Bytes32 stringToBytes32(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return new Bytes32(byteValueLen32);
    }
	
	
	public static void addCandidate(Candidate candidate) {
		candidateList.add(candidate);
	}
	
	
	public static Voting getVoting() {
		return VOTING;
	}

	
	private void setTableRecords() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Candidate, String>("name"));
		votesColumn.setCellValueFactory(new PropertyValueFactory<Candidate, Integer>("votes"));	

		candidatesTable.setItems(candidateList);
	}	
	
}
