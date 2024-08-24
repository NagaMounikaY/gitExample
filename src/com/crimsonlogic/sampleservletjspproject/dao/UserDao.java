package com.crimsonlogic.sampleservletjspproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.crimsonlogic.sampleservletjspproject.model.User;
import com.crimsonlogic.sampleservletjspproject.utils.JdbcUtils;

public class UserDao {
	public int registerUser(User employee) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO users"
				+ "  (user_first_name, user_last_name, user_name, user_password) VALUES "
				+ " (?, ?, ?, ?);";
 
		int result = 0;
		try (Connection connection = JdbcUtils.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			preparedStatement.setString(1, employee.getUser_firstName());
			preparedStatement.setString(2, employee.getUser_lastName());
			preparedStatement.setString(3, employee.getUser_userName());
			preparedStatement.setString(4, employee.getUser_password());
 
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			result = preparedStatement.executeUpdate();
 
		} catch (SQLException e) {
			// process sql exception
			JdbcUtils.printSQLException(e);
		}
		return result;
	}

}
