package com.kh.imageUpload;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/UploadServlet")
@MultipartConfig // ������ �ø��� ���� ���� ���� �����ϴ� ����
/* 
 * @MultipartConfig ( 
 * 					  fileSizeThreshold= 1024 * 1024, // ���� �����͸� ��ũ�� ����� �����ϱ� ���� �޸𸮿� �����Ǵ� �ִ� ũ��(1MB)
 * 					  maxFileSize = 1024 * 1024 * 5, // ���ε��� ������ �ִ� ũ�� = 1024 * 1024 * 5 (5MB)
 * 					  maxRequestSiz = 1024 * 1024 * 10, // ��û �������� �ִ� ũ�� = 1024 * 1024 * 10 (10MB)
 * 					  location="/tmp" // ������ ��ũ�� ����� �ӽ� ���͸�
 * )
 */
public class UploadServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUser = "KHBANK";
		String jdbcPassword = "KHBANK";
		
		/*
		 String title = request.getParameter("title"); // ����ڰ� ��û�� �� �����͸� ó���ϴ� �� ����ϴ� �������� jsp���� title�̶�� �Ķ���͸� �����ͼ�
		 													title�̶�� ���ڿ� ������ ������ �� ������ �Էµ� ������ ��Ÿ��.
		 */
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Part imagePart = request.getPart("image"); // �̹����� ���ڰ� �ƴ϶� �ȼ��� �̷���� part�� Part�� �Ἥ import ���Ѽ� �����
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
			String sql = "INSERT INTO board (board_id, title, content, image, created_at, author)"
					+ "VALUES (board_sequence.nextval, ?, ?, ?, ?, ?)";
			//String sql = "INSERT INTO board (board_id, title, content, image, created_at, author)"
			//		+ "VALUES (������ �̸�.nextval, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setBinaryStream(3, imagePart.getInputStream(), (int)imagePart.getSize());
			ps.setTimestamp(4, new Timestamp(new Date().getTime())); // Date�� util import / Timestamp�� sql import
			ps.setString(5, "author name");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("imageList.jsp");
		
	}

}
