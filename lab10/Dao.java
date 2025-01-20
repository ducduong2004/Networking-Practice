package lab10;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
String path = "jdbc:ucanaccess://src/data/Store.accdb";
private Connection connect;
public Dao() throws RemoteException {
	try {
		this.connect = DriverManager.getConnection(path);
	} catch (Exception e) {
		throw new RemoteException(e.getMessage());
	}
}
public Product getProdudctByID (int id) throws RemoteException {
	
	
	try {
		Product result = new Product();
		String query = "SELECT * FROM PRODUCT WHERE ID = ? ";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, String.valueOf(id));
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int  PID  = rs.getInt(1);
			String PName = rs.getString(2);
			int Pcount = rs.getInt(3);
			double PPrice = rs.getDouble(4);
			result = new Product(PID, PName, Pcount, PPrice);
			return result;
			
		}
	} catch (SQLException e) {
		throw new RemoteException(e.getMessage());
	}
	return null;
}
public boolean checkUserName (String userName) throws RemoteException {
	try {
		String query = "SELECT Name FROM USER WHERE Name =  ? ";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String name  = rs.getString(1);
			if (name != null) {
				return true;
			}
		}
	} catch (SQLException e) {
		throw new RemoteException(e.getMessage());
	}
	return false;
}
public int getLoggin (String userName , String pass) throws RemoteException {
	try {
		String query = "SELECT ID FROM USER WHERE Name = ? AND Pass = ? ";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, userName);
		ps.setString(2, pass);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
		return rs.getInt(1);
		}
	} catch (SQLException e) {
		throw new RemoteException(e.getMessage());
	}
	return -1;
}
public static void main(String[] args) throws RemoteException {
	Dao dao = new Dao();
	System.out.println(dao.checkUserName("Anh"));
	System.out.println(dao.getLoggin("Anh", "2004"));
	System.out.println(dao.getProdudctByID(1));
	System.out.println(dao.getProdudctByName("kem"));
}
public List<Product> getProdudctByName(String tenSP) throws RemoteException {
	List<Product> result = new ArrayList<Product>();

	try {
		String query = "SELECT * FROM PRODUCT WHERE Name like ? ";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, "%"+tenSP+"%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int  PID  = rs.getInt(1);
			String PName = rs.getString(2);
			int Pcount = rs.getInt(3);
			double PPrice = rs.getDouble(4);
			Product p  = new Product(PID, PName, Pcount, PPrice);
			result.add(p);
		}
	} catch (SQLException e) {
		throw new RemoteException(e.getMessage());
	}
	return result;
}
public boolean updateSP (int idSP) throws RemoteException {
	String query = "UPDATE Product set count = count-1 WHERE ID = ? ";
	try {
	
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, String.valueOf(idSP));
	
		int rs = ps.executeUpdate();
		if (rs != -1) {
			return true;
		}
	} catch (SQLException e) {
		throw new RemoteException(e.getMessage());
	}
	return false;
}
public int getCountSP (int id) throws RemoteException {

	try {
		String query = "SELECT count  FROM PRODUCT WHERE ID = ? ";
		PreparedStatement ps = connect.prepareStatement(query);
		ps.setString(1, String.valueOf(id));
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			return rs.getInt(1);
		}
	} catch (SQLException e) {
		throw new RemoteException(e.getMessage());
	}
	return -1;
}
public String Mua(int idSP1, int idSP2) throws RemoteException {
	if (getProdudctByID(idSP1) == null || getProdudctByID(idSP2) == null) {
		return "MA SAN PHAM KHONG TON TAI";
	}
	if (getCountSP(idSP1) == 0 || getCountSP(idSP2) == 0 ) {
		return "kHÔNG ĐỦ SỐ LƯỢNG";
	}
	updateSP(idSP1);
	updateSP(idSP2);
	return "MUA THÀNH CÔNG";
}

}
