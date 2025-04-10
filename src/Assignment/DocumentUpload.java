package Assignment;

import DAO.EmployeeDocumentDAO;
import DAO.EmployeePageDAO;
import Model.EmployeeDocumentModel;
import Model.EmployeePageModel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class DocumentUpload extends Application {

    private TextField txtEmployeeId;
    private TextField txtEmployeeName;
    private TextField txtEmployeeEmail;
    private ComboBox<String> cmbDocumentType;
    private TextField txtFilePath;
    private ListView<String> lstDocuments;
    
    private EmployeePageModel currentEmployee;
    private static final String DOCUMENTS_FOLDER = "employee_documents";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Document Upload");

        // Create documents folder if it doesn't exist
        File documentsDir = new File(DOCUMENTS_FOLDER);
        if (!documentsDir.exists()) {
            documentsDir.mkdirs();
        }

        // Main container
        BorderPane mainContainer = new BorderPane();
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");

        // Title
        Label titleLabel = new Label("Document Upload");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        VBox titleBox = new VBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20));
        mainContainer.setTop(titleBox);

        // Search area
        GridPane searchPane = new GridPane();
        searchPane.setHgap(10);
        searchPane.setVgap(10);
        searchPane.setPadding(new Insets(10));
        searchPane.setAlignment(Pos.CENTER);

        Label lblSearch = new Label("Search Employee:");
        TextField txtSearch = new TextField();
        txtSearch.setPromptText("Enter Employee ID or Email");
        Button btnSearch = new Button("Search");
        btnSearch.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        searchPane.add(lblSearch, 0, 0);
        searchPane.add(txtSearch, 1, 0);
        searchPane.add(btnSearch, 2, 0);

        // Employee details area
        GridPane detailsPane = new GridPane();
        detailsPane.setHgap(10);
        detailsPane.setVgap(10);
        detailsPane.setPadding(new Insets(20));
        detailsPane.setStyle("-fx-background-color: white; -fx-background-radius: 5;");

        Label lblEmployeeId = new Label("Employee ID:");
        txtEmployeeId = new TextField();
        txtEmployeeId.setEditable(false);

        Label lblEmployeeName = new Label("Name:");
        txtEmployeeName = new TextField();
        txtEmployeeName.setEditable(false);

        Label lblEmployeeEmail = new Label("Email:");
        txtEmployeeEmail = new TextField();
        txtEmployeeEmail.setEditable(false);

        Label lblDocumentType = new Label("Document Type:");
        cmbDocumentType = new ComboBox<>();
        cmbDocumentType.getItems().addAll("ID", "Passport", "Resume", "Certificate", "Other");
        cmbDocumentType.setPromptText("Select document type");

        Label lblFilePath = new Label("File:");
        txtFilePath = new TextField();
        txtFilePath.setEditable(false);
        Button btnBrowse = new Button("Browse");
        btnBrowse.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        Button btnUpload = new Button("Upload Document");
        btnUpload.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");

        detailsPane.add(lblEmployeeId, 0, 0);
        detailsPane.add(txtEmployeeId, 1, 0);
        detailsPane.add(lblEmployeeName, 0, 1);
        detailsPane.add(txtEmployeeName, 1, 1);
        detailsPane.add(lblEmployeeEmail, 0, 2);
        detailsPane.add(txtEmployeeEmail, 1, 2);
        detailsPane.add(lblDocumentType, 0, 3);
        detailsPane.add(cmbDocumentType, 1, 3);
        
        HBox fileBox = new HBox(10);
        fileBox.getChildren().addAll(txtFilePath, btnBrowse);
        detailsPane.add(lblFilePath, 0, 4);
        detailsPane.add(fileBox, 1, 4);
        detailsPane.add(btnUpload, 1, 5);

        // Documents list area
        VBox documentsArea = new VBox(10);
        documentsArea.setPadding(new Insets(20));
        
        Label lblDocuments = new Label("Uploaded Documents:");
        lblDocuments.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        lstDocuments = new ListView<>();
        lstDocuments.setPrefHeight(200);
        
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        
        Button btnDelete = new Button("Delete Selected");
        btnDelete.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        
        Button btnClose = new Button("Close");
        btnClose.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        
        buttonBox.getChildren().addAll(btnDelete, btnClose);
        
        documentsArea.getChildren().addAll(lblDocuments, lstDocuments, buttonBox);

        // Combine all areas
        VBox contentArea = new VBox(20);
        contentArea.setPadding(new Insets(20));
        contentArea.getChildren().addAll(searchPane, detailsPane, documentsArea);
        
        mainContainer.setCenter(contentArea);

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
                    
                    // Load employee's existing documents
                    loadEmployeeDocuments();
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            }
        });

        btnBrowse.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Document");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Document Files", "*.pdf", "*.doc", "*.docx", "*.jpg", "*.jpeg", "*.png"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                txtFilePath.setText(selectedFile.getAbsolutePath());
            }
        });

        btnUpload.setOnAction(e -> {
            if (currentEmployee == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please search for an employee first");
                return;
            }
            
            if (cmbDocumentType.getValue() == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select a document type");
                return;
            }
            
            String filePath = txtFilePath.getText();
            if (filePath.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select a file to upload");
                return;
            }
            
            try {
                File sourceFile = new File(filePath);
                String fileExtension = getFileExtension(sourceFile.getName());
                String documentType = cmbDocumentType.getValue();
                
                // Create employee folder if it doesn't exist
                String employeeFolder = DOCUMENTS_FOLDER + "/" + currentEmployee.getEmployeeId();
                File empDir = new File(employeeFolder);
                if (!empDir.exists()) {
                    empDir.mkdirs();
                }
                
                // Generate a unique filename
                String destinationFileName = documentType.toLowerCase() + "_" + 
                                            System.currentTimeMillis() + 
                                            "." + fileExtension;
                
                Path destinationPath = Paths.get(employeeFolder, destinationFileName);
                
                // Copy the file
                Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                
                // Save to database
                EmployeeDocumentModel document = new EmployeeDocumentModel();
                document.setEmployeeId(currentEmployee.getEmployeeId());
                document.setDocumentType(documentType);
                document.setDocumentPath(destinationPath.toString());
                
                EmployeeDocumentDAO docDao = new EmployeeDocumentDAO();
                boolean success = docDao.insertDocument(document);
                
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Document uploaded successfully");
                    // Clear form fields and reload documents
                    txtFilePath.clear();
                    cmbDocumentType.setValue(null);
                    loadEmployeeDocuments();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to record document in database");
                }
                
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to upload document: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        
        btnDelete.setOnAction(e -> {
            String selected = lstDocuments.getSelectionModel().getSelectedItem();
            if (selected == null || currentEmployee == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select a document to delete");
                return;
            }
            
            try {
                // Extract document ID from the displayed text
                int docId = Integer.parseInt(selected.split(":")[0].trim());
                
                EmployeeDocumentDAO docDao = new EmployeeDocumentDAO();
                boolean success = docDao.deleteDocument(docId);
                
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Document deleted successfully");
                    loadEmployeeDocuments();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete document");
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete document: " + ex.getMessage());
            }
        });
        
        // Close button action
        btnClose.setOnAction(e -> primaryStage.close());

        // Set up scene
        Scene scene = new Scene(mainContainer, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void loadEmployeeDocuments() {
        if (currentEmployee == null) return;
        
        try {
            EmployeeDocumentDAO docDao = new EmployeeDocumentDAO();
            List<EmployeeDocumentModel> documents = docDao.getDocumentsByEmployeeId(currentEmployee.getEmployeeId());
            
            lstDocuments.getItems().clear();
            
            for (EmployeeDocumentModel doc : documents) {
                String listItem = doc.getId() + ": " + doc.getDocumentType() + " (" + 
                                  doc.getUploadDate() + ")";
                lstDocuments.getItems().add(listItem);
            }
            
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load documents: " + ex.getMessage());
        }
    }
    
    private void clearFields() {
        txtEmployeeId.clear();
        txtEmployeeName.clear();
        txtEmployeeEmail.clear();
        txtFilePath.clear();
        cmbDocumentType.setValue(null);
        lstDocuments.getItems().clear();
        currentEmployee = null;
    }
    
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
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