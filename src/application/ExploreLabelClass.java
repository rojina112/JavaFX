package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ExploreLabelClass extends Application {

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane flowPane= new FlowPane();
		Scene scene=new Scene(flowPane);
		primaryStage.setScene(scene);
		primaryStage.setHeight(250);
		primaryStage.setWidth(350);
		
		Label lblHello;
		Label lblHello2;
		
		lblHello=new Label();
		lblHello.setText("Hello World of JavaFX");
		
		lblHello2 = new Label("Hello World of JavaFX");
				
		flowPane.getChildren().add(lblHello);
		flowPane.getChildren().add(lblHello2);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

		
	}


