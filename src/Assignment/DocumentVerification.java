package Assignment;


import DAO.EmployeeDocumentsDAO;
import DAO.EmployeePageDAO;
import Model.EmployeeDocumentsModels;
import Model.EmployeePageModel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

public class DocumentVerification extends Application {

    private TextField txtEmployeeId;
    private TextField txtEmployeeName;
    private TextField txtEmployeeEmail;
    private TextField txtStatus;
    private ListView<String> lstDocuments;
    private EmployeePageModel currentEmployee;
    private ComboBox<String> cmbDocumentType;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Document Verification");

        // Main container
        BorderPane mainContainer = new BorderPane();
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");

        // Title
        Label titleLabel = new Label("Document Verification");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        VBox titleBox = new VBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20));
        mainContainer.setTop(titleBox);

        // Search area
        GridPane searchPane = new GridPane();
        searchPane.setHgap(10);
        searchPane.setVgap(10);
        searchPane.setPadding(new Insets(20));
        searchPane.setAlignment(Pos.CENTER);
        searchPane.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        Label lblSearch = new Label("Search Employee:");
        lblSearch.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        TextField txtSearch = new TextField();
        txtSearch.setPromptText("Enter Employee ID or Email");
        txtSearch.setPrefWidth(300);
        Button btnSearch = new Button("Search");
        btnSearch.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 8 15;");

        searchPane.add(lblSearch, 0, 0);
        searchPane.add(txtSearch, 1, 0);
        searchPane.add(btnSearch, 2, 0);

        // Employee details area
        GridPane detailsPane = new GridPane();
        detailsPane.setHgap(15);
        detailsPane.setVgap(15);
        detailsPane.setPadding(new Insets(20));
        detailsPane.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        Label lblEmployeeId = new Label("Employee ID:");
        lblEmployeeId.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        txtEmployeeId = new TextField();
        txtEmployeeId.setEditable(false);
        txtEmployeeId.setPrefWidth(300);

        Label lblEmployeeName = new Label("Name:");
        lblEmployeeName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        txtEmployeeName = new TextField();
        txtEmployeeName.setEditable(false);
        txtEmployeeName.setPrefWidth(300);

        Label lblEmployeeEmail = new Label("Email:");
        lblEmployeeEmail.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        txtEmployeeEmail = new TextField();
        txtEmployeeEmail.setEditable(false);
        txtEmployeeEmail.setPrefWidth(300);
        
        Label lblStatus = new Label("Status:");
        lblStatus.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        txtStatus = new TextField();
        txtStatus.setEditable(false);
        txtStatus.setPrefWidth(300);

        detailsPane.add(lblEmployeeId, 0, 0);
        detailsPane.add(txtEmployeeId, 1, 0);
        detailsPane.add(lblEmployeeName, 0, 1);
        detailsPane.add(txtEmployeeName, 1, 1);
        detailsPane.add(lblEmployeeEmail, 0, 2);
        detailsPane.add(txtEmployeeEmail, 1, 2);
        detailsPane.add(lblStatus, 0, 3);
        detailsPane.add(txtStatus, 1, 3);

        // Document upload section
        GridPane uploadPane = new GridPane();
        uploadPane.setHgap(15);
        uploadPane.setVgap(15);
        uploadPane.setPadding(new Insets(20));
        uploadPane.setStyle("-fx-background-color: #e8f5e9; -fx-background-radius: 5;");
        
        Label lblUploadTitle = new Label("Upload Documents");
        lblUploadTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        
        Label lblDocType = new Label("Document Type:");
        lblDocType.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        cmbDocumentType = new ComboBox<>();
        cmbDocumentType.getItems().addAll("Citizenship", "Passport", "Driving License", "SSF");
        cmbDocumentType.setPromptText("Select Document Type");
        cmbDocumentType.setPrefWidth(300);
        
        TextField txtFilePath = new TextField();
        txtFilePath.setEditable(false);
        txtFilePath.setPromptText("No file selected");
        txtFilePath.setPrefWidth(300);
        
        Button btnBrowse = new Button("Browse");
        btnBrowse.setStyle("-fx-background-color: #ff9800; -fx-text-fill: white; -fx-padding: 8 15;");
        
        Button btnUpload = new Button("Upload Document");
        btnUpload.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 8 15;");
        
        uploadPane.add(lblUploadTitle, 0, 0, 3, 1);
        uploadPane.add(lblDocType, 0, 1);
        uploadPane.add(cmbDocumentType, 1, 1, 2, 1);
        uploadPane.add(txtFilePath, 0, 2, 2, 1);
        uploadPane.add(btnBrowse, 2, 2);
        uploadPane.add(btnUpload, 1, 3);

        // Documents list area
        VBox documentsArea = new VBox(15);
        documentsArea.setPadding(new Insets(20));
        documentsArea.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        
        Label lblDocuments = new Label("Employee Documents:");
        lblDocuments.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        
        lstDocuments = new ListView<>();
        lstDocuments.setPrefHeight(200);
        
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(15, 0, 10, 0));
        
        Button btnViewDocument = new Button("View Document");
        btnViewDocument.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 8 15;");
        
        Button btnVerifyEmployee = new Button("Verify Employee");
        btnVerifyEmployee.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 15;");
        
        Button btnCloseVerification = new Button("Close");
        btnCloseVerification.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-padding: 8 15;");
        
        buttonBox.getChildren().addAll(btnViewDocument, btnVerifyEmployee, btnCloseVerification);
        
        documentsArea.getChildren().addAll(lblDocuments, lstDocuments, buttonBox);

        // Combine all areas
        VBox contentArea = new VBox(20);
        contentArea.setPadding(new Insets(20));
        contentArea.getChildren().addAll(searchPane, detailsPane, uploadPane, documentsArea);
        
        // Wrap the content area in a ScrollPane to make it scrollable
        ScrollPane scrollPane = new ScrollPane(contentArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefViewportHeight(600); // Set a preferred height for the viewport
        scrollPane.setVvalue(0); // Start at the top
        
        // Add padding to ensure buttons are visible
        VBox scrollContainer = new VBox(scrollPane);
        scrollContainer.setPadding(new Insets(0, 0, 20, 0)); // Add bottom padding
        
        mainContainer.setCenter(scrollContainer);

        // Button actions
        btnSearch.setOnAction(e -> {
            String searchText = txtSearch.getText().trim();
            if (searchText.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please enter an Employee ID or Email");
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
                    txtEmployeeId.setText(String.valueOf(currentEmployee.getEmployeeId()));
                    txtEmployeeName.setText(currentEmployee.getEmployeeName());
                    txtEmployeeEmail.setText(currentEmployee.getEmail());
                    txtStatus.setText(currentEmployee.getStatus());
                    
                    // Load employee's documents
                    loadEmployeeDocuments();
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            }
        });

        // File browser action
        final File[] selectedFile = new File[1];
        btnBrowse.setOnAction(e -> {
            if (currentEmployee == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please search for an employee first");
                return;
            }
            
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Document");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            
            selectedFile[0] = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile[0] != null) {
                txtFilePath.setText(selectedFile[0].getAbsolutePath());
            }
        });
        
        // Upload document action
        btnUpload.setOnAction(e -> {
            if (currentEmployee == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please search for an employee first");
                return;
            }
            
            if (selectedFile[0] == null || !selectedFile[0].exists()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select a file to upload");
                return;
            }
            
            if (cmbDocumentType.getValue() == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select a document type");
                return;
            }
            
            try {
                // Create documents directory if it doesn't exist
                String documentsDir = "documents/" + currentEmployee.getEmployeeId();
                Path dirPath = Paths.get(documentsDir);
                Files.createDirectories(dirPath);
                
                // Copy file to documents directory
                String fileName = selectedFile[0].getName();
                String documentType = cmbDocumentType.getValue();
                String destFileName = documentType.replaceAll("\\s+", "_").toLowerCase() + "_" + System.currentTimeMillis() + "_" + fileName;
                Path destPath = dirPath.resolve(destFileName);
                
                Files.copy(selectedFile[0].toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
                
                // Save document info to database
                EmployeeDocumentsModels document = new EmployeeDocumentsModels();
                document.setEmployeeId(currentEmployee.getEmployeeId());
                document.setDocumentType(documentType);
                document.setDocumentPath(destPath.toString());
                document.setUploadDate(LocalDate.now());
                
                EmployeeDocumentsDAO docDao = new EmployeeDocumentsDAO();
                boolean success = docDao.insertDocument(document);
                
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Document uploaded successfully");
                    txtFilePath.clear();
                    selectedFile[0] = null;
                    cmbDocumentType.setValue(null);
                    loadEmployeeDocuments();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to upload document");
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to upload document: " + ex.getMessage());
            }
        });

        btnViewDocument.setOnAction(e -> {
            String selected = lstDocuments.getSelectionModel().getSelectedItem();
            if (selected == null || currentEmployee == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select a document to view");
                return;
            }
            
            try {
                // Extract document ID from the displayed text
                int docId = Integer.parseInt(selected.split(":")[0].trim());
                
                EmployeeDocumentsDAO docDao = new EmployeeDocumentsDAO();
                List<EmployeeDocumentsModels> documents = docDao.getDocumentById(docId);
                
                if (documents.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Document not found");
                    return;
                }
                
                EmployeeDocumentsModels document = documents.get(0);
                String filePath = document.getDocumentPath();
                
                File file = new File(filePath);
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "File not found at: " + filePath);
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to open document: " + ex.getMessage());
            }
        });
        
        btnVerifyEmployee.setOnAction(e -> {
            if (currentEmployee == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please search for an employee first");
                return;
            }
            
            // Check if documents exist
            if (lstDocuments.getItems().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Employee has no documents uploaded. Cannot verify.");
                return;
            }
            
            // Check if already verified
            if ("Verified".equals(currentEmployee.getStatus())) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Employee is already verified");
                return;
            }
            
            try {
                // Update employee status to verified
                EmployeePageDAO dao = new EmployeePageDAO();
                boolean success = dao.updateEmployeeStatus(currentEmployee.getEmployeeId(), "Verified");
                
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Employee verified successfully");
                    currentEmployee.setStatus("Verified");
                    txtStatus.setText("Verified");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to verify employee");
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to verify employee: " + ex.getMessage());
            }
        });

        // Set up scene with larger dimensions
        Scene scene = new Scene(mainContainer, 800, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Add event handler for close button
        btnCloseVerification.setOnAction(e -> primaryStage.close());
    }
    
    private void loadEmployeeDocuments() {
        if (currentEmployee == null) return;
        
        try {
            EmployeeDocumentsDAO docDao = new EmployeeDocumentsDAO();
            List<EmployeeDocumentsModels> documents = docDao.getDocumentsByEmployeeId(currentEmployee.getEmployeeId());
            
            lstDocuments.getItems().clear();
            
            if (documents.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "No documents found for this employee");
            } else {
                for (EmployeeDocumentsModels doc : documents) {
                    String listItem = doc.getId() + ": " + doc.getDocumentType() + " (" + 
                                      doc.getUploadDate() + ")";
                    lstDocuments.getItems().add(listItem);
                }
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load documents: " + e.getMessage());
        }
    }
    
    private void clearFields() {
        txtEmployeeId.clear();
        txtEmployeeName.clear();
        txtEmployeeEmail.clear();
        txtStatus.clear();
        lstDocuments.getItems().clear();
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
