package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.OfficeEmployeeModel;

public class OfficeEmployeeDAO extends Mydatabase {
	public void Insert(OfficeEmployeeModel officeEmployeeModel) {

		String sql = "Insert into officeemployee(employeeid, employeename, position, department, Salary) values(?, ?, ?, ?, ?);";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setInt(1, officeEmployeeModel.getEmployeeId());
			pStat.setString(2, officeEmployeeModel.getEmployeeName());
			pStat.setString(3, officeEmployeeModel.getPosition());
			pStat.setString(4, officeEmployeeModel.getDepartment());
			pStat.setString(5, officeEmployeeModel.getSalary());
			pStat.executeUpdate(); // True if the record was inserted successfully
		} catch (SQLException ex) {
			System.out.println("Error inserting staff: " + ex.getMessage());
		}
	}
	 // Retrieve all employees
    public List<OfficeEmployeeModel> getAllEmployees() {
        List<OfficeEmployeeModel> employees = new ArrayList<>();
        String sql = "SELECT * FROM officeemployee;";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                OfficeEmployeeModel emp = new OfficeEmployeeModel();
                emp.setEmployeeId(rs.getInt("employeeid"));
                emp.setEmployeeName(rs.getString("employeename"));
                emp.setPosition(rs.getString("position"));
                emp.setDepartment(rs.getString("department"));
                emp.setSalary(rs.getString("salary"));

                employees.add(emp);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving employees: " + ex.getMessage());
        }

        return employees;
    }
}
