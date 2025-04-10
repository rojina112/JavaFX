package Assignment;

import java.util.List;

import DAO.EmployeePageDAO;
import Model.EmployeePageModel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmployeePage extends Application {
	private TextField txtEmployeeId, txtEmployeeName, txtEmail, txtPhone, txtAddress, txtStatus, txtRole;
	private EmployeePageModel currentEmployee;
	private boolean editMode = false;
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Employee Information");

		// Main container
		VBox mainContainer = new VBox(20);
		mainContainer.setAlignment(Pos.CENTER);
		mainContainer.setPadding(new Insets(20));
		mainContainer.setStyle("-fx-background-color: #f5f5f5;");

		// Title
		Label titleLabel = new Label("My Profile");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

		// Form container
		GridPane formGrid = new GridPane();
		formGrid.setAlignment(Pos.CENTER);
		formGrid.setHgap(20);
		formGrid.setVgap(15);
		formGrid.setPadding(new Insets(20));
		formGrid.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

		// Employee ID Section
		Label lblEmployeeId = new Label("Employee ID:");
		txtEmployeeId = new TextField();
		txtEmployeeId.setEditable(false);
		txtEmployeeId.setStyle("-fx-opacity: 0.7;");
		formGrid.add(lblEmployeeId, 0, 0);
		formGrid.add(txtEmployeeId, 1, 0);

		// Employee Details
		Label lblEmployeeName = new Label("Name:");
		txtEmployeeName = new TextField();
		txtEmployeeName.setEditable(false);
		formGrid.add(lblEmployeeName, 0, 1);
		formGrid.add(txtEmployeeName, 1, 1);

		Label lblEmail = new Label("Email:");
		txtEmail = new TextField();
		txtEmail.setEditable(false);
		formGrid.add(lblEmail, 0, 2);
		formGrid.add(txtEmail, 1, 2);

		Label lblPhone = new Label("Phone:");
		txtPhone = new TextField();
		txtPhone.setEditable(false);
		formGrid.add(lblPhone, 0, 3);
		formGrid.add(txtPhone, 1, 3);

		Label lblAddress = new Label("Address:");
		txtAddress = new TextField();
		txtAddress.setEditable(false);
		formGrid.add(lblAddress, 0, 4);
		formGrid.add(txtAddress, 1, 4);
		
		Label lblRole = new Label("Role:");
		txtRole = new TextField();
		txtRole.setEditable(false);
		formGrid.add(lblRole, 0, 5);
		formGrid.add(txtRole, 1, 5);
		
		Label lblStatus = new Label("Status:");
		txtStatus = new TextField();
		txtStatus.setEditable(false);
		formGrid.add(lblStatus, 0, 6);
		formGrid.add(txtStatus, 1, 6);

		// Action Buttons
		HBox buttonBox = new HBox(20);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(20, 0, 20, 0)); // Add padding top and bottom
		
		Button btnEdit = new Button("Edit Profile");
		btnEdit.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 15;");
		
		Button btnSave = new Button("Save Changes");
		btnSave.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 15;");
		btnSave.setDisable(true);
		
		Button btnClose = new Button("Close");
		btnClose.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 15;");
		btnClose.setMinWidth(100); // Set minimum width
		
		buttonBox.getChildren().addAll(btnEdit, btnSave, btnClose);
		buttonBox.setSpacing(20); // Increase spacing between buttons
		
		// Add button box directly to the main container
		mainContainer.getChildren().addAll(titleLabel, formGrid, buttonBox);
		VBox.setMargin(buttonBox, new Insets(20, 0, 0, 0)); // Add margin to separate from form

		// Load current employee's information
		loadEmployeeData();

		// Button Actions
		btnEdit.setOnAction(e -> {
			if (!editMode) {
				// Enable editing for editable fields
				txtEmployeeName.setEditable(true);
				txtPhone.setEditable(true);
				txtAddress.setEditable(true);
				
				btnEdit.setText("Cancel");
				btnSave.setDisable(false);
				
				editMode = true;
			} else {
				// Cancel editing, restore values from current employee
				if (currentEmployee != null) {
					txtEmployeeName.setText(currentEmployee.getEmployeeName());
					txtPhone.setText(currentEmployee.getPhone());
					txtAddress.setText(currentEmployee.getAddress());
				}
				
				// Disable editing
				txtEmployeeName.setEditable(false);
				txtPhone.setEditable(false);
				txtAddress.setEditable(false);
				
				btnEdit.setText("Edit Profile");
				btnSave.setDisable(true);
				
				editMode = false;
			}
		});

		btnSave.setOnAction(e -> {
			try {
				// Validate inputs
				if (txtEmployeeName.getText().trim().isEmpty() || 
					txtPhone.getText().trim().isEmpty()) {
					showAlert(AlertType.ERROR, "Validation Error", "Name and Phone fields cannot be empty");
					return;
				}
				
				if (!txtPhone.getText().trim().matches("^[0-9]{10}$")) {
					showAlert(AlertType.ERROR, "Validation Error", "Phone number must be 10 digits");
					return;
				}
				
				// Update the current employee model
				if (currentEmployee != null) {
					currentEmployee.setEmployeeName(txtEmployeeName.getText().trim());
					currentEmployee.setPhone(txtPhone.getText().trim());
					currentEmployee.setAddress(txtAddress.getText().trim());
					
					// Update in database
					EmployeePageDAO dao = new EmployeePageDAO();
					boolean success = dao.updateEmployee(currentEmployee);
					
					if (success) {
						showAlert(AlertType.INFORMATION, "Success", "Profile updated successfully");
						
						// Disable editing
						txtEmployeeName.setEditable(false);
						txtPhone.setEditable(false);
						txtAddress.setEditable(false);
						
						btnEdit.setText("Edit Profile");
						btnSave.setDisable(true);
						
						editMode = false;
					} else {
						showAlert(AlertType.ERROR, "Error", "Failed to update profile");
					}
				}
			} catch (Exception ex) {
				showAlert(AlertType.ERROR, "Error", ex.getMessage());
			}
		});

		btnClose.setOnAction(e -> primaryStage.close());

		// Scene setup
		Scene scene = new Scene(mainContainer, 500, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void loadEmployeeData() {
		try {
			// Get current user's email from login session
			String currentUserEmail = UserLogin.getCurrentUserEmail();
			String currentUserRole = UserLogin.getCurrentUserRole();
			
			if (currentUserEmail != null && !currentUserEmail.isEmpty()) {
				EmployeePageDAO dao = new EmployeePageDAO();
				List<EmployeePageModel> employees = dao.searchByEmail(currentUserEmail);
				
				if (!employees.isEmpty()) {
					currentEmployee = employees.get(0);
					
					// Update window title based on role
					if (currentUserRole != null) {
						primaryStage.setTitle(currentUserRole + " Profile");
					}
					
					// Populate fields
					txtEmployeeId.setText(String.valueOf(currentEmployee.getEmployeeId()));
					txtEmployeeName.setText(currentEmployee.getEmployeeName());
					txtEmail.setText(currentEmployee.getEmail());
					txtPhone.setText(currentEmployee.getPhone());
					txtAddress.setText(currentEmployee.getAddress());
					txtRole.setText(currentEmployee.getRole());
					txtStatus.setText(currentEmployee.getStatus());
				} else {
					showAlert(AlertType.ERROR, "Error", "Could not find your employee information.");
				}
			} else {
				showAlert(AlertType.ERROR, "Error", "No active user session found. Please log in again.");
			}
		} catch (Exception ex) {
			showAlert(AlertType.ERROR, "Error", "Failed to load employee data: " + ex.getMessage());
		}
	}

	private void showAlert(AlertType type, String title, String message) {
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
