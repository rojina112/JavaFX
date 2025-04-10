package application;
	
	import javafx.application.Application;
	import javafx.stage.Stage;
	import javafx.scene.Scene;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.VBox;
	import javafx.scene.control.Label;
	import javafx.geometry.Insets;
	import javafx.geometry.Pos;


	public class MainWindow extends Application {
		@Override
		public void start(Stage primaryStage) {
			try {
				// Create the main layout
				BorderPane root = new BorderPane();
				root.getStyleClass().add("border-pane");
				
				// Create header
				Label titleLabel = new Label("Employee Enrollment System");
				titleLabel.getStyleClass().add("title");
				VBox header = new VBox(titleLabel);
				header.setAlignment(Pos.CENTER);
				header.setPadding(new Insets(20));
				root.setTop(header);
				
				// Set up the scene
				Scene scene = new Scene(root, 800, 600);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				// Configure the stage
				primaryStage.setTitle("Employee Enrollment System");
				primaryStage.setScene(scene);
				primaryStage.setMinWidth(800);
				primaryStage.setMinHeight(600);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			launch(args);
		}
	}

