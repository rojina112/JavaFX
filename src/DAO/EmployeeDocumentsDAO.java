package DAO;

import Model.EmployeeDocumentsModels;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDocumentsDAO {
    
    public boolean insertDocument(EmployeeDocumentsModels document) {
        String sql = "INSERT INTO employee_documents (employeeid, document_type, document_path, upload_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = Mydatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, document.getEmployeeId());
            stmt.setString(2, document.getDocumentType());
            stmt.setString(3, document.getDocumentPath());
            stmt.setDate(4, Date.valueOf(document.getUploadDate()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<EmployeeDocumentsModels> getDocumentsByEmployeeId(int employeeId) {
        List<EmployeeDocumentsModels> documents = new ArrayList<>();
        String sql = "SELECT * FROM employee_documents WHERE employeeid = ?";
        try (Connection conn = Mydatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EmployeeDocumentsModels doc = new EmployeeDocumentsModels();
                doc.setId(rs.getInt("id"));
                doc.setEmployeeId(rs.getInt("employeeid"));
                doc.setDocumentType(rs.getString("document_type"));
                doc.setDocumentPath(rs.getString("document_path"));
                doc.setUploadDate(rs.getDate("upload_date").toLocalDate());
                documents.add(doc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }

    public List<EmployeeDocumentsModels> getDocumentById(int id) {
        List<EmployeeDocumentsModels> documents = new ArrayList<>();
        String sql = "SELECT * FROM employee_documents WHERE id = ?";
        try (Connection conn = Mydatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EmployeeDocumentsModels doc = new EmployeeDocumentsModels();
                doc.setId(rs.getInt("id"));
                doc.setEmployeeId(rs.getInt("employeeid"));
                doc.setDocumentType(rs.getString("document_type"));
                doc.setDocumentPath(rs.getString("document_path"));
                doc.setUploadDate(rs.getDate("upload_date").toLocalDate());
                documents.add(doc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }
}
