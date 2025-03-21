package application;



import javafx.scene.control.MenuBar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ExploreMenuClass extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane flowPane = new FlowPane();
		Scene scene = new Scene(flowPane);
		primaryStage.setTitle("MenuBar Example");
		primaryStage.setScene(scene);
		//primaryStage.setHeight(250);
		//primaryStage.setWidth(350);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setMaximized(true);
		
		MenuBar menuBar1 = new MenuBar();
		Menu menu1 = new Menu("File");
		MenuItem menuItem1=new MenuItem("Open");
		
		menu1.getItems().add(menuItem1);
		
		menuBar1.getMenus().add(menu1);
		
		flowPane.getChildren().add(menuBar1);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
