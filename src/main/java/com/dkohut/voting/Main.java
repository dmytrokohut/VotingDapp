package com.dkohut.voting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import com.dkohut.voting.controllers.AddCandidate;
import com.dkohut.voting.controllers.LoadContractController;
import com.dkohut.voting.controllers.LoadCredentialsController;
import com.dkohut.voting.controllers.LoadFromFileController;
import com.dkohut.voting.entity.Candidate;
import com.dkohut.voting.wrappers.Voting;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rx.Subscription;
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
	

	private Web3j web3j;	
	private Credentials credentials;	
	private Voting voting;	
	private ObservableList<Candidate> candidateList = FXCollections.observableArrayList();
	
	private Subscription newCandidateSubscription;
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	
	private final FileChooser fileChooser = new FileChooser();
	private Stage stage;
	
	// This variable as it defines opened Main form necessary another form.
	public static Main activeForm;
	

	public Main() {
		Main.activeForm = this;
		web3j = Web3j.build(new HttpService("http://localhost:8545"));
	}	
	
	public static void main(String[] args) {
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
			this.stage = stage;
			
		} catch(IOException | NullPointerException e) {
			logger.log(Level.SEVERE, "Error during Main window loading\n" + e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	/**
	 * This method responsible for contract deploying.
	 * 
	 * @param ActionEvent actionEvent
	 */
	public void deployContract(ActionEvent actionEvent) {		
		Voting.deploy(web3j, credentials, Voting.GAS_PRICE, Voting.GAS_LIMIT)
		.sendAsync()
		.thenAccept(acceptedVoting -> {
			voting = acceptedVoting;
			newCandidatesEvents();
		})
		.exceptionally(error -> {
			logger.log(Level.SEVERE, "Error during contract deploying\n" + error.getMessage() + "\n" + error.getStackTrace());
			return null;
		});
	}	
	
	/**
	 * This method executes voting for the chosen candidate in TableView.
	 * 
	 * @param ActionEvent actionEvent
	 */
	public void vote(ActionEvent ActionEvent) {
		Candidate selectedCandidate = (Candidate) candidatesTable.getSelectionModel().getSelectedItem();
		
		Bytes32 candidate = stringToBytes32(selectedCandidate.getName());
		
		voting.voteForCandidate(candidate)
		.sendAsync()
		.thenAccept(transaction -> {
			logger.info("Voted, transaction hash = " + transaction.getTransactionHash());
			
			voting.votesReceived(candidate)
			.sendAsync()
			.thenAccept(response -> {
				selectedCandidate.setVotes(response.getValue().intValue());
				candidatesTable.refresh();
			})
			.exceptionally(error -> {
				logger.log(Level.SEVERE, "Error during getting sum of votes of candidate" + error.getMessage() + "\n" + error.getStackTrace());
				return null;
			});
		})
		.exceptionally(error -> {
			logger.log(Level.SEVERE, "You can vote only once\n" + error.getMessage() + "\n" + error.getStackTrace());
			return null;
		});
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
	
	/**
	 * This method send basic info to contract and than filling list of canidates.   
	 */
	public void initializeInfo() {
		if(!candidateList.isEmpty())
			return;
		
		List<Bytes32> canidateListBytes32 = new ArrayList<>();
		
		candidateList.add(new Candidate("Dima", 0));
		candidateList.add(new Candidate("Rama", 0));
		candidateList.add(new Candidate("Jack", 0));
		
		candidateList.forEach(candidate -> {
			canidateListBytes32.add(stringToBytes32(candidate.getName()));
		});
		
		DynamicArray<Bytes32> arrayOfCanidates = new DynamicArray<Bytes32>(canidateListBytes32);
		
		voting.init(arrayOfCanidates)
		.sendAsync()
		.thenAccept(transaction -> {
			logger.info("Succed execution of init operation, transaction = " + transaction.getTransactionHash());
			setTableRecords();
			initializedEvent(transaction);
		})
		.exceptionally(error -> {
			logger.log(Level.SEVERE, "Error during init execution\n" + error.getMessage() + "\n" + error.getStackTrace());
			return null;
		});		
	}	
	
	/**
	 * This method load existing in network contract.
	 * 
	 * @param String contractAddress
	 */
	public void setContract(String contractAddress) {
		voting = Voting.load(contractAddress, web3j, credentials, Voting.GAS_PRICE, Voting.GAS_LIMIT);
		
		voting.getCandidateList()
		.sendAsync()
		.thenAccept(candidates -> {
			candidates.getValue().forEach(candidate -> {
				voting.votesReceived(candidate)
				.sendAsync()
				.thenAccept(votes -> {
					candidateList.add(new Candidate(new String(candidate.getValue()), votes.getValue().intValue()));
				})
				.exceptionally(error -> {
					logger.log(Level.SEVERE, 
							"Error during getting the votes of candidate\n" + error.getMessage() + "\n" + error.getStackTrace());
					return null;
				});
			});
			
			setTableRecords();
			newCandidatesEvents();
		})
		.exceptionally(error -> {
			logger.log(Level.SEVERE, 
					"Error during getting the list of candidates\n" + error.getMessage() + "\n" + error.getStackTrace());
			return null;
		});
	}
	
	/**
	 * This method destroys deployed contract.
	 * NOTE: it is possible only if you are deployer of the contract.
	 * 
	 * @param ActionEvent actionEvent
	 */
	public void killContract(ActionEvent actionEvent) {
		voting.kill()
		.sendAsync()
		.thenAccept(transaction -> {
			logger.log(Level.SEVERE, "Contract with address destroyed, local logger\n");
			candidateList.clear();
			candidatesTable.refresh();
		})
		.exceptionally(error -> {
			logger.log(Level.SEVERE, "Error during contract destroying");
			return null;
		});
	}
	
	private void newCandidatesEvents() {
		newCandidateSubscription = voting.newCandidateEventObservable(
				DefaultBlockParameterName.EARLIEST,
				DefaultBlockParameterName.LATEST
				)
		.subscribe(log -> {
			logger.info("New candidate added, " + log.toString());
		});		
	}	
	
	private void initializedEvent(TransactionReceipt transactionReceipt) {
		voting.getInitializedEvents(transactionReceipt)
		.forEach(log -> {
			logger.info("Contract initilize transaction has completed\n");
		});
	}
	
	public void loadContract(ActionEvent actionEvent) {
		LoadContractController controller = new LoadContractController();
		controller.showDialog();
	}
	
	public void openLoadCredentials() {
		LoadCredentialsController controller = new LoadCredentialsController();
		controller.showDialog();
	}	
	
	public void setCredentials(String privateKey) {
		credentials = Credentials.create(privateKey);
	}	
	
	public void loadCredentialsFromFile(ActionEvent actionEvent) {
		LoadFromFileController controller = new LoadFromFileController();
		controller.showDialog();
	}	
	
	public void loadCredentailsFile(String password) {
		File file = fileChooser.showOpenDialog(stage);
		if(file != null) {
			try {
				credentials = WalletUtils.loadCredentials(password, file);
			} catch(IOException | CipherException e ) {
				logger.log(Level.SEVERE, "Error during file opening\n" + e.getMessage() + "\n" + e.getStackTrace());
			}
		}		
	}	
	
	public void addCandidate(Candidate candidate) {
		candidateList.add(candidate);
	}	
	
	public Voting getVoting() {
		return voting;
	}
	
	private void setTableRecords() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Candidate, String>("name"));
		votesColumn.setCellValueFactory(new PropertyValueFactory<Candidate, Integer>("votes"));	

		candidatesTable.setItems(candidateList);
	}	
	
	public void newCandidateEventSubscribe(ActionEvent actionEvent) {
		newCandidatesEvents();
	}
	
	public void newCandidateEventUnsubscribe(ActionEvent actionEvent) {
		if(!newCandidateSubscription.isUnsubscribed())
			newCandidateSubscription.unsubscribe();
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
	
	@Override
	public void stop() {
		logger.info("Exit application!");		
	}
	
}
