package lab8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {

	@Override
	public boolean checkUser(String username) {
		Connection conn = UCanAccessConnector.getConnection();
		String sql = "SELECT * FROM user WHERE username = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("username") != null)
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean login(String username, String password) {
		Connection conn = UCanAccessConnector.getConnection();
		String sql = "SELECT * FROM user WHERE username = ? and password = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("username") != null)
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		IUserDAO userDAO = new UserDAO();
//		System.out.println(userDAO.checkUser("teo"));
		System.out.println(userDAO.login("admin", "admdsdin"));
	}

}
