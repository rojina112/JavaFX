package Assignment;


	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.scene.control.*;
	import javafx.scene.layout.AnchorPane;
	import javafx.stage.Stage;
	import javafx.scene.control.Alert.AlertType;

	public class AdminPage extends Application {
	    private TextField adminIDField, nameField, totalEmployeesField, pendingEnrollmentsField, approvedEnrollmentsField;

	    @Override
	    public void start(Stage primaryStage) {
	        // Root Pane
	        AnchorPane root = new AnchorPane();
	        root.setStyle("-fx-background-color: #2A3F54; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

	        // Title
	        Label titleLabel = new Label("Admin Dashboard");
	        titleLabel.setLayoutX(150);
	        titleLabel.setLayoutY(20);
	        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF;");

	        // Admin Details Labels and TextFields
	        createLabel(root, "Admin ID:", 50, 70);
	        adminIDField = createTextField(root, 160, 70);

	        createLabel(root, "Name:", 50, 110);
	        nameField = createTextField(root, 160, 110);

	        // Enrollment and Employee Details
	        createLabel(root, "Total Employees:", 10, 150);
	        totalEmployeesField = createTextField(root, 160, 150);

	        createLabel(root, "Pending Enrollments:", 10, 185);
	        pendingEnrollmentsField = createTextField(root, 160, 200);

	        createLabel(root, "Approved Enrollments:", 10, 230);
	        approvedEnrollmentsField = createTextField(root, 160, 240);

	        // Default Sample Data
	        adminIDField.setText("");
	        nameField.setText(" ");
	        totalEmployeesField.setText("");
	        pendingEnrollmentsField.setText("");
	        approvedEnrollmentsField.setText("");

	        // Action Button - Send Confirmation Mail
	        Button sendMailButton = new Button("Send Confirmation Mail");
	        sendMailButton.setLayoutX(120);
	        sendMailButton.setLayoutY(290);
	        sendMailButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5px 10px;");
	        sendMailButton.setOnAction(e -> showAlert("Mail Sent", "Confirmation mail sent successfully!"));

	        // Adding Components to Root
	        root.getChildren().addAll(titleLabel, sendMailButton);

	        // Scene Configuration
	        Scene scene = new Scene(root, 400, 350);
	        primaryStage.setTitle("Admin Page");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    // Utility Method to Create Labels
	    private void createLabel(AnchorPane root, String text, int x, int y) {
	        Label label = new Label(text);
	        label.setLayoutX(x);
	        label.setLayoutY(y);
	        label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: bold; -fx-font-size: 14px;");
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


