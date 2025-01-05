package lab8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UCanAccessConnector {
	private static Connection connection;
	static {
		String path = "D:\\Access\\db.accdb";
		String url = "jdbc:ucanaccess://" + path;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			connection = DriverManager.getConnection(url);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
