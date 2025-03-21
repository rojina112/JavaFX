package Assignment;


	import javafx.application.Application;
	import javafx.geometry.Insets;
	import javafx.scene.Scene;
	import javafx.scene.control.*;
	import javafx.scene.layout.*;
	import javafx.stage.Stage;

	public class UserLogin extends Application {
	    @Override
	    public void start(Stage primaryStage) {
	        // Labels
	        Label lblUsername = new Label("Username :");
	        Label lblPassword = new Label("Enter Password :");
	        Label lblPosition = new Label("Position :");
	        
	        // Input Fields
	        TextField txtUsername = new TextField();
	        PasswordField txtPassword = new PasswordField();
	        ComboBox<String> cmbPosition = new ComboBox<>();
	        cmbPosition.getItems().addAll("Admin", "Employee", "Manager");

	        // Buttons
	        Button btnLogin = new Button("Login");
	        Button btnReset = new Button("Reset Password");

	        // Layout settings
	        GridPane gridPane = new GridPane();
	        gridPane.setPadding(new Insets(20));
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        
	        gridPane.add(lblUsername, 0, 0);
	        gridPane.add(txtUsername, 1, 0);
	        gridPane.add(lblPassword, 0, 1);
	        gridPane.add(txtPassword, 1, 1);
	        gridPane.add(lblPosition, 0, 2);
	        gridPane.add(cmbPosition, 1, 2);
	        gridPane.add(btnLogin, 0, 3);
	        gridPane.add(btnReset, 1, 3);

	        
	        // Scene and Stage setup
	        Scene scene = new Scene(gridPane, 350, 200);
	        primaryStage.setTitle("Login Page");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	}


