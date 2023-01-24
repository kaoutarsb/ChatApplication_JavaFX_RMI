package application;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChatController implements Initializable{
	
	ServerInterface serveur;
	ClientInterface client;
   @FXML
   private TextArea appendedmessagetextarea;
	@FXML
	private Button logoutButton;
	
	@FXML
	private Button sendButton;
	@FXML
	private TextArea messagetextarea;
	@FXML
	public  Label usernamelabel;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		try {
			serveur=(ServerInterface)Naming.lookup("rmi://127.0.0.1:1100/chatServer");
			client=(ClientInterface)Naming.lookup("rmi://127.0.0.1:1101/chatClient");
			serveur.login(usernamelabel.getText());
		    
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		
		sendButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
					if(messagetextarea.getText().trim().equals("")) {
						Alert alert =new Alert( Alert.AlertType.ERROR);
						alert.setContentText("enter a message");
						alert.show();
					}else {
				        try {
							serveur.sendMessage2(messagetextarea.getText());
							String p=usernamelabel.getText()+" : "+messagetextarea.getText();
							appendedmessagetextarea.setText(p);
							
							String s=serveur.getMessage(p);
							client.getMessage(s);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();}
						}
				}
			
		});

		logoutButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					//client.logout(usernamelabel.getText());
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
public void getInformations (String username) throws SQLException {
	       System.out.println("i'm working2");
			usernamelabel.setText(username);
}
}
	


