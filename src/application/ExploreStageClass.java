package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ExploreStageClass extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("");
		//primaryStage.setWidth(250);
		//primaryStage.setHeight(350);
		//primaryStage.setMaximized(true);
		//primaryStage.setResizable(false);
		//primaryStage.setFullScreen(true);
		//primaryStage.setAlwaysOnTop(true);
		
		StackPane sPane = new StackPane();
		Scene scene=new Scene(sPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}