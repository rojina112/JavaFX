package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class ExploreApplicationClass extends Application {

	private static final Stage primaryStage = null;
	@Override
	public void init() {
		System.out.println("Hello form init() method");
		
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		System.out.println("Hello form start() method");
		primaryStage.show();
		
	}
  @Override
public void stop() {
	  System.out.println("Hello form stop() method");
	  
  }
  public static void main (String []  args) {
	  launch(args);
	  
  }
}
