package application;

//Ctrl+shift+O
import javafx.application.Application;
import javafx.stage.Stage;

public class MainWindow extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("MainWindow");
		primaryStage.setWidth(350);
	    primaryStage.setHeight(250);
	    primaryStage.setResizable(false);
	    primaryStage.setAlwaysOnTop(true);
	    primaryStage.show();
	}
	public static void main(String []  args) {
		//call start method
		launch(args);
		
	}
	
	
}
