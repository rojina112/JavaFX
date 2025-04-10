package Model;

import java.sql.Timestamp;

/**
 * Model class for employee document storage
 */
public class EmployeeDocumentModel {
    private int id;
    private int employeeId;
    private String documentType;
    private String documentPath;
    private Timestamp uploadDate;

    public EmployeeDocumentModel() {
        this.id = 0;
        this.employeeId = 0;
        this.documentType = "";
        this.documentPath = "";
        this.uploadDate = null;
    }

    public EmployeeDocumentModel(int id, int employeeId, String documentType, String documentPath, Timestamp uploadDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.documentType = documentType;
        this.documentPath = documentPath;
        this.uploadDate = uploadDate;
    }

    public EmployeeDocumentModel(int employeeId, String documentType, String documentPath) {
        this.employeeId = employeeId;
        this.documentType = documentType;
        this.documentPath = documentPath;
    }

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
        if (documentType == null || documentType.trim().isEmpty()) {
            throw new IllegalArgumentException("Document type cannot be empty");
        }
        this.documentType = documentType;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        if (documentPath == null || documentPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Document path cannot be empty");
        }
        this.documentPath = documentPath;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }
} 