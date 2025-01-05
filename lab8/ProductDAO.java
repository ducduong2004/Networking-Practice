package lab8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

	@Override
	public boolean add(Product product) {
		Connection conn = UCanAccessConnector.getConnection();
		String sql = "INSERT INTO sanpham(idsp,name,count,price) VALUES(?,?,?,?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, product.getIdsp());
			preparedStatement.setString(2, product.getTen_san_pham());
			preparedStatement.setInt(3, product.getSo_luong());
			preparedStatement.setDouble(4, product.getGia_ban());
			int affectedRow = preparedStatement.executeUpdate();
			if (affectedRow > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean remove(String idsp) {
		Connection conn = UCanAccessConnector.getConnection();
		String sql = "DELETE FROM sanpham WHERE idsp = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, idsp);
			int affectedRow = preparedStatement.executeUpdate();
			if (affectedRow > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean edit(Product product) {
		Connection conn = UCanAccessConnector.getConnection();
		String sql = "UPDATE sanpham SET name = ? ,count =?,price = ? WHERE idsp= ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, product.getTen_san_pham());
			preparedStatement.setInt(2, product.getSo_luong());
			preparedStatement.setDouble(3, product.getGia_ban());
			preparedStatement.setString(4, product.getIdsp());
			int affectedRow = preparedStatement.executeUpdate();
			if (affectedRow > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> view(String productName) {
		List<Product> products = new ArrayList<Product>();
		Connection conn = UCanAccessConnector.getConnection();
		String sql = "SELECT * FROM sanpham WHERE name like ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + productName + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				products.add(new Product(resultSet.getString("idsp"), resultSet.getString("name"),
						resultSet.getInt("count"), resultSet.getDouble("price")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	public static void main(String[] args) {
		IProductDAO productDAO = new ProductDAO();
		Product p1 = new Product("sp4", "Dien thoai Google Pixel 6", 200, 10000000);
		System.out.println(productDAO.add(p1));
		System.out.println(productDAO.view("Google"));
	}

}
