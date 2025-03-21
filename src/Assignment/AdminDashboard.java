package Assignment;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminDashboard extends Application {

    private StackPane contentArea; // Main content display area

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Dashboard");

        // Sidebar Menu using GridPane
        GridPane sidebar = new GridPane();
        sidebar.setPadding(new Insets(15));
        sidebar.setHgap(10);
        sidebar.setVgap(15);
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 200px;");
        sidebar.setAlignment(Pos.TOP_CENTER);

        // Sidebar Buttons
        Button btnEmployees = new Button("Manage Employees");
        Button btnReports = new Button("Reports");
        Button btnSettings = new Button("Settings");
        Button btnLogout = new Button("Logout");

        // Styling buttons
        String buttonStyle = "-fx-background-color: #34495e; -fx-text-fill: white; -fx-font-size: 14px;";
        btnEmployees.setStyle(buttonStyle);
        btnReports.setStyle(buttonStyle);
        btnSettings.setStyle(buttonStyle);
        btnLogout.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;");

        // Add buttons to GridPane
        sidebar.add(btnEmployees, 0, 0);
        sidebar.add(btnReports, 0, 1);
        sidebar.add(btnSettings, 0, 2);
        sidebar.add(btnLogout, 0, 3);

        // Main Content Area
        contentArea = new StackPane();
        contentArea.setStyle("-fx-background-color: #ecf0f1; -fx-pref-height: 400px;");
        Label defaultContent = new Label("Welcome to Admin Dashboard");
        contentArea.getChildren().add(defaultContent);

        // Button Actions - Change content
        btnEmployees.setOnAction(e -> setContent("Employee Management Section"));
        btnReports.setOnAction(e -> setContent("Reports Section"));
        btnSettings.setOnAction(e -> setContent("Settings Section"));
        btnLogout.setOnAction(e -> System.out.println("Logging out..."));

        // Layout using BorderPane
        BorderPane layout = new BorderPane();
        layout.setLeft(sidebar);
        layout.setCenter(contentArea);

        // Scene Setup
        Scene scene = new Scene(layout, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Function to update content area dynamically
    private void setContent(String text) {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label(text));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

	