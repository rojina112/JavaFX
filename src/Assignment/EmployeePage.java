package Assignment;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class EmployeePage extends Application {
    private TextField employeeIDField, nameField, emailField, phoneField, addressField;

    @Override
    public void start(Stage primaryStage) {
        // Root Pane
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-border-color: #333;");

        // Title
        Label titleLabel = new Label("Employee Page");
        titleLabel.setLayoutX(150);
        titleLabel.setLayoutY(20);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2A3F54;");

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

        // Action Buttons
        Button updateButton = new Button("Update Info");
        updateButton.setLayoutX(80);
        updateButton.setLayoutY(280);
        updateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5px 10px;");
        updateButton.setOnAction(e -> showAlert("Update Information", "Employee information updated successfully!"));

        Button leaveButton = new Button("Apply for Leave");
        leaveButton.setLayoutX(220);
        leaveButton.setLayoutY(280);
        leaveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5px 10px;");
        leaveButton.setOnAction(e -> showAlert("Leave Application", "Leave application submitted successfully!"));

        // Adding Components to Root
        root.getChildren().addAll(titleLabel, updateButton, leaveButton);

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
