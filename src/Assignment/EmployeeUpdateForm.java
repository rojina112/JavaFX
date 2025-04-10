package Assignment;

import DAO.EmployeePageDAO;
import Model.EmployeePageModel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class EmployeeUpdateForm extends Application {
    private TextField idField;
    private TextField nameField;
    private TextField addressField;
    private TextField emailField;
    private TextField phoneField;
    private ComboBox<String> roleComboBox;
    private ComboBox<String> statusComboBox;
    private EmployeePageModel currentEmployee;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Update Employee");

        // Main container
        BorderPane mainContainer = new BorderPane();
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");

        // Title
        Label titleLabel = new Label("Update Employee Information");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        VBox titleBox = new VBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20));
        mainContainer.setTop(titleBox);

        // Search section
        GridPane searchPane = new GridPane();
        searchPane.setHgap(10);
        searchPane.setVgap(10);
        searchPane.setPadding(new Insets(20));
        searchPane.setAlignment(Pos.CENTER);

        Label searchLabel = new Label("Search Employee:");
        TextField searchField = new TextField();
        searchField.setPromptText("Enter ID or Email");
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        searchPane.add(searchLabel, 0, 0);
        searchPane.add(searchField, 1, 0);
        searchPane.add(searchButton, 2, 0);

        // Form container
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(20);
        formGrid.setVgap(15);
        formGrid.setPadding(new Insets(20));
        formGrid.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

        // Form fields
        idField = new TextField();
        idField.setEditable(false);
        nameField = new TextField();
        addressField = new TextField();
        emailField = new TextField();
        phoneField = new TextField();
        roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Admin", "Manager", "Employee");
        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Pending", "Verified", "Rejected");

        // Add form fields to grid
        formGrid.add(new Label("Employee ID:"), 0, 0);
        formGrid.add(idField, 1, 0);
        formGrid.add(new Label("Name:"), 0, 1);
        formGrid.add(nameField, 1, 1);
        formGrid.add(new Label("Address:"), 0, 2);
        formGrid.add(addressField, 1, 2);
        formGrid.add(new Label("Email:"), 0, 3);
        formGrid.add(emailField, 1, 3);
        formGrid.add(new Label("Phone:"), 0, 4);
        formGrid.add(phoneField, 1, 4);
        formGrid.add(new Label("Role:"), 0, 5);
        formGrid.add(roleComboBox, 1, 5);
        formGrid.add(new Label("Status:"), 0, 6);
        formGrid.add(statusComboBox, 1, 6);

        // Buttons
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 0, 20, 0));

        Button updateButton = new Button("Update");
        updateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 8 15;");
        Button clearButton = new Button("Clear");
        clearButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-padding: 8 15;");
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 8 15;");
        closeButton.setMinWidth(100);

        buttonBox.getChildren().addAll(updateButton, clearButton, closeButton);
        buttonBox.setSpacing(20);

        // Wrap formGrid in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(formGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: white; -fx-background-color: white;");

        // Add all elements to main container
        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().addAll(searchPane, scrollPane, buttonBox);
        VBox.setMargin(buttonBox, new Insets(20, 0, 0, 0));
        mainContainer.setCenter(contentBox);

        // Event handlers
        searchButton.setOnAction(e -> {
            String searchText = searchField.getText().trim();
            if (searchText.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please enter an ID or Email");
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
                    clearFields();
                } else {
                    currentEmployee = employees.get(0);
                    idField.setText(String.valueOf(currentEmployee.getEmployeeId()));
                    nameField.setText(currentEmployee.getEmployeeName());
                    addressField.setText(currentEmployee.getAddress());
                    emailField.setText(currentEmployee.getEmail());
                    phoneField.setText(currentEmployee.getPhone());
                    roleComboBox.setValue(currentEmployee.getRole());
                    statusComboBox.setValue(currentEmployee.getStatus());
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            }
        });

        updateButton.setOnAction(e -> {
            if (currentEmployee == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please search for an employee first");
                return;
            }

            try {
                currentEmployee.setEmployeeName(nameField.getText());
                currentEmployee.setAddress(addressField.getText());
                currentEmployee.setEmail(emailField.getText());
                currentEmployee.setPhone(phoneField.getText());
                currentEmployee.setRole(roleComboBox.getValue());
                currentEmployee.setStatus(statusComboBox.getValue());

                EmployeePageDAO dao = new EmployeePageDAO();
                boolean success = dao.updateEmployee(currentEmployee);

                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Employee updated successfully");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update employee");
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            }
        });

        clearButton.setOnAction(e -> clearFields());

        // Add event handler for close button
        closeButton.setOnAction(e -> primaryStage.close());

        // Set up scene
        Scene scene = new Scene(mainContainer, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        addressField.clear();
        emailField.clear();
        phoneField.clear();
        roleComboBox.setValue(null);
        statusComboBox.setValue(null);
        currentEmployee = null;
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