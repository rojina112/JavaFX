package Assignment;

import DAO.OfficeEmployeeDAO;
import Model.OfficeEmployeeModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ManagerDashboard extends Application {

	private StackPane contentArea;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Manager Dashboard");

		// Sidebar Menu
		GridPane sidebar = new GridPane();
		sidebar.setPadding(new Insets(15));
		sidebar.setVgap(15);
		sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 200px;");
		sidebar.setAlignment(Pos.TOP_CENTER);

		// Sidebar Buttons
		Button btnViewEmployees = new Button("View OfficeEmployees");
		Button btnAddUpdateEmployee = new Button("Add/Update OfficeEmployee");
		Button btnLogout = new Button("Logout");

		// Button Styling
		String buttonStyle = "-fx-background-color: #34495e; -fx-text-fill: white; -fx-font-size: 14px;";
		btnViewEmployees.setStyle(buttonStyle);
		btnAddUpdateEmployee.setStyle(buttonStyle);
		btnLogout.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;");

		// Add buttons to sidebar
		sidebar.add(btnViewEmployees, 0, 0);
		sidebar.add(btnAddUpdateEmployee, 0, 1);
		sidebar.add(btnLogout, 0, 2);

		// Main Content Area
		contentArea = new StackPane();
		contentArea.setStyle("-fx-background-color: #ecf0f1; -fx-pref-height: 400px;");
		Label defaultContent = new Label("Welcome to Manager Dashboard");
		contentArea.getChildren().add(defaultContent);

		// Button Actions
		btnViewEmployees.setOnAction(e -> showEmployeeTable());
		btnAddUpdateEmployee.setOnAction(e -> openAddUpdateEmployeeWindow());
		btnLogout.setOnAction(e -> logout(primaryStage));

		// Layout using BorderPane
		BorderPane layout = new BorderPane();
		layout.setLeft(sidebar);
		layout.setCenter(contentArea);

		// Scene Setup
		Scene scene = new Scene(layout, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	

	// Function to open Add/Update Employee window
	private void openAddUpdateEmployeeWindow() {
		Stage addUpdateStage = new Stage();
		addUpdateStage.setTitle("Add or Update OfficeEmployee");

		// Create fields for Office employee details
		TextField nameField = new TextField();
		TextField positionField = new TextField();
		TextField departmentField = new TextField();
		TextField salaryField = new TextField();

		Button saveButton = new Button("Save");

		// Set up layout with GridPane
		GridPane layout = new GridPane();
		layout.setVgap(10);
		layout.setHgap(10);
		layout.setPadding(new Insets(10));

		// Labels
		layout.add(new Label("Employee Name:"), 0, 0);
		layout.add(nameField, 1, 0);
		layout.add(new Label("Position:"), 0, 1);
		layout.add(positionField, 1, 1);
		layout.add(new Label("Department:"), 0, 2);
		layout.add(departmentField, 1, 2);
		layout.add(new Label("Salary:"), 0, 3);
		layout.add(salaryField, 1, 3);
		layout.add(saveButton, 1, 4);

		// Set save button action
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				OfficeEmployeeModel epm = new OfficeEmployeeModel();
				try {
					// epm.setEmployeeId(Integer.parseInt(employeeIdField.getText()));
					epm.setEmployeeName(nameField.getText());
					epm.setPosition(positionField.getText());
					epm.setDepartment(departmentField.getText());
					epm.setSalary(salaryField.getText());

					new OfficeEmployeeDAO().Insert(epm);

				} catch (Exception ex) {
					showAlert("Error", "Failed to save the information!");
				}
			}
		});

		Scene scene = new Scene(layout, 400, 250);
		addUpdateStage.setScene(scene);
		addUpdateStage.show();
	}

	// Function to save employee data (Add or Update)
	private void saveEmployee(TextField nameField, TextField positionField, TextField departmentField,
			TextField salaryField) {
		// Retrieve data from fields
		String name = nameField.getText();
		String position = positionField.getText();
		String department = departmentField.getText();
		String salary = salaryField.getText();

		// Create an OfficeEmployeeModel object and set data
		OfficeEmployeeModel epm = new OfficeEmployeeModel();
		epm.setEmployeeName(name);
		epm.setPosition(position);
		epm.setDepartment(department);
		epm.setSalary(salary); // Assuming salary is a double

		// Save to database
		try {
			new OfficeEmployeeDAO().Insert(epm);
			showAlert("Success", "Employee details saved successfully!");
		} catch (Exception ex) {
			showAlert("Error", "Failed to save the information!");
		}
	}

	// Function to show alert messages
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	// Function to logout and return to the login screen
	private void logout(Stage primaryStage) {
		System.out.println("Logging out...");
		primaryStage.close();

		// Show login window (You can modify this part with your actual login window
		// class)
		UserLogin loginScreen = new UserLogin();
		loginScreen.start(new Stage());
	}
	private void showEmployeeTable() {
	    TableView<OfficeEmployeeModel> table = new TableView<>();

	    TableColumn<OfficeEmployeeModel, Integer> colId = new TableColumn<>("ID");
	    colId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

	    TableColumn<OfficeEmployeeModel, String> colName = new TableColumn<>("Name");
	    colName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));

	    TableColumn<OfficeEmployeeModel, String> colPosition = new TableColumn<>("Position");
	    colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));

	    TableColumn<OfficeEmployeeModel, String> colDepartment = new TableColumn<>("Department");
	    colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));

	    TableColumn<OfficeEmployeeModel, Double> colSalary = new TableColumn<>("Salary");
	    colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

	    table.getColumns().addAll(colId, colName, colPosition, colDepartment, colSalary);

	    // Load Data from Database
	    OfficeEmployeeDAO dao = new OfficeEmployeeDAO();
	    table.getItems().setAll(dao.getAllEmployees());

	    contentArea.getChildren().setAll(table); // Display the table in the UI
	}


	// Main method
	public static void main(String[] args) {
		launch(args);
	}
}
