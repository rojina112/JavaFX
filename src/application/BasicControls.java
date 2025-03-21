package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class BasicControls extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane flowPane=new FlowPane(); 
		Scene scene=new Scene(flowPane);		
		primaryStage.setScene(scene);
		primaryStage.setHeight(250);
		primaryStage.setWidth(350);
		
		//Label Control
		Label lblID;
		lblID = new Label();
		lblID.setText("Your Name :");
				
		//TextField Control
		TextField txtID;
		txtID=new TextField();
		txtID.setPrefWidth(450);
		
		//Button Control
		Button btnOk;
		btnOk=new Button();
		btnOk.setText("OK");
		
		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("Button btnOk Clicked");				
			}
		});
		
		flowPane.getChildren().add(lblID);
		flowPane.getChildren().add(txtID);
		flowPane.getChildren().add(btnOk);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}