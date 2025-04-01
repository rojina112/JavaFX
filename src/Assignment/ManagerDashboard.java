package Assignment;

import Model.Employee;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
        Button btnViewEmployees = new Button("View Employees");
        Button btnAddUpdateEmployee = new Button("Add/Update Employee");
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

    // Function to show employees in the manager's department
    private void showEmployeeTable() {
        // For demonstration, replace this with a real query for employees in the manager's department
        TableView<Employee> table = new TableView<>();
        TableColumn<Employee, String> colName = new TableColumn<>("Employee Name");
        TableColumn<Employee, String> colPosition = new TableColumn<>("Position");
        TableColumn<Employee, String> colDepartment = new TableColumn<>("Department");
        TableColumn<Employee, String> colSalary = new TableColumn<>("Salary");

        table.getColumns().addAll(colName, colPosition, colDepartment, colSalary);

        contentArea.getChildren().clear();
        contentArea.getChildren().add(table);
    }

    // Function to open Add/Update Employee window
    private void openAddUpdateEmployeeWindow() {
        Stage addUpdateStage = new Stage();
        addUpdateStage.setTitle("Add or Update Employee");

        // Create fields for employee details
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
        layout.add(new Label("Position:"), 0, 1);
        layout.add(new Label("Department:"), 0, 2);
        layout.add(new Label("Salary:"), 0, 3);

        // Input Fields
        layout.add(nameField, 1, 0);
        layout.add(positionField, 1, 1);
        layout.add(departmentField, 1, 2);
        layout.add(salaryField, 1, 3);
        layout.add(saveButton, 1, 4);

        // Button Action to save employee
        saveButton.setOnAction(e -> saveEmployee(nameField, positionField, departmentField, salaryField));

        Scene scene = new Scene(layout, 400, 250);
        addUpdateStage.setScene(scene);
        addUpdateStage.show();
    }

    // Function to save employee data (Add or Update)
    private void saveEmployee(TextField nameField, TextField positionField, TextField departmentField, TextField salaryField) {
        // Retrieve data from fields
        String name = nameField.getText();
        String position = positionField.getText();
        String department = departmentField.getText();

        // Convert salaryField to a double
        double salary = 0;
        try {
            salary = Double.parseDouble(salaryField.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid salary input!");
            return;
        }

        // Save employee logic (could be inserting or updating in the database)
        Employee employee = new Employee(name, position, department, (int) salary);
        // new EmployeeDAO().save(employee);  // Uncomment and implement database save

        showAlert("Success", "Employee information saved successfully!");
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
        // This assumes you have a Login screen that you can show here
        System.out.println("Logging out...");
        // Close the current window
        primaryStage.close();

        // Show login window (You can modify this part with your actual login window class)
        UserLogin loginScreen = new UserLogin();
        loginScreen.start(new Stage());
    }

    // Main method
    public static void main(String[] args) {
        launch(args);
    }
}
