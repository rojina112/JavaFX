package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Model.EmployeeDocumentsModels;
import Model.EmployeePageModel;

public class EmployeePageDAO extends Mydatabase {

	public boolean Insert(EmployeePageModel employeePageModel) {
		String sql = "Insert into employee(employeename, address, email, phone, password, role, status) values(?, ?, ?, ?, ?, ?, ?);";

		try (Connection conn = connect();
			 PreparedStatement pStat = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			
			pStat.setString(1, employeePageModel.getEmployeeName());
			pStat.setString(2, employeePageModel.getAddress());
			pStat.setString(3, employeePageModel.getEmail());
			pStat.setString(4, employeePageModel.getPhone());
			pStat.setString(5, employeePageModel.getPassword());
			pStat.setString(6, employeePageModel.getRole());
			pStat.setString(7, employeePageModel.getStatus());
			
			int rowsAffected = pStat.executeUpdate();
			if (rowsAffected > 0) {
				try (ResultSet generatedKeys = pStat.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						int generatedId = generatedKeys.getInt(1);
						employeePageModel.setEmployeeId(generatedId);
						System.out.println("Generated ID: " + generatedId); // Debug line
					}
				}
			}
			return rowsAffected > 0;
		} catch (SQLException ex) {
			System.out.println("Error inserting employee: " + ex.getMessage());
			return false;
		}
	}

	public List<EmployeePageModel> searchById(int employeeId) {
        List<EmployeePageModel> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE employeeid = ?";

        try (PreparedStatement pStat = connect().prepareStatement(sql)) {
            pStat.setInt(1, employeeId);
            ResultSet rs = pStat.executeQuery();

            while (rs.next()) {
                EmployeePageModel epm = new EmployeePageModel();
                epm.setEmployeeId(rs.getInt("employeeid"));
                epm.setEmployeeName(rs.getString("employeename"));
                epm.setPhone(rs.getString("phone"));
                epm.setEmail(rs.getString("email"));
                epm.setAddress(rs.getString("address"));
                epm.setStatus(rs.getString("status"));
                epm.setRole(rs.getString("role"));
                epm.setPassword(rs.getString("password"));
                employees.add(epm);
            }
        } catch (SQLException ex) {
            System.out.println("Error searching for staff: " + ex.getMessage());
        }

        return employees;
    }
    
    public List<EmployeePageModel> searchByEmail(String email) {
        List<EmployeePageModel> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE email = ?";

        try (PreparedStatement pStat = connect().prepareStatement(sql)) {
            pStat.setString(1, email);
            ResultSet rs = pStat.executeQuery();

            while (rs.next()) {
                EmployeePageModel epm = new EmployeePageModel();
                epm.setEmployeeId(rs.getInt("employeeid"));
                epm.setEmployeeName(rs.getString("employeename"));
                epm.setPhone(rs.getString("phone"));
                epm.setEmail(rs.getString("email"));
                epm.setAddress(rs.getString("address"));
                epm.setStatus(rs.getString("status"));
                epm.setRole(rs.getString("role"));
                epm.setPassword(rs.getString("password"));
                employees.add(epm);
            }
        } catch (SQLException ex) {
            System.out.println("Error searching for staff by email: " + ex.getMessage());
        }

        return employees;
    }
    
    public boolean updateEmployee(EmployeePageModel employee) {
        String sql = "UPDATE employee SET employeename = ?, phone = ?, email = ?, address = ?, status = ? WHERE employeeid = ?";

        try (PreparedStatement pStat = connect().prepareStatement(sql)) {
            pStat.setString(1, employee.getEmployeeName());
            pStat.setString(2, employee.getPhone());
            pStat.setString(3, employee.getEmail());
            pStat.setString(4, employee.getAddress());
            pStat.setString(5, employee.getStatus());
            pStat.setInt(6, employee.getEmployeeId());
            
            int rowsAffected = pStat.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error updating employee: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean updateEmployeeStatus(int employeeId, String status) {
        String sql = "UPDATE employee SET status = ? WHERE employeeid = ?";
        
        try (Connection conn = connect();
             PreparedStatement pStat = conn.prepareStatement(sql)) {
            
            pStat.setString(1, status);
            pStat.setInt(2, employeeId);
            
            int rowsAffected = pStat.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating employee status: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateEmployeeRole(String email, String role) {
        String sql = "UPDATE employee SET role = ? WHERE email = ?";
        
        try (Connection conn = connect();
             PreparedStatement pStat = conn.prepareStatement(sql)) {
            
            pStat.setString(1, role);
            pStat.setString(2, email);
            
            int rowsAffected = pStat.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating employee role: " + e.getMessage());
            return false;
        }
    }

	public boolean insertDocument(EmployeeDocumentsModels document) {
		
		return false;
	}

	public List<EmployeeDocumentsModels> getDocumentsByEmployeeId(int employeeId) {
		
		return null;
	}
}