package application;


	import javafx.application.Application;
	import javafx.geometry.Insets;
	import javafx.scene.Scene;
	import javafx.scene.control.*;
	import javafx.scene.layout.GridPane;
	import javafx.stage.Stage;

	public class UserSystem extends Application {
	    @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("User Login System");

	        GridPane grid = new GridPane();
	        grid.setPadding(new Insets(20));
	        grid.setVgap(10);
	        grid.setHgap(10);

	        Label userIdLabel = new Label("User ID:");
	        TextField userIdInput = new TextField();
	        grid.add(userIdLabel, 0, 0);
	        grid.add(userIdInput, 1, 0);

	        Label nameLabel = new Label("Name:");
	        TextField nameInput = new TextField();
	        grid.add(nameLabel, 0, 1);
	        grid.add(nameInput, 1, 1);

	        Label emailLabel = new Label("Email:");
	        TextField emailInput = new TextField();
	        grid.add(emailLabel, 0, 2);
	        grid.add(emailInput, 1, 2);

	        Label phoneLabel = new Label("Phone:");
	        TextField phoneInput = new TextField();
	        grid.add(phoneLabel, 0, 3);
	        grid.add(phoneInput, 1, 3);

	        Button loginButton = new Button("Login");
	        Button logoutButton = new Button("Logout");
	        Button resetPasswordButton = new Button("Reset Password");
	        
	        grid.add(loginButton, 1, 4);
	        grid.add(logoutButton, 1, 5);
	        grid.add(resetPasswordButton, 1, 6);

	        Scene scene = new Scene(grid, 400, 300);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	}


