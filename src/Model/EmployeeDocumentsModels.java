package Model;

import java.time.LocalDate;

public class EmployeeDocumentsModels{

    private int id;
    private int employeeId;
    private String documentType;
    private String documentPath;
    private LocalDate uploadDate;

    public EmployeeDocumentsModels() {
        // Default constructor
    }

    public EmployeeDocumentsModels(int id, int employeeId, String documentType, String documentPath, LocalDate uploadDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.documentType = documentType;
        this.documentPath = documentPath;
        this.uploadDate = uploadDate;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
