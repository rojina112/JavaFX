package Assignment;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminDashboard extends Application {

	private StackPane contentArea;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Admin Dashboard");

		// Sidebar Menu
		GridPane sidebar = new GridPane();
		sidebar.setPadding(new Insets(15));
		sidebar.setHgap(10);
		sidebar.setVgap(15);
		sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 200px;");
		sidebar.setAlignment(Pos.TOP_CENTER);

		// Sidebar Buttons
		Button btnEnroll = new Button("Employee Enrollment");
		Button btnVerify = new Button("Document Verification");
		Button btnUpdate = new Button("Employee Info");
		Button btnLogout = new Button("Logout");

		// Button Styling
		String buttonStyle = "-fx-background-color: #34495e; -fx-text-fill: white; -fx-font-size: 14px;";
		btnEnroll.setStyle(buttonStyle);
		btnVerify.setStyle(buttonStyle);
		btnUpdate.setStyle(buttonStyle);
		btnLogout.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;");

		// Add buttons to sidebar
		sidebar.add(btnEnroll, 0, 0);
		sidebar.add(btnVerify, 0, 1);
		sidebar.add(btnUpdate, 0, 2);
		sidebar.add(btnLogout, 0, 3);

		// Main Content Area
		contentArea = new StackPane();
		contentArea.setStyle("-fx-background-color: #ecf0f1; -fx-pref-height: 400px;");
		Label defaultContent = new Label("Welcome to Employee Enrollment System");
		contentArea.getChildren().add(defaultContent);

		// Button Actions to Switch Views
		btnEnroll.setOnAction(e -> openEmployeeEnrollmentWindow());
		btnVerify.setOnAction(e -> openDocumentVerificationWindow());
		btnUpdate.setOnAction(e -> openEmployeePageWindow());
		btnLogout.setOnAction(e -> returnToLogin(primaryStage)); // Logout Action

		// Layout using BorderPane
		BorderPane layout = new BorderPane();
		layout.setLeft(sidebar);
		layout.setCenter(contentArea);

		// Scene Setup
		Scene scene = new Scene(layout, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// Function to update content dynamically
	private void setContent(String text) {
		contentArea.getChildren().clear();
		contentArea.getChildren().add(new Label(text));
	}

	// Method to open Employee Enrollment Window
	private void openEmployeeEnrollmentWindow() {
		EmployeeEnrollment employeeEnrollment = new EmployeeEnrollment();
		Stage employeeEnrollmentStage = new Stage();
		employeeEnrollment.start(employeeEnrollmentStage); // Start the existing page in the new window
	}

	// Method to open Document Verification Window
	private void openDocumentVerificationWindow() {
		DocumentVerification documentVerification = new DocumentVerification();
		Stage documentVerificationStage = new Stage();
		documentVerification.start(documentVerificationStage); // Start the existing page in the new window
	}

	// Method to open EmployeePage Window
	private void openEmployeePageWindow() {
		EmployeePage employeePage = new EmployeePage();
		Stage employeePageStage = new Stage();
		employeePage.start(employeePageStage); // Start the existing page in the new window
	}

	// Logout and return to UserLogin page
	private void returnToLogin(Stage currentStage) {
		currentStage.close(); // Close the dashboard window
		UserLogin userLogin = new UserLogin(); // Assuming UserLogin extends Application
		Stage loginStage = new Stage();
		userLogin.start(loginStage); // Open the login window
	}

	public static void main(String[] args) {
		launch(args);
	}
}
