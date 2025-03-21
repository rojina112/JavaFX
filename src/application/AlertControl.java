package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class AlertControl extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane flowPane = new FlowPane();
		Scene scene = new Scene(flowPane);
		primaryStage.setScene(scene);
		primaryStage.setHeight(250);
		primaryStage.setWidth(350);

		Button btnOk;
		btnOk = new Button();
		btnOk.setText("OK");

		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Alert Title");
				alert.setHeaderText(null);
				alert.setContentText("Showing an Alert in JavaFX!");
				alert.showAndWait();
				
			}
		});

		flowPane.getChildren().add(btnOk);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
