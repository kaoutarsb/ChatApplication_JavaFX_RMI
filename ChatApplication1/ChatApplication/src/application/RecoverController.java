package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RecoverController implements Initializable{
	
	@FXML
	private ChoiceBox<String> 	choiceboxQuestion ;
	private String[] Question = {"favorit subject?","favorit sport?", "favorit dish?"};
	@FXML
	private ChoiceBox<String> 	choiceboxAnswer ;
	private String[] subject = {"Math","Programming", "History","Painting","Languages","Physics","Chimestery"};
	private String[] sport = {"Football","Basketball", "voleyball","swimming","course","gymnastic","surffing","boxing"};
	private String[] dish = {"Couscous","Tajine", "Pizza","Pastilla","Pasta","Tacos","Salade","Berger","Pastitsio"};
	
	@FXML
	private Button recoverButton;
	@FXML
	private Button backButton;
	
	@FXML
	private TextField textfieldusername;
	
	@FXML
	private Label recoverpasswordlabel;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceboxQuestion.getItems().addAll(Question);
		choiceboxQuestion.setOnAction(this::setquestionsChoice);
		
		recoverButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				String username= textfieldusername.getText();
				String recoverquestion= choiceboxQuestion.getValue();
				String recoveranswer= choiceboxAnswer.getValue(); 
				
				if (!username.trim().isEmpty() && !recoverquestion.trim().isEmpty() && !recoveranswer.trim().isEmpty()) {
					
					try {
						Fonctions.recoverPassword(event, username, recoverquestion, recoveranswer, recoverpasswordlabel);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					System.out.println("please fill in all informations");
					Alert alert =new Alert( Alert.AlertType.ERROR);
					alert.setContentText("please fill in all informations to be able to recover your password");
					alert.show();
				}
				
			}
		});
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					Fonctions.changeScene(event,"TALKY.fxml","TALKY",800,750,null,null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}

public void setquestionsChoice (ActionEvent event) {
		
		String questionChoose = choiceboxQuestion.getValue();
		
		if(questionChoose == "favorit subject?") {
			choiceboxAnswer.getItems().addAll(subject);
		}else if(questionChoose == "favorit sport?") {
			choiceboxAnswer.getItems().addAll(sport);
		}else if (questionChoose == "favorit dish?"){
			choiceboxAnswer.getItems().addAll(dish);
		}
	}
}
