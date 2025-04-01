package Assignment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmployeeEnrollment extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Enrollment");

        // Creating Labels
        Label lblFirstName = new Label("First name:");
        Label lblLastName = new Label("Last name:");
        Label lblEmail = new Label("Email:");
        Label lblPhone = new Label("Phone:");
        Label lblPosition = new Label("Position:");
        Label lblDepartment = new Label("Department:");

        // Creating Input Fields
        TextField txtFirstName = new TextField();
        TextField txtLastName = new TextField();
        TextField txtEmail = new TextField();
        TextField txtPhone = new TextField();
        TextField txtPosition = new TextField();
        ComboBox<String> cmbDepartment = new ComboBox<>();
        cmbDepartment.getItems().addAll("HR", "IT", "Finance", "Marketing");

        // Buttons
        Button btnApprove = new Button("Approve");
        Button btnRemove = new Button("Remove");
        
        // Approve Button Action
        btnApprove.setOnAction(event -> {
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();
            String position = txtPosition.getText();
            String department = cmbDepartment.getValue();

            // Check if all fields are filled
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || 
                phone.isEmpty() || position.isEmpty() || department == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields before submitting.");
                alert.showAndWait();
            } else {
                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Enrollment Successful");
                alert.setHeaderText(null);
                alert.setContentText("Employee " + firstName + " " + lastName + " has been enrolled successfully!");
                alert.showAndWait();

                // Optionally, clear input fields after submission
                txtFirstName.clear();
                txtLastName.clear();
                txtEmail.clear();
                txtPhone.clear();
                txtPosition.clear();
                cmbDepartment.setValue(null);
            }
        });
        
        // Remove Button Action
        btnRemove.setOnAction(event -> {
            // Clear all input fields
            txtFirstName.clear();
            txtLastName.clear();
            txtEmail.clear();
            txtPhone.clear();
            txtPosition.clear();
            cmbDepartment.setValue(null);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Form Cleared");
            alert.setHeaderText(null);
            alert.setContentText("It has been removed.");
            alert.showAndWait();
        });

        // Layout Grid
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        // Adding Elements to Grid
        grid.add(lblFirstName, 0, 0);
        grid.add(txtFirstName, 1, 0);
        grid.add(lblLastName, 0, 1);
        grid.add(txtLastName, 1, 1);
        grid.add(lblEmail, 0, 2);
        grid.add(txtEmail, 1, 2);
        grid.add(lblPhone, 0, 3);
        grid.add(txtPhone, 1, 3);
        grid.add(lblPosition, 0, 4);
        grid.add(txtPosition, 1, 4);
        grid.add(lblDepartment, 0, 5);
        grid.add(cmbDepartment, 1, 5);
        grid.add(btnApprove, 1, 6);
        grid.add(btnRemove, 1, 7);

        // Setting Scene
        Scene scene = new Scene(grid, 350, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}