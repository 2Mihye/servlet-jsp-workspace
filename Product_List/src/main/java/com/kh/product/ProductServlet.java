package com.kh.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String userName = "KHBANK";
	private static final String password = "KHBANK";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����ͺ��̽� ����
		Connection connection = null;
		try {
			Class.forName("oracle. jdbc.OracleDriver");// Class.forName("oracle.jdbc.driver.OracleDriver); ���� ���� ����ǳ� ������ ����� �����̶� �� ������ ���� �� �� ����.
			connection = DriverManager.getConnection(jdbcURL, userName, password);
			// SQL ����
			String sql = "SELECT * FROM products";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet =  ps.executeQuery();
			
			// ��ǰ ����� ������ ��ٱ��ϰ��� ArrayList ����
			ArrayList<Product> productList = new ArrayList<>();
			
			while(resultSet.next()) {
				int productID = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				String category = resultSet.getString("category");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stockQuantity");
				
				Product product = new Product(productID, productName, category, price, stockQuantity);
					// productList�� ��ǰ���� �ϳ��� add�ؼ� �־���
				productList.add(product);			
			}
			
			// JSP ��ǰ��� �������� ��ǰ ����� �������� !
			request.setAttribute("productList", productList);
			request.getRequestDispatcher("/productList.jsp").forward(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
