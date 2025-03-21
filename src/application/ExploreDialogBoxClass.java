package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExploreDialogBoxClass extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane flowPane = new FlowPane();
		Scene scene = new Scene(flowPane);
		primaryStage.setTitle("Dialog Example");
		primaryStage.setScene(scene);
		primaryStage.setHeight(250);
		primaryStage.setWidth(350);

		Button btnOk;
		btnOk = new Button();
		btnOk.setText("OK");

		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {

				Dialog<String> dialog = new Dialog<String>();
				dialog.setTitle("Dialog");
				ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
				dialog.setContentText("This is a sample dialog");
				dialog.getDialogPane().getButtonTypes().add(type);
				Text txt = new Text("Click the button to show the dialog");
				Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
				txt.setFont(font);
				
				dialog.showAndWait();

			}
		});

		flowPane.getChildren().add(btnOk);

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}