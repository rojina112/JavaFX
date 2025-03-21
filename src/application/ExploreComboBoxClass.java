package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ExploreComboBoxClass extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane flowPane = new FlowPane();
		Scene scene = new Scene(flowPane);
		primaryStage.setTitle("RadioButton Example");
		primaryStage.setScene(scene);
		primaryStage.setHeight(250);
		primaryStage.setWidth(350);

		CheckBox chkPlaying;
		CheckBox chkReading;
		
		 
		chkPlaying= new CheckBox();
		chkReading= new CheckBox();
		
		chkPlaying.setText("Playing");
		chkReading.setText("Reading");
		
		
		flowPane.getChildren().add(chkPlaying);
		flowPane.getChildren().add(chkReading);

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}