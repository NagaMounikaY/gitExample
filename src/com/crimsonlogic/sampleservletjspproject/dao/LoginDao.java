package com.crimsonlogic.sampleservletjspproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crimsonlogic.sampleservletjspproject.model.LoginBean;
import com.crimsonlogic.sampleservletjspproject.utils.JdbcUtils;

public class LoginDao {
	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
		boolean status = false;
 
		//Class.forName("org");
 
		try (Connection connection = JdbcUtils.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from users where user_name = ? and user_password = ? ")) {
			preparedStatement.setString(1, loginBean.getUserName());
			preparedStatement.setString(2, loginBean.getPassword());
 
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
 
		} catch (SQLException e) {
			// process sql exception
			JdbcUtils.printSQLException(e);
		}
		return status;
	}

}
