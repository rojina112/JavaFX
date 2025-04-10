package Assignment;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class AdminDashboard extends Application {

	private StackPane contentArea;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Admin Dashboard");

		// Main container
		BorderPane mainContainer = new BorderPane();
		mainContainer.setStyle("-fx-background-color: #f5f5f5;");

		// Title
		Label titleLabel = new Label("Admin Dashboard");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
		VBox titleBox = new VBox(titleLabel);
		titleBox.setAlignment(Pos.CENTER);
		titleBox.setPadding(new Insets(20));
		mainContainer.setTop(titleBox);

		// Sidebar
		VBox sidebar = new VBox(10);
		sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 200px; -fx-padding: 20;");
		sidebar.setAlignment(Pos.TOP_CENTER);

		// Sidebar title
		Label sidebarTitle = new Label("Admin Menu");
		sidebarTitle.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 0 0 20 0;");
		sidebar.getChildren().add(sidebarTitle);

		// Sidebar buttons
		Button btnEnroll = createSidebarButton("Employee Enrollment");
		Button btnVerify = createSidebarButton("Document Verification");
		Button btnEmployeeInfo = createSidebarButton("Employee Info");
		Button btnLogout = createSidebarButton("Logout");
		btnLogout.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");

		sidebar.getChildren().addAll(btnEnroll, btnVerify, btnEmployeeInfo, btnLogout);
		mainContainer.setLeft(sidebar);

		// Content area
		contentArea = new StackPane();
		contentArea.setStyle("-fx-background-color: #ecf0f1; -fx-padding: 20;");
		
		// Welcome message
		VBox welcomeBox = createWelcomeContent();
		contentArea.getChildren().add(welcomeBox);
		
		mainContainer.setCenter(contentArea);

		// Button actions
		btnEnroll.setOnAction(e -> {
			try {
				EmployeeEnrollment enrollment = new EmployeeEnrollment();
				enrollment.start(new Stage());
			} catch (Exception ex) {
				showAlert(Alert.AlertType.ERROR, "Error", "Could not open Employee Enrollment: " + ex.getMessage());
				ex.printStackTrace();
			}
		});

		btnVerify.setOnAction(e -> {
			try {
				DocumentVerification verification = new DocumentVerification();
				verification.start(new Stage());
			} catch (Exception ex) {
				showAlert(Alert.AlertType.ERROR, "Error", "Could not open Document Verification: " + ex.getMessage());
				ex.printStackTrace();
			}
		});

		btnEmployeeInfo.setOnAction(e -> {
			try {
				EmployeeUpdateForm employeeUpdateForm = new EmployeeUpdateForm();
				employeeUpdateForm.start(new Stage());
			} catch (Exception ex) {
				showAlert(Alert.AlertType.ERROR, "Error", "Could not open Employee Update Form: " + ex.getMessage());
				ex.printStackTrace();
			}
		});

		btnLogout.setOnAction(e -> {
			try {
				// Reset current user before logging out
				UserLogin.resetCurrentUser();
				primaryStage.close();
				UserLogin login = new UserLogin();
				login.start(new Stage());
			} catch (Exception ex) {
				showAlert(Alert.AlertType.ERROR, "Error", "Could not open Login page: " + ex.getMessage());
				ex.printStackTrace();
			}
		});

		// Scene setup
		Scene scene = new Scene(mainContainer, 900, 650);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private VBox createWelcomeContent() {
		VBox welcomeBox = new VBox(30);
		welcomeBox.setAlignment(Pos.CENTER);
		
		Label welcomeTitle = new Label("Welcome to Admin Dashboard");
		welcomeTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
		
		Label welcomeText = new Label("This dashboard provides administrative access to all system features.");
		welcomeText.setStyle("-fx-font-size: 16px; -fx-text-fill: #2c3e50;");
		
		// Feature cards
		HBox featureCards = new HBox(20);
		featureCards.setAlignment(Pos.CENTER);
		
		VBox enrollmentCard = createFeatureCard(
			"Employee Enrollment",
			"Add new employees to the system with personal and employment details."
		);
		
		VBox verificationCard = createFeatureCard(
			"Document Verification",
			"Verify employee documents and update their verification status."
		);
		
		VBox employeeInfoCard = createFeatureCard(
			"Employee Information",
			"View and update employee details and information."
		);
		
		featureCards.getChildren().addAll(enrollmentCard, verificationCard, employeeInfoCard);
		
		welcomeBox.getChildren().addAll(welcomeTitle, welcomeText, featureCards);
		return welcomeBox;
	}
	
	private VBox createFeatureCard(String title, String description) {
		VBox card = new VBox(10);
		card.setPadding(new Insets(20));
		card.setMaxWidth(250);
		card.setMinHeight(150);
		card.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 0);");
		
		Label titleLabel = new Label(title);
		titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
		
		Label descLabel = new Label(description);
		descLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d; -fx-wrap-text: true;");
		
		card.getChildren().addAll(titleLabel, descLabel);
		return card;
	}

	private Button createSidebarButton(String text) {
		Button button = new Button(text);
		button.setMaxWidth(Double.MAX_VALUE);
		button.setPrefHeight(40);
		button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-alignment: center-left; -fx-font-size: 14px;");
		button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; -fx-alignment: center-left; -fx-font-size: 14px;"));
		button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-alignment: center-left; -fx-font-size: 14px;"));
		return button;
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
