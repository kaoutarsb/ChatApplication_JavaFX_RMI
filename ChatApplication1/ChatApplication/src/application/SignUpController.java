package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SignUpController implements Initializable{
	
	@FXML
	private TextField textfieldFname;
	@FXML
	private TextField textfieldLname;
	@FXML
	private TextField textfieldusername;
	@FXML
	private DatePicker datepickerBirth;
	@FXML
	private RadioButton radiobuttonMale, radiobuttonFemale;
	@FXML
	private ImageView avatar1imageview;
	@FXML
	private ImageView avatar2imageview;
	@FXML
	private ImageView avatar3imageview;
	@FXML
	private RadioButton imageview1RadioButton, imageview2RadioButton,imageview3RadioButton;

	@FXML
	private TextField textfieldpassword;
	@FXML
	private TextField textfieldLpasswordconfirm;
	@FXML
	private ChoiceBox<String> 	choiceboxQuestion ;
	private String[] Question = {"favorit subject?","favorit sport?", "favorit dish?"};
	@FXML
	private ChoiceBox<String> 	choiceboxAnswer ;
	private String[] subject = {"Math","Programming", "History","Painting","Languages","Physics","Chimestery"};
	private String[] sport = {"Football","Basketball", "voleyball","swimming","course","gymnastic","surffing","boxing"};
	private String[] dish = {"Couscous","Tajine", "Pizza","Pastilla","Pasta","Tacos","Salade","Berger","Pastitsio"};
	
	@FXML
	private Button submitButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceboxQuestion.getItems().addAll(Question);
		choiceboxQuestion.setOnAction(this::setquestionsChoice);
		
		/*imageview1Button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(radiobuttonMale.isSelected()) {
					String urlAvatar ="file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar1.png";
					
				}else if(radiobuttonFemale.isSelected()) {
					String urlAvatar ="file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar4.png";
				}
				
				
			}
		});*/
		
         submitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
					String firstname= textfieldFname.getText();
					String lastname= textfieldLname.getText();
					String username= textfieldusername.getText();
					String birthsday=((TextField)datepickerBirth.getEditor()).getText();
					String passwordmot=textfieldpassword.getText();
					String confirmpasswordmot=textfieldLpasswordconfirm.getText();
					String recoverquestion= choiceboxQuestion.getValue();
					String recoveranswer= choiceboxAnswer.getValue(); 
					String gender=null;
					
					
					if(radiobuttonMale.isSelected()) {
						gender=radiobuttonMale.getText();
						
						
					}else if(radiobuttonFemale.isSelected()) {
						gender=radiobuttonFemale.getText();
					}
					
					String urlimage = null;
					if(radiobuttonMale.isSelected()) {
						if(imageview1RadioButton.isSelected()) {
							urlimage="file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar1.png";
						}else if(imageview2RadioButton.isSelected()) {
							urlimage="file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar2.png";
						}else if(imageview3RadioButton.isSelected()){
							urlimage="file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar3.png";
						}else {
							urlimage="file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/account.png";
						}
						
					}else if(radiobuttonFemale.isSelected()) {
						if(imageview1RadioButton.isSelected()) {
							urlimage="file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar4.png";
						}else if(imageview2RadioButton.isSelected()) {
							urlimage="file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar5.png";
						}else if(imageview3RadioButton.isSelected()){
							urlimage="file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar6.png";
						}else {
							urlimage="file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/account.png";
						}
					}
					
					/*System.out.println("name: "+firstname);
					System.out.println("lastname: "+lastname);
					System.out.println("username: "+username);
					System.out.println("gender: "+gender);
					System.out.println("birthsdays: "+birthsday);
					System.out.println(passwordmot);
					System.out.println(confirmpasswordmot);
					System.out.println("question de recuperation: "+recoverquestion);
					System.out.println("reponce de recuperation: "+recoveranswer);*/
					
					
					if (!username.trim().isEmpty() && !passwordmot.trim().isEmpty()) {
						try {
							
							Fonctions.signUpUser(event,firstname, lastname,username,birthsday,passwordmot,confirmpasswordmot,recoverquestion,recoveranswer,gender,urlimage);
							
							
						} catch (IOException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}else {
						System.out.println("please fill in all informations");
						Alert alert =new Alert( Alert.AlertType.ERROR);
						alert.setContentText("please fill in all informations to sign up");
						alert.show();
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
	
	public void setAvatarChoice (ActionEvent event) {
		
		if(radiobuttonMale.isSelected()) {
			Image image1=new Image("file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar1.png");
			avatar1imageview.setImage(image1);
			Image image2=new Image("file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar2.png");
			avatar2imageview.setImage(image2);
			Image image3=new Image("file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar3.png");
			avatar3imageview.setImage(image3);
			
		}else if(radiobuttonFemale.isSelected()) {
			Image image3=new Image("file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar4.png");
			avatar1imageview.setImage(image3);
			Image image4=new Image("file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar5.png");
			avatar2imageview.setImage(image4);
			Image image5=new Image("file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/avatar6.png");
			avatar3imageview.setImage(image5);
		}
		
	}
	
	
}

