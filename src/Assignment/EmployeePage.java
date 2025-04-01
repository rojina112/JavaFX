package Assignment;

import DAO.EmployeePageDAO;

import Model.EmployeePageModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EmployeePage extends Application {
    private TextField employeeIDField, nameField, emailField, phoneField, addressField;

    @Override
    public void start(Stage primaryStage) {
        
        // Root Pane
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-padding: 20px;"); // Removed the background color, border-radius, and border-color

        // Title
        Label titleLabel = new Label("Employee Page");
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Employee Details Labels and TextFields
        createLabel(root, "Employee ID:", 50, 70);
        employeeIDField = createTextField(root, 160, 70);

        createLabel(root, "Name:", 50, 110);
        nameField = createTextField(root, 160, 110);

        createLabel(root, "Email:", 50, 150);
        emailField = createTextField(root, 160, 150);

        createLabel(root, "Phone:", 50, 190);
        phoneField = createTextField(root, 160, 190);

        createLabel(root, "Address:", 50, 230);
        addressField = createTextField(root, 160, 230);

        // Default Sample Data
        employeeIDField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");

        Button saveButton = new Button("save");
        saveButton.setLayoutX(80);
        saveButton.setLayoutY(280);
        saveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5px 10px;");

        // Search Button
        Button searchButton = new Button("Search");
        searchButton.setLayoutX(310);
        searchButton.setLayoutY(70);
        searchButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5px 10px;");

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    int employeeId = Integer.parseInt(employeeIDField.getText().trim());
                    EmployeePageModel epm = new EmployeePageDAO().searchById(employeeId);

                    if (epm != null) {
                        nameField.setText(epm.getEmployeeName());
                        emailField.setText(epm.getEmail());
                        phoneField.setText(epm.getPhone());
                        addressField.setText(epm.getAddress());
                    } else {
                        showAlert("Search Failed", "Employee not found!");
                    }
                } catch (NumberFormatException e) {
                    showAlert("Error", "Invalid Employee ID format!");
                }
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EmployeePageModel epm = new EmployeePageModel();
                try {
                    epm.setEmployeeId(Integer.parseInt(employeeIDField.getText()));
                    epm.setEmployeeName(nameField.getText());
                    epm.setPhone(phoneField.getText());
                    epm.setAddress(addressField.getText());
                    epm.setEmail(emailField.getText());

                    new EmployeePageDAO().Insert(epm);

                } catch (Exception ex) {
                    showAlert("Error", "Failed to save the information!");
                }
            }
        });

        // Action Buttons
        Button updateButton = new Button("Update Info");
        updateButton.setLayoutX(220);
        updateButton.setLayoutY(280);
        updateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5px 10px;");
        updateButton.setOnAction(e -> showAlert("Update Information", "Employee information updated successfully!"));

        // Adding Components to Root
        root.getChildren().addAll(titleLabel, saveButton, updateButton, searchButton);

        // Scene Configuration
        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("Employee Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Utility Method to Create Labels
    private void createLabel(AnchorPane root, String text, int x, int y) {
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setStyle("-fx-text-fill: #333; -fx-font-weight: bold; -fx-font-size: 14px;");
        root.getChildren().add(label);
    }

    // Utility Method to Create TextFields
    private TextField createTextField(AnchorPane root, int x, int y) {
        TextField textField = new TextField();
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setStyle("-fx-background-color: #FFF; -fx-border-color: #AAA; -fx-padding: 5px;");
        root.getChildren().add(textField);
        return textField;
    }

    // Method to Show Alert Dialogs
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
