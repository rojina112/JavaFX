
	package application;

	import javafx.scene.Scene;
	import javafx.stage.Stage;
	import javafx.scene.Parent;

	public class PageNavigator {
	    private static Stage mainStage;

	    public static void setMainStage(Stage stage) {
	        mainStage = stage;
	    }

	    public static void loadPage(Parent page) {
	        Scene scene = new Scene(page, 400, 300);
	        mainStage.setScene(scene);
	        mainStage.show();
	    }
	}


