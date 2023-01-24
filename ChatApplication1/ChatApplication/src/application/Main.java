package application;
	
import java.rmi.AccessException;

import java.rmi.RemoteException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("TALKY.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 Image icon = new Image("file:///C:/Users/dell/eclipse-workspace/ChatApplication/src/Icons/LOGO6.png");
	            primaryStage.getIcons().add(icon);
	            primaryStage.setTitle("TALKY");
				primaryStage.setScene(scene);
				primaryStage.setWidth(800);
				primaryStage.setHeight(750);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws AccessException, RemoteException {
		launch(args);
	}
}
