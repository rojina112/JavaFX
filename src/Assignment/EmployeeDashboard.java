package Assignment;

import DAO.EmployeePageDAO;
import Model.EmployeePageModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class EmployeeDashboard extends Application {
    private Stage primaryStage;
    private BorderPane borderPane;
    private String currentUserEmail = UserLogin.getCurrentUserEmail();
    private String currentUserRole = UserLogin.getCurrentUserRole();
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Employee Dashboard");
        
        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20));
        
        // Create sidebar
        VBox sidebar = createSidebar();
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-padding: 15px;");
        borderPane.setLeft(sidebar);
        
        // Welcome message
        VBox centerContent = new VBox(20);
        centerContent.setPadding(new Insets(20));
        centerContent.setAlignment(Pos.CENTER);
        
        Label welcomeLabel = new Label("Welcome to Employee Dashboard");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        Label roleLabel = new Label("Your role: " + currentUserRole);
        roleLabel.setFont(Font.font("Arial", 16));
        
        centerContent.getChildren().addAll(welcomeLabel, roleLabel);
        borderPane.setCenter(centerContent);
        
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createSidebar() {
        VBox sidebar = new VBox(15);
        sidebar.setPrefWidth(200);
        sidebar.setAlignment(Pos.TOP_CENTER);
        
        Label menuLabel = new Label("MENU");
        menuLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        menuLabel.setTextFill(Color.WHITE);
        
        Button myProfileBtn = createSidebarButton("My Profile");
        
        Button logoutBtn = createSidebarButton("Logout");
        
        // Add buttons to sidebar based on role
        sidebar.getChildren().add(menuLabel);
        sidebar.getChildren().add(myProfileBtn);
        
        // Only add document upload if user is not just an Employee
        if (!currentUserRole.equals("Employee")) {
            Button documentUploadBtn = createSidebarButton("Document Upload");
            sidebar.getChildren().add(documentUploadBtn);
            
            // Add document upload action
            documentUploadBtn.setOnAction(e -> {
                try {
                    DocumentUpload documentUpload = new DocumentUpload();
                    documentUpload.start(new Stage());
                } catch (Exception ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
                }
            });
        }
        
        sidebar.getChildren().add(logoutBtn);
        
        // Button actions
        myProfileBtn.setOnAction(e -> {
            try {
                EmployeePage employeePage = new EmployeePage();
                employeePage.start(new Stage());
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            }
        });
        
        logoutBtn.setOnAction(e -> {
            handleLogoutButton();
        });
        
        return sidebar;
    }
    
    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(180);
        button.setPrefHeight(40);
        button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-weight: bold;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;"));
        
        return button;
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Logout button action
    private void handleLogoutButton() {
        // Reset current user session
        UserLogin.resetCurrentUser();
        
        // Close current stage
        primaryStage.close();
        
        try {
            // Create and show login stage
            UserLogin login = new UserLogin();
            Stage loginStage = new Stage();
            login.start(loginStage);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Could not open login page: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} 