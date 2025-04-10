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

public class EmployeeEnrollment extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Enrollment");

        // Main container
        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");

        // Title
        Label titleLabel = new Label("Employee Enrollment");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Form container
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(20);
        formGrid.setVgap(15);
        formGrid.setPadding(new Insets(20));
        formGrid.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

        // Wrap formGrid in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(formGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: white; -fx-background-color: white;");

        // Auto-assigned Employee ID field (non-editable)
        Label lblEmployeeId = new Label("Employee ID:");
        TextField txtEmployeeId = new TextField();
        txtEmployeeId.setPromptText("Auto-assigned");
        txtEmployeeId.setEditable(false);
        txtEmployeeId.setDisable(true);
        txtEmployeeId.setStyle("-fx-opacity: 0.7;");

        // Personal Information Section
        Label lblPersonalInfo = new Label("Personal Information");
        lblPersonalInfo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Input Fields
        Label lblFirstName = new Label("First Name:");
        TextField txtFirstName = new TextField();
        txtFirstName.setPromptText("Enter first name");

        Label lblLastName = new Label("Last Name:");
        TextField txtLastName = new TextField();
        txtLastName.setPromptText("Enter last name");

        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Enter email address");

        Label lblPhone = new Label("Phone:");
        TextField txtPhone = new TextField();
        txtPhone.setPromptText("Enter phone number (10 digits)");

        Label lblAddress = new Label("Address:");
        TextField txtAddress = new TextField();
        txtAddress.setPromptText("Enter address");

        Label lblPassword = new Label("Password:");
        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Enter password");

        Label lblConfirmPassword = new Label("Confirm Password:");
        PasswordField txtConfirmPassword = new PasswordField();
        txtConfirmPassword.setPromptText("Confirm password");

        // Role Selection
        Label lblRole = new Label("Role:");
        ComboBox<String> cmbRole = new ComboBox<>();
        cmbRole.getItems().addAll("Admin", "Manager", "Employee");
        cmbRole.setValue("Employee"); // Default role
        cmbRole.setPromptText("Select role");

        // Employment Information Section
        Label lblEmploymentInfo = new Label("Employment Information");
        lblEmploymentInfo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label lblPosition = new Label("Position:");
        TextField txtPosition = new TextField();
        txtPosition.setPromptText("Enter position");

        Label lblDepartment = new Label("Department:");
        ComboBox<String> cmbDepartment = new ComboBox<>();
        cmbDepartment.getItems().addAll("IT", "HR", "Finance", "Marketing", "Operations");
        cmbDepartment.setPromptText("Select department");

        // Add fields to grid - start with Employee ID at the top
        formGrid.add(lblEmployeeId, 0, 0);
        formGrid.add(txtEmployeeId, 1, 0);
        
        formGrid.add(lblPersonalInfo, 0, 1, 2, 1);
        formGrid.add(lblFirstName, 0, 2);
        formGrid.add(txtFirstName, 1, 2);
        formGrid.add(lblLastName, 0, 3);
        formGrid.add(txtLastName, 1, 3);
        formGrid.add(lblEmail, 0, 4);
        formGrid.add(txtEmail, 1, 4);
        formGrid.add(lblPhone, 0, 5);
        formGrid.add(txtPhone, 1, 5);
        formGrid.add(lblAddress, 0, 6);
        formGrid.add(txtAddress, 1, 6);
        formGrid.add(lblPassword, 0, 7);
        formGrid.add(txtPassword, 1, 7);
        formGrid.add(lblConfirmPassword, 0, 8);
        formGrid.add(txtConfirmPassword, 1, 8);
        formGrid.add(lblRole, 0, 9);
        formGrid.add(cmbRole, 1, 9);
        
        formGrid.add(lblEmploymentInfo, 0, 10, 2, 1);
        formGrid.add(lblPosition, 0, 11);
        formGrid.add(txtPosition, 1, 11);
        formGrid.add(lblDepartment, 0, 12);
        formGrid.add(cmbDepartment, 1, 12);

        // Action Buttons
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        
        Button btnEnroll = new Button("Enroll");
        btnEnroll.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");
        
        Button btnClear = new Button("Clear");
        btnClear.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");
        
        Button btnClose = new Button("Close");
        btnClose.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");
        
        buttonBox.getChildren().addAll(btnEnroll, btnClear, btnClose);
        formGrid.add(buttonBox, 0, 13, 2, 1);

        // Check if current user has admin or manager role
        String currentUserRole = UserLogin.getCurrentUserRole();
        if (currentUserRole == null || (!currentUserRole.equals("Admin") && !currentUserRole.equals("Manager"))) {
            // Disable role selection for non-admin/manager users
            cmbRole.setDisable(true);
        }

        // Button Actions
        btnEnroll.setOnAction(e -> {
            if (validateFields(txtFirstName, txtLastName, txtEmail, txtPhone, txtPosition, cmbDepartment, 
                             txtPassword, txtConfirmPassword, cmbRole)) {
                try {
                    EmployeePageModel epm = new EmployeePageModel();
                    epm.setEmployeeName(txtFirstName.getText() + " " + txtLastName.getText());
                    epm.setEmail(txtEmail.getText());
                    epm.setPhone(txtPhone.getText());
                    epm.setAddress(txtAddress.getText());
                    epm.setPassword(txtPassword.getText());
                    epm.setRole(cmbRole.getValue());
                    epm.setStatus("Pending");

                    EmployeePageDAO dao = new EmployeePageDAO();
                    boolean success = dao.Insert(epm);
                    
                    if (success) {
                        // Display the assigned ID in the ID field
                        txtEmployeeId.setText(String.valueOf(epm.getEmployeeId()));
                        
                        showAlert(Alert.AlertType.INFORMATION, "Enrollment Successful", 
                            "Employee " + txtFirstName.getText() + " " + txtLastName.getText() + 
                            " has been enrolled successfully! Employee ID: " + epm.getEmployeeId());
                        clearFields(txtEmployeeId, txtFirstName, txtLastName, txtEmail, txtPhone, txtAddress, 
                                 txtPosition, cmbDepartment, txtPassword, txtConfirmPassword, cmbRole);
            } else {
                        showAlert(Alert.AlertType.ERROR, "Enrollment Failed", 
                            "Failed to enroll employee. Please try again.");
                    }
                } catch (Exception ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
                }
            }
        });

        btnClear.setOnAction(e -> clearFields(txtEmployeeId, txtFirstName, txtLastName, txtEmail, txtPhone, txtAddress, 
                                         txtPosition, cmbDepartment, txtPassword, txtConfirmPassword, cmbRole));

        btnClose.setOnAction(e -> primaryStage.close());

        // Add all elements to main container
        mainContainer.getChildren().addAll(titleLabel, scrollPane);

        // Scene setup
        Scene scene = new Scene(mainContainer, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validateFields(TextField firstName, TextField lastName, TextField email, TextField phone,
                                 TextField position, ComboBox<String> department, PasswordField password,
                                 PasswordField confirmPassword, ComboBox<String> role) {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty() ||
            phone.getText().isEmpty() || position.getText().isEmpty() || department.getValue() == null ||
            password.getText().isEmpty() || confirmPassword.getText().isEmpty() || role.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in all fields");
            return false;
        }

        if (!password.getText().equals(confirmPassword.getText())) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Passwords do not match");
            return false;
        }

        if (!email.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid email format");
            return false;
        }

        if (!phone.getText().matches("^[0-9]{10}$")) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Phone number must be 10 digits");
            return false;
        }

        return true;
    }

    private void clearFields(TextField employeeId, TextField firstName, TextField lastName, TextField email, TextField phone,
                           TextField address, TextField position, ComboBox<String> department,
                           PasswordField password, PasswordField confirmPassword, ComboBox<String> role) {
        employeeId.clear();
        firstName.clear();
        lastName.clear();
        email.clear();
        phone.clear();
        address.clear();
        position.clear();
        department.setValue(null);
        password.clear();
        confirmPassword.clear();
        role.setValue("Employee"); // Reset to default role
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        try {
        launch(args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}