package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.EmployeePageModel;

public class EmployeePageDAO extends Mydatabase {

	public void Insert(EmployeePageModel employeePageModel) {

		String sql = "Insert into employee(employeeid, employeename, address, email, phone) values(?, ?, ?, ?, ?);";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setInt(1, employeePageModel.getEmployeeId());
			pStat.setString(2, employeePageModel.getEmployeeName());
			pStat.setString(3, employeePageModel.getPhone());
			pStat.setString(4, employeePageModel.getAddress());
			pStat.setString(5, employeePageModel.getEmail());
			pStat.executeUpdate(); // True if the record was inserted successfully
		} catch (SQLException ex) {
			System.out.println("Error inserting staff: " + ex.getMessage());
		}
	}

	public EmployeePageModel searchById(int employeeId) {
        EmployeePageModel epm = null;
        String sql = "SELECT * FROM employee WHERE employeeid = ?";

        try (PreparedStatement pStat = connect().prepareStatement(sql)) {
            pStat.setInt(1, employeeId);
            ResultSet rs = pStat.executeQuery();

            if (rs.next()) {
                epm = new EmployeePageModel();
                epm.setEmployeeId(rs.getInt("employeeid"));
                epm.setEmployeeName(rs.getString("employeename"));
                epm.setPhone(rs.getString("phone"));
                epm.setEmail(rs.getString("email"));
                epm.setAddress(rs.getString("address"));
            }
        } catch (SQLException ex) {
            System.out.println("Error searching for staff: " + ex.getMessage());
        }

        return epm;
    }
}