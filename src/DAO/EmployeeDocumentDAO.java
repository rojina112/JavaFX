package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.EmployeeDocumentModel;

/**
 * Data Access Object for Employee Document operations
 */
public class EmployeeDocumentDAO extends Mydatabase {

    /**
     * Insert a new document record
     * @param document The document model with data to insert
     * @return true if insert was successful, false otherwise
     */
    public boolean insertDocument(EmployeeDocumentModel document) {
        String sql = "INSERT INTO employee_documents (employeeid, document_type, document_path) VALUES (?, ?, ?)";
        
        try (Connection conn = connect();
             PreparedStatement pStat = conn.prepareStatement(sql)) {
            
            pStat.setInt(1, document.getEmployeeId());
            pStat.setString(2, document.getDocumentType());
            pStat.setString(3, document.getDocumentPath());
            
            int rowsAffected = pStat.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error inserting document: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get all documents for a specific employee
     * @param employeeId The ID of the employee
     * @return List of document models
     */
    public List<EmployeeDocumentModel> getDocumentsByEmployeeId(int employeeId) {
        List<EmployeeDocumentModel> documents = new ArrayList<>();
        String sql = "SELECT * FROM employee_documents WHERE employeeid = ?";
        
        try (Connection conn = connect();
             PreparedStatement pStat = conn.prepareStatement(sql)) {
            
            pStat.setInt(1, employeeId);
            ResultSet rs = pStat.executeQuery();
            
            while (rs.next()) {
                EmployeeDocumentModel document = new EmployeeDocumentModel();
                document.setId(rs.getInt("id"));
                document.setEmployeeId(rs.getInt("employeeid"));
                document.setDocumentType(rs.getString("document_type"));
                document.setDocumentPath(rs.getString("document_path"));
                document.setUploadDate(rs.getTimestamp("upload_date"));
                
                documents.add(document);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving documents: " + e.getMessage());
        }
        
        return documents;
    }
    
    /**
     * Get a document by its ID
     * @param documentId The ID of the document
     * @return List of document models
     */
    public List<EmployeeDocumentModel> getDocumentById(int documentId) {
        List<EmployeeDocumentModel> documents = new ArrayList<>();
        String sql = "SELECT * FROM employee_documents WHERE id = ?";
        
        try (Connection conn = connect();
             PreparedStatement pStat = conn.prepareStatement(sql)) {
            
            pStat.setInt(1, documentId);
            ResultSet rs = pStat.executeQuery();
            
            while (rs.next()) {
                EmployeeDocumentModel document = new EmployeeDocumentModel();
                document.setId(rs.getInt("id"));
                document.setEmployeeId(rs.getInt("employeeid"));
                document.setDocumentType(rs.getString("document_type"));
                document.setDocumentPath(rs.getString("document_path"));
                document.setUploadDate(rs.getTimestamp("upload_date"));
                
                documents.add(document);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving document: " + e.getMessage());
        }
        
        return documents;
    }
    
    /**
     * Delete a document by its ID
     * @param documentId The ID of the document to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteDocument(int documentId) {
        String sql = "DELETE FROM employee_documents WHERE id = ?";
        
        try (Connection conn = connect();
             PreparedStatement pStat = conn.prepareStatement(sql)) {
            
            pStat.setInt(1, documentId);
            int rowsAffected = pStat.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting document: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Check if an employee has a document of a specific type
     * @param employeeId The employee ID
     * @param documentType The document type to check
     * @return true if the document exists, false otherwise
     */
    public boolean hasDocumentOfType(int employeeId, String documentType) {
        String sql = "SELECT COUNT(*) FROM employee_documents WHERE employeeid = ? AND document_type = ?";
        
        try (Connection conn = connect();
             PreparedStatement pStat = conn.prepareStatement(sql)) {
            
            pStat.setInt(1, employeeId);
            pStat.setString(2, documentType);
            ResultSet rs = pStat.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking for document: " + e.getMessage());
        }
        
        return false;
    }
} 