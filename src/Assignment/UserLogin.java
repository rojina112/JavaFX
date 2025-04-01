package Assignment;

import DAO.UserDAO;
import Model.UserModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserLogin extends Application {
	private String currentUserType = ""; // Store the logged-in user type

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
		Button btnBack = new Button("Back");

		btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				String userType = cmbPosition.getValue();

				if (username.isEmpty() || password.isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Input Error");
					alert.setHeaderText(null);
					alert.setContentText("Please fill in all fields.");
					alert.showAndWait();
					return;
				}

				// Call the CRUD search operation
				UserDAO userDAO = new UserDAO();
				UserModel user = userDAO.login(username, password, userType);

				if (user != null) {
					// Store the logged-in user type
					currentUserType = userType;

					// Successful login
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Login Successful");
					alert.setHeaderText(null);
					alert.setContentText("Welcome " + user.getUserName() + "!");
					alert.showAndWait();

					try {
						Stage newStage = new Stage(); // Create a new stage for the dashboard
						primaryStage.close(); // Close the login window

						if ("Manager".equalsIgnoreCase(userType)) {
							new ManagerDashboard().start(newStage);
						} else if ("Employee".equalsIgnoreCase(userType)) {
							new EmployeePage().start(newStage);
						} else if ("Admin".equalsIgnoreCase(userType)) {
							new AdminDashboard().start(newStage);
						} else {
							Alert unknownUserAlert = new Alert(AlertType.WARNING);
							unknownUserAlert.setTitle("Unknown Role");
							unknownUserAlert.setHeaderText(null);
							unknownUserAlert.setContentText("User role not recognized.");
							unknownUserAlert.showAndWait();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// Clear fields
					txtUsername.clear();
					txtPassword.clear();
				} else {
					// Failed login
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Login Failed");
					alert.setHeaderText(null);
					alert.setContentText("Invalid username or password.");
					alert.showAndWait();
				}
			}
		});

		// Handle the Back button click event
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if ("Manager".equalsIgnoreCase(currentUserType)) {
					try {
						Stage newStage = new Stage();
						primaryStage.close();
						new ManagerDashboard().start(newStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Back");
					alert.setHeaderText(null);
					alert.setContentText("No previous page available.");
					alert.showAndWait();
				}
			}
		});

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
		gridPane.add(btnBack, 1, 3); // Corrected reference

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
