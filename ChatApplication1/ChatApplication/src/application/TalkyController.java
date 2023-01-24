package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class TalkyController implements Initializable{
	
	@FXML
	private Button signInButton;
	@FXML
	private Button signUpButton;
	@FXML
	private TextField textfieldusername;
	@FXML
	private TextField textfieldpassword;
	@FXML
	private Hyperlink hyperlinkpassword;
	
		 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//choiceboxQuestion.getItems().addAll(Question);
		
		signUpButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					Fonctions.changeScene(event ,"signUp.fxml", "SignUp" ,800,800,null,null );
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		signInButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					Fonctions.signInUser(event, textfieldusername.getText(), textfieldpassword.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		hyperlinkpassword.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				try {
					Fonctions.changeScene(event,"recover.fxml" ,"recover your password" ,700,750,null,null);
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

		public String getusername() {
			String username=textfieldusername.getText();
		return username;
		}
		 
	}
	


