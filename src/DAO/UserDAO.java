package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.UserModel;

public class UserDAO extends Mydatabase {
	public UserModel login(String username, String password, String userType) {
		UserModel user = null;
        String SQL = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ? AND USERTYPE = ?";
        
        try (Connection conn = connect(); 
             PreparedStatement pStat = conn.prepareStatement(SQL)) {
             
            pStat.setString(1, username);
            pStat.setString(2, password);
            pStat.setString(3, userType);
            
            ResultSet resultSet = pStat.executeQuery();
            if (resultSet.next()) {
                user = new UserModel();
                user.setUserId(resultSet.getInt("userid"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setUserType(resultSet.getString("usertype"));
            
            }
            pStat.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return user;
    }
}
