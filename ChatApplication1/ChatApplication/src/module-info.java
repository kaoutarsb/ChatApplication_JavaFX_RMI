module ChatApplication {
	exports application;
	requires javafx.controls;
	requires javafx.fxml;
	requires java.rmi;
	requires javafx.base;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml, java.rmi;
}
