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

public class ManagerDashboard extends Application {
	private Stage primaryStage;  // Add primaryStage as a class field

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;  // Store the stage reference
		primaryStage.setTitle("Manager Dashboard");

		// Main container
		BorderPane mainContainer = new BorderPane();
		mainContainer.setStyle("-fx-background-color: #f5f5f5;");

		// Title
		Label titleLabel = new Label("Manager Dashboard");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
		VBox titleBox = new VBox(titleLabel);
		titleBox.setAlignment(Pos.CENTER);
		titleBox.setPadding(new Insets(20));
		mainContainer.setTop(titleBox);

		// Sidebar
		VBox sidebar = new VBox(10);
		sidebar.setPadding(new Insets(20));
		sidebar.setStyle("-fx-background-color: #2c3e50; -fx-min-width: 200px;");

		Label sidebarTitle = new Label("Manager Menu");
		sidebarTitle.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
		sidebar.getChildren().add(sidebarTitle);

		Button btnEmployeeEnrollment = createSidebarButton("Employee Enrollment");
		Button btnUpdateEmployee = createSidebarButton("Update Employee");
		Button btnLogout = createSidebarButton("Logout");

		sidebar.getChildren().addAll(btnEmployeeEnrollment, btnUpdateEmployee, btnLogout);
		mainContainer.setLeft(sidebar);

		// Content area
		VBox contentArea = new VBox(20);
		contentArea.setPadding(new Insets(20));
		contentArea.setAlignment(Pos.CENTER);

		// Welcome message
		Label welcomeLabel = new Label("Welcome to the Manager Dashboard");
		welcomeLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2c3e50;");
		
		Label infoLabel = new Label("As a manager, you can: \n" +
		                           "• Enroll new employees\n" +
		                           "• Update employee details\n");
		infoLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50;");
		
		contentArea.getChildren().addAll(welcomeLabel, infoLabel);
		
		// Initial view
		mainContainer.setCenter(contentArea);

		// Employee search section
		GridPane searchGrid = new GridPane();
		searchGrid.setHgap(10);
		searchGrid.setVgap(10);
		searchGrid.setAlignment(Pos.CENTER);
		searchGrid.setPadding(new Insets(20));

		TextField txtSearch = new TextField();
		txtSearch.setPromptText("Enter Employee ID or Email");
		Button btnSearch = new Button("Search");
		btnSearch.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

		searchGrid.add(new Label("Search Employee:"), 0, 0);
		searchGrid.add(txtSearch, 1, 0);
		searchGrid.add(btnSearch, 2, 0);

		// Employee details section
		GridPane detailsGrid = new GridPane();
		detailsGrid.setHgap(20);
		detailsGrid.setVgap(15);
		detailsGrid.setPadding(new Insets(20));
		detailsGrid.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

		Label lblEmployeeId = new Label("Employee ID:");
		TextField txtEmployeeId = new TextField();
		txtEmployeeId.setEditable(false);

		Label lblEmployeeName = new Label("Name:");
		TextField txtEmployeeName = new TextField();

		Label lblEmployeeEmail = new Label("Email:");
		TextField txtEmployeeEmail = new TextField();

		Label lblEmployeePhone = new Label("Phone:");
		TextField txtEmployeePhone = new TextField();

		Label lblEmployeeAddress = new Label("Address:");
		TextField txtEmployeeAddress = new TextField();

		Label lblEmployeeStatus = new Label("Status:");
		ComboBox<String> cmbEmployeeStatus = new ComboBox<>();
		cmbEmployeeStatus.getItems().addAll("Pending", "Verified", "Rejected");

		Label lblEmployeeRole = new Label("Role:");
		ComboBox<String> cmbEmployeeRole = new ComboBox<>();
		cmbEmployeeRole.getItems().addAll("Employee", "Manager");

		detailsGrid.add(lblEmployeeId, 0, 0);
		detailsGrid.add(txtEmployeeId, 1, 0);
		detailsGrid.add(lblEmployeeName, 0, 1);
		detailsGrid.add(txtEmployeeName, 1, 1);
		detailsGrid.add(lblEmployeeEmail, 0, 2);
		detailsGrid.add(txtEmployeeEmail, 1, 2);
		detailsGrid.add(lblEmployeePhone, 0, 3);
		detailsGrid.add(txtEmployeePhone, 1, 3);
		detailsGrid.add(lblEmployeeAddress, 0, 4);
		detailsGrid.add(txtEmployeeAddress, 1, 4);
		detailsGrid.add(lblEmployeeStatus, 0, 5);
		detailsGrid.add(cmbEmployeeStatus, 1, 5);
		detailsGrid.add(lblEmployeeRole, 0, 6);
		detailsGrid.add(cmbEmployeeRole, 1, 6);

		// Update button
		Button btnUpdate = new Button("Update Employee");
		btnUpdate.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");
		detailsGrid.add(btnUpdate, 0, 7, 2, 1);

		// Button Actions
		btnEmployeeEnrollment.setOnAction(e -> {
			try {
				EmployeeEnrollment enrollment = new EmployeeEnrollment();
				enrollment.start(new Stage());
			} catch (Exception ex) {
				showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
			}
		});
		
		btnUpdateEmployee.setOnAction(e -> {
			VBox updateView = new VBox(20);
			updateView.setPadding(new Insets(20));
			updateView.setAlignment(Pos.CENTER);
			
			Label updateTitle = new Label("Update Employee");
			updateTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
			
			updateView.getChildren().addAll(updateTitle, searchGrid, detailsGrid);
			mainContainer.setCenter(updateView);
			
			// Clear previous inputs
			txtSearch.clear();
			clearEmployeeFields(txtEmployeeId, txtEmployeeName, txtEmployeeEmail, txtEmployeePhone, txtEmployeeAddress, cmbEmployeeStatus, cmbEmployeeRole);
		});

		btnSearch.setOnAction(e -> {
			String searchText = txtSearch.getText().trim();
			if (searchText.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please enter an Employee ID or Email");
				return;
			}

			try {
				EmployeePageDAO dao = new EmployeePageDAO();
				List<EmployeePageModel> employees;
				
				if (searchText.matches("\\d+")) {
					employees = dao.searchById(Integer.parseInt(searchText));
				} else {
					employees = dao.searchByEmail(searchText);
				}

				if (employees.isEmpty()) {
					showAlert(Alert.AlertType.ERROR, "Error", "Employee not found");
					clearEmployeeFields(txtEmployeeId, txtEmployeeName, txtEmployeeEmail, txtEmployeePhone, txtEmployeeAddress, cmbEmployeeStatus, cmbEmployeeRole);
				} else {
					EmployeePageModel employee = employees.get(0);
					txtEmployeeId.setText(String.valueOf(employee.getEmployeeId()));
					txtEmployeeName.setText(employee.getEmployeeName());
					txtEmployeeEmail.setText(employee.getEmail());
					txtEmployeePhone.setText(employee.getPhone());
					txtEmployeeAddress.setText(employee.getAddress());
					cmbEmployeeStatus.setValue(employee.getStatus());
					cmbEmployeeRole.setValue(employee.getRole());
				}
				} catch (Exception ex) {
				showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
			}
		});

		btnUpdate.setOnAction(e -> {
			if (txtEmployeeId.getText().isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please search for an employee first");
				return;
			}

			try {
				EmployeePageModel epm = new EmployeePageModel();
				epm.setEmployeeId(Integer.parseInt(txtEmployeeId.getText()));
				epm.setEmployeeName(txtEmployeeName.getText());
				epm.setEmail(txtEmployeeEmail.getText());
				epm.setPhone(txtEmployeePhone.getText());
				epm.setAddress(txtEmployeeAddress.getText());
				epm.setStatus(cmbEmployeeStatus.getValue());
				epm.setRole(cmbEmployeeRole.getValue());

				EmployeePageDAO dao = new EmployeePageDAO();
				boolean success = dao.updateEmployee(epm);
				
				if (success) {
					showAlert(Alert.AlertType.INFORMATION, "Success", "Employee details updated successfully");
				} else {
					showAlert(Alert.AlertType.ERROR, "Error", "Failed to update employee details");
				}
			} catch (Exception ex) {
				showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
			}
		});

		btnLogout.setOnAction(e -> {
			handleLogoutButton();
		});

		// Scene setup
		Scene scene = new Scene(mainContainer, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Button createSidebarButton(String text) {
		Button button = new Button(text);
		button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-alignment: CENTER-LEFT; -fx-font-size: 14px;");
		button.setPrefWidth(180);
		button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-alignment: CENTER-LEFT; -fx-font-size: 14px;"));
		button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-alignment: CENTER-LEFT; -fx-font-size: 14px;"));
		return button;
	}

	private void clearEmployeeFields(TextField id, TextField name, TextField email, TextField phone, TextField address, ComboBox<String> status, ComboBox<String> role) {
		id.clear();
		name.clear();
		email.clear();
		phone.clear();
		address.clear();
		status.setValue(null);
		role.setValue(null);
	}

	private void showAlert(Alert.AlertType type, String title, String message) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void handleLogoutButton() {
		// Reset current user session
		UserLogin.resetCurrentUser();
		
		// Close current stage
		primaryStage.close();

		try {
			// Create and show login stage
			UserLogin login = new UserLogin();
			Stage loginStage = new Stage();
			login.start(loginStage);
		} catch (Exception e) {
			showAlert(Alert.AlertType.ERROR, "Error", "Could not open login page: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
