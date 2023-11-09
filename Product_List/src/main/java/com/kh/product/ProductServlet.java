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
		// 데이터베이스 연결
		Connection connection = null;
		try {
			Class.forName("oracle. jdbc.OracleDriver");// Class.forName("oracle.jdbc.driver.OracleDriver); 위와 같이 실행되나 위쪽이 개편된 문장이라 윗 문장을 쓰는 게 더 좋음.
			connection = DriverManager.getConnection(jdbcURL, userName, password);
			// SQL 쿼리
			String sql = "SELECT * FROM products";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet =  ps.executeQuery();
			
			// 제품 목록을 저장할 장바구니같은 ArrayList 생성
			ArrayList<Product> productList = new ArrayList<>();
			
			while(resultSet.next()) {
				int productID = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				String category = resultSet.getString("category");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stockQuantity");
				
				Product product = new Product(productID, productName, category, price, stockQuantity);
					// productList에 제품들을 하나씩 add해서 넣어줌
				productList.add(product);			
			}
			
			// JSP 제품목록 페이지로 제품 목록을 전달하자 !
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
