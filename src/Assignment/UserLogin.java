package Assignment;

import DAO.EmployeePageDAO;
import Model.EmployeePageModel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class UserLogin extends Application {
	private static String currentUserEmail;
	private static String currentUserRole;
	private Stage primaryStage;

	public static String getCurrentUserEmail() {
		return currentUserEmail;
	}
	
	public static String getCurrentUserRole() {
		return currentUserRole;
	}

	public static void resetCurrentUser() {
		currentUserEmail = null;
		currentUserRole = null;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Employee Enrollment System - Login");

		// Main container
		VBox mainContainer = new VBox(20);
		mainContainer.setAlignment(Pos.CENTER);
		mainContainer.setPadding(new Insets(20));
		mainContainer.setStyle("-fx-background-color: #f5f5f5;");

		// Title
		Label titleLabel = new Label("Employee Enrollment System");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

		// Login form container
		GridPane formGrid = new GridPane();
		formGrid.setAlignment(Pos.CENTER);
		formGrid.setHgap(20);
		formGrid.setVgap(15);
		formGrid.setPadding(new Insets(20));
		formGrid.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

		// Login fields
		Label lblUsername = new Label("Email:");
		TextField txtUsername = new TextField();
		txtUsername.setPromptText("Enter email address");

		Label lblPassword = new Label("Password:");
		PasswordField txtPassword = new PasswordField();
		txtPassword.setPromptText("Enter password");

		Label lblRole = new Label("Role:");
		ComboBox<String> cmbRole = new ComboBox<>();
		cmbRole.getItems().addAll("Admin", "Manager", "Employee");
		cmbRole.setPromptText("Select role");

		// Add fields to grid
		formGrid.add(lblUsername, 0, 0);
		formGrid.add(txtUsername, 1, 0);
		formGrid.add(lblPassword, 0, 1);
		formGrid.add(txtPassword, 1, 1);
		formGrid.add(lblRole, 0, 2);
		formGrid.add(cmbRole, 1, 2);

		// Action Buttons
		HBox buttonBox = new HBox(20);
		buttonBox.setAlignment(Pos.CENTER);
		
		Button btnLogin = new Button("Login");
		btnLogin.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");
		
		Button btnClear = new Button("Clear");
		btnClear.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");
		
		buttonBox.getChildren().addAll(btnLogin, btnClear);
		formGrid.add(buttonBox, 0, 3, 2, 1);

		// Button Actions
		btnLogin.setOnAction(e -> {
			if (validateFields(txtUsername, txtPassword, cmbRole)) {
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				String role = cmbRole.getValue();
				
				processLogin(username, password, role);
			}
		});

		btnClear.setOnAction(e -> {
			txtUsername.clear();
			txtPassword.clear();
			cmbRole.setValue(null);
		});

		// Add all elements to main container
		mainContainer.getChildren().addAll(titleLabel, formGrid);

		// Scene setup
		Scene scene = new Scene(mainContainer, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private boolean validateFields(TextField username, PasswordField password, ComboBox<String> role) {
		if (username.getText().isEmpty() || password.getText().isEmpty() || role.getValue() == null) {
			showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in all fields");
			return false;
		}
		return true;
	}

	private void processLogin(String username, String password, String role) {
		try {
			if (role.equals("Admin")) {
				// Admin login logic
				if ((username.equals("admin") && password.equals("admin123")) || 
					(username.equals("rojina") && password.equals("roro"))) {
					currentUserEmail = username;
					showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome Admin!");
					AdminDashboard adminDashboard = new AdminDashboard();
					adminDashboard.start(new Stage());
					primaryStage.close();
				} else {
					// Try to find admin in employee table
					EmployeePageDAO dao = new EmployeePageDAO();
					List<EmployeePageModel> employees = dao.searchByEmail(username);
					
					if (!employees.isEmpty()) {
						EmployeePageModel employee = employees.get(0);
						if (employee.getPassword().equals(password) && 
							employee.getRole().equals("Admin") && 
							employee.getStatus().equals("Verified")) {
							
							currentUserEmail = username;
							currentUserRole = "Admin";
							showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome Admin!");
							
							AdminDashboard adminDashboard = new AdminDashboard();
							adminDashboard.start(new Stage());
							primaryStage.close();
							return;
						}
					}
					
					showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid admin credentials");
				}
			} else if (role.equals("Manager")) {
				// Manager login logic
				EmployeePageDAO dao = new EmployeePageDAO();
				List<EmployeePageModel> employees = dao.searchByEmail(username);
				
				if (!employees.isEmpty()) {
					EmployeePageModel employee = employees.get(0);
					if (employee.getPassword().equals(password) && 
						employee.getRole().equals("Manager") && 
						employee.getStatus().equals("Verified")) {
						currentUserEmail = username;
						currentUserRole = "Manager";
						showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome Manager!");
						// Open manager dashboard
						ManagerDashboard managerDashboard = new ManagerDashboard();
						managerDashboard.start(new Stage());
						primaryStage.close();
					} else {
						if (!employee.getRole().equals("Manager")) {
							showAlert(Alert.AlertType.ERROR, "Login Failed", "You do not have Manager access");
						} else if (!employee.getStatus().equals("Verified")) {
							showAlert(Alert.AlertType.ERROR, "Login Failed", "Your account is not verified yet");
						} else {
							showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid credentials");
						}
					}
				} else {
					showAlert(Alert.AlertType.ERROR, "Login Failed", "Manager not found");
				}
			} else {
				// Employee login logic
				EmployeePageDAO dao = new EmployeePageDAO();
				List<EmployeePageModel> employees = dao.searchByEmail(username);
				
				if (!employees.isEmpty()) {
					EmployeePageModel employee = employees.get(0);
					if (employee.getPassword().equals(password) && employee.getStatus().equals("Verified")) {
						currentUserEmail = username;
						currentUserRole = employee.getRole();
						showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome " + currentUserRole + "!");
						
						// Open employee page for all types of employees
						try {
							EmployeePage employeePage = new EmployeePage();
						Stage newStage = new Stage();
							employeePage.start(newStage);
						primaryStage.close();
						} catch (Exception ex) {
							showAlert(Alert.AlertType.ERROR, "Error", "Could not open Employee Page: " + ex.getMessage());
							ex.printStackTrace();
						}
					} else {
						if (!employee.getStatus().equals("Verified")) {
							showAlert(Alert.AlertType.ERROR, "Login Failed", "Your account is not verified yet");
						} else {
							showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid credentials");
						}
					}
				} else {
					showAlert(Alert.AlertType.ERROR, "Login Failed", "Employee not found");
				}
			}
		} catch (Exception ex) {
			showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
		}
	}

	private void showAlert(Alert.AlertType type, String title, String message) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
