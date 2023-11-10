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
@MultipartConfig // 파일을 올리기 전에 파일 값을 설정하는 공간
/* 
 * @MultipartConfig ( 
 * 					  fileSizeThreshold= 1024 * 1024, // 파일 데이터를 디스크에 기록을 시작하기 전에 메모리에 보유되는 최대 크기(1MB)
 * 					  maxFileSize = 1024 * 1024 * 5, // 업로드할 파일의 최대 크기 = 1024 * 1024 * 5 (5MB)
 * 					  maxRequestSiz = 1024 * 1024 * 10, // 요청 데이터의 최대 크기 = 1024 * 1024 * 10 (10MB)
 * 					  location="/tmp" // 파일이 디스크에 저장될 임시 디렉터리
 * )
 */
public class UploadServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUser = "KHBANK";
		String jdbcPassword = "KHBANK";
		
		/*
		 String title = request.getParameter("title"); // 사용자가 요청한 폼 데이터를 처리하는 데 사용하는 문장으로 jsp에서 title이라는 파라미터를 가져와서
		 													title이라는 문자열 변수에 저장한 후 폼에서 입력된 제목을 나타냄.
		 */
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Part imagePart = request.getPart("image"); // 이미지는 글자가 아니라 픽셀로 이루어진 part라서 Part를 써서 import 시켜서 사용함
		
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
			//		+ "VALUES (시퀀스 이름.nextval, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setBinaryStream(3, imagePart.getInputStream(), (int)imagePart.getSize());
			ps.setTimestamp(4, new Timestamp(new Date().getTime())); // Date는 util import / Timestamp는 sql import
			ps.setString(5, "author name");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("imageList.jsp");
		
	}

}
