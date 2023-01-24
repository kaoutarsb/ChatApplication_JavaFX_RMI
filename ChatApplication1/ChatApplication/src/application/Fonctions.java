package application;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Fonctions {

	// creer la methode de changescene pour switcher entre les scenes
	
	public static void changeScene (ActionEvent event, String fxmlFile, String title, int width, int height, String username, String urlimage) throws IOException, SQLException {
		 AnchorPane root=null;
		 if(username!=null && urlimage!=null){
			 try {
			 FXMLLoader loader = new FXMLLoader(Fonctions.class.getResource(fxmlFile));
			 root= (AnchorPane) loader.load();
			 ChatController cc =loader.getController();
			  cc.getInformations(username);
			  Image image=new Image(urlimage);
			  ImageView avatarimageview = new ImageView(image);
			  avatarimageview.setX(10);
			  avatarimageview.setY(10);
			  avatarimageview.setFitHeight(60);
			  avatarimageview.setFitWidth(60);
			  System.out.println("i'm working5");
			  root.getChildren().add(avatarimageview);
			 }catch(IOException E) {
				 E.printStackTrace();
			 }
		 }else {
			 try {
				 FXMLLoader loader = new FXMLLoader(Fonctions.class.getResource(fxmlFile));
				 root= (AnchorPane) loader.load();
			 }catch(IOException E) {
				 E.printStackTrace();
			 }
		 }
		
		  Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		  Scene scene =new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle(title);
		  Image icon = new Image("file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/LOGO6.png");
		  stage.getIcons().add(icon);
		  
		  stage.setWidth(width);
		  stage.setHeight(height);
		  stage.setResizable(false);
		  stage.show();
		 
		
	}
	
	
	public static void signUpUser (ActionEvent event,String firstname, String lastname, String username, String birthsday, String passwordmot,String confirmpasswordmot, String recoverquestion, String recoveranswer, String gender, String urlimage) throws IOException, SQLException {

	Connection cn=null;
	PreparedStatement insert=null;
	PreparedStatement precheck=null;
	ResultSet result =null;
	String url = "jdbc:mysql://localhost:3306/chatapplicationtalky";
    String user = "root";
    String password = "bac2smks";
    
    
    if(confirmpasswordmot.equals(passwordmot)) {
    try {
    	cn= DriverManager.getConnection(url,user,password);
		System.out.println("Database connected");
		precheck=cn.prepareStatement("SELECT * FROM compte WHERE username = ?");
		precheck.setString (1,username);
		result = precheck.executeQuery();
		
		
		if (result.isBeforeFirst()) {
			Alert alert =new Alert( Alert.AlertType.ERROR);
			alert.setContentText("you can not use "+username+" as a username, it's already exist.");
			alert.show();
		}else {
			insert = cn.prepareStatement("INSERT INTO compte (username,firstname,lastname,birthsday,gender,passwordmot,recoverquestion,recoveranswer,urlimage) values(?,?,?,?,?,?,?,?,?)");
			insert.setString (1,username);
			insert.setString (2,firstname);
			insert.setString (3,lastname);
			insert.setString (4,birthsday);
			insert.setString (5,gender);
			insert.setString (6,passwordmot);
			insert.setString (7,recoverquestion);
			insert.setString (8,recoveranswer);
			insert.setString (9,urlimage);
			insert.executeUpdate();
			
			Alert alert =new Alert( Alert.AlertType.INFORMATION);
			alert.setContentText("accout created successfully");
			alert.show();
			changeScene(event, "TALKY.fxml", "TALKY", 700,700, null, null);
			
		}
    
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }finally {
		   
			result.close();
		   insert.close();
		   precheck.close();
		   cn.close();
	   }
    }else{
    	Alert alert =new Alert( Alert.AlertType.ERROR);
		alert.setContentText("your confirm password is incorrect, please try again");
		alert.show();
    }
	}
	
	public static void signInUser (ActionEvent event,String username, String insertpassword) throws SQLException, IOException {
		
		Connection cn=null;
		PreparedStatement check=null;
		ResultSet result =null;
		String url = "jdbc:mysql://localhost:3306/chatapplicationtalky";
	    String user = "root";
	    String password = "bac2smks";
	    
	    try {
	    	cn= DriverManager.getConnection(url,user,password);
			System.out.println("Database connected");
			check=cn.prepareStatement("SELECT passwordmot, urlimage FROM compte WHERE username = ?");
			check.setString (1,username);
			result = check.executeQuery();
			
			if (!result.isBeforeFirst()) {
				System.out.println("user not found");
				Alert alert =new Alert( Alert.AlertType.ERROR);
				alert.setContentText("username "+username+" does not exist.");
				alert.show();
			}else {
				while(result.next()) {
					String passwordmot = result.getString("passwordmot");
					String avatar= result.getString("urlimage");
					if(insertpassword.equals(passwordmot)) {
						System.out.println("i'm working");
						changeScene(event, "chat.fxml", "chat", 700,740,username, avatar);
					}else {
						System.out.println("password not match");
						Alert alert =new Alert( Alert.AlertType.ERROR);
						alert.setContentText("password incorrect.");
						alert.show();
					}
				}
			}
	    }finally {
			   
				result.close();
			   check.close();
			   cn.close();
		   }
	}
	public static void recoverPassword (ActionEvent event,String username, String recoverquestion, String recoveranswer, Label recoverpasswordlabel) throws SQLException, IOException {
		
		Connection cn=null;
		PreparedStatement check=null;
		ResultSet result =null;
		String url = "jdbc:mysql://localhost:3306/chatapplicationtalky";
	    String user = "root";
	    String password = "bac2smks";
	    
	    cn= DriverManager.getConnection(url,user,password);
		System.out.println("Database connected");
		check=cn.prepareStatement("SELECT passwordmot, recoverquestion, recoveranswer FROM compte WHERE username = ?");
		check.setString (1,username);
		result = check.executeQuery();
		
		if (!result.isBeforeFirst()) {
			System.out.println("user not found");
			Alert alert =new Alert( Alert.AlertType.ERROR);
			alert.setContentText("username "+username+" does not exist.");
			alert.show();
		}else {
			while(result.next()) {
				String question = result.getString("recoverquestion");
				String answer = result.getString("recoveranswer");
				if(recoverquestion.equals(question) && recoveranswer.equals(answer)) {
					String recoverpassword = result.getString("passwordmot");
					recoverpasswordlabel.setText(recoverpassword);
					
				}else {
					System.out.println("either the question or the answer are incorrect");
					Alert alert =new Alert( Alert.AlertType.ERROR);
					alert.setContentText("the question or ahe answer are incorrect.");
					alert.show();
				}
					
			}
	    }
		
		   result.close();
		   check.close();
		   cn.close();
	
    }
	
	
	}
