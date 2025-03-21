package Assignment;

	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.scene.control.*;
	import javafx.scene.layout.AnchorPane;
	import javafx.stage.Stage;
	import javafx.scene.control.Alert.AlertType;

	public class ManagerPage extends Application {
	    private TextField managerIDField, nameField, taskField, leaveRequestField;

	    @Override
	    public void start(Stage primaryStage) {
	        // Root Pane
	        AnchorPane root = new AnchorPane();
	        root.setStyle("-fx-background-color: #e3e3e3; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-border-color: #555;");

	        // Title
	        Label titleLabel = new Label("Manager Page");
	        titleLabel.setLayoutX(150);
	        titleLabel.setLayoutY(20);
	        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2A3F54;");

	        // Manager Details Labels and TextFields
	        createLabel(root, "Manager ID:", 50, 70);
	        managerIDField = createTextField(root, 160, 70);

	        createLabel(root, "Name:", 50, 110);
	        nameField = createTextField(root, 160, 110);

	        // Task Assignment
	        createLabel(root, "Assign Task:", 50, 150);
	        taskField = createTextField(root, 160, 150);

	        // Leave Request Approval
	        createLabel(root, "Leave Request ID:", 50, 190);
	        leaveRequestField = createTextField(root, 160, 190);

	        // Default Sample Data
	        managerIDField.setText("");
	        nameField.setText("");

	        // Action Buttons
	        Button assignButton = new Button("Assign Task");
	        assignButton.setLayoutX(80);
	        assignButton.setLayoutY(250);
	        assignButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5px 10px;");
	        assignButton.setOnAction(e -> showAlert("Task Assignment", "Task assigned successfully!"));

	        Button approveButton = new Button("Approve Leave");
	        approveButton.setLayoutX(200);
	        approveButton.setLayoutY(250);
	        approveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5px 10px;");
	        approveButton.setOnAction(e -> showAlert("Leave Approval", "Leave request approved successfully!"));

	        // Adding Components to Root
	        root.getChildren().addAll(titleLabel, assignButton, approveButton);

	        // Scene Configuration
	        Scene scene = new Scene(root, 400, 350);
	        primaryStage.setTitle("Manager Page");
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


