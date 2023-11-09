package com.kh.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String userName = "KHBANK";
	private static final String password = "KHBANK";
	
	public ProductDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<>();
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, userName, password);
			String sql = "SELECT * FROM products";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				int productID = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				String category = resultSet.getString("category");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stock_quantity");
				
				Product product = new Product(productID, productName, category, price, stockQuantity);
				products.add(product);
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}

	public Product getProductID(int productsID) {
		Product product = null;
		// select�ؼ� �ϳ��� �� �� �ִ� ���� �ۼ��ϰ� new Product �̿��ؼ� �� ��������
		
		String selectSql = "SELECT * FROM products WHERE productID = ?";
		Connection connection;
		try {
			connection = DriverManager.getConnection(jdbcURL, userName, password);
			PreparedStatement ps = connection.prepareStatement(selectSql);
			
			ps.setInt(1,productsID);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next()) {
				int productID = resultSet.getInt("productID");
				String productName = resultSet.getString("productName");
				String category = resultSet.getString("category");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stockQuantity");
				
				product = new Product (productID, productName, category, price, stockQuantity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return product;
	}
}