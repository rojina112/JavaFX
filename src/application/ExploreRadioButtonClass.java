package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExploreRadioButtonClass extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane flowPane = new FlowPane();
		Scene scene = new Scene(flowPane);
		primaryStage.setTitle("RadioButton Example");
		primaryStage.setScene(scene);
		primaryStage.setHeight(250);
		primaryStage.setWidth(350);
		
		
		RadioButton rbMale;
		RadioButton rbFemale;
		
		ToggleGroup toggleGroup = new ToggleGroup();
		rbMale= new RadioButton();
		rbFemale= new RadioButton();
		
		
		rbMale.setText("Male");
		rbFemale.setText("Female");
		
		rbMale.setToggleGroup(toggleGroup);
		rbFemale.setToggleGroup(toggleGroup);
		
		
		flowPane.getChildren().add(rbMale);
		flowPane.getChildren().add(rbFemale);
		
		primaryStage.show();
	}
public static void main(String[]args) {
	launch(args);
	
}
}
