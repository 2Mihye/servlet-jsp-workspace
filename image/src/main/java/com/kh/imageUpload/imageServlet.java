package com.kh.imageUpload;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/imageServlet")
public class imageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUser = "KHBANK";
		String jdbcPassword = "KHBANK";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
			
			// sql작성
			String sql = "SELECT image FROM board";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				// Result image 해당하는 데이터를 가지고 와서 blob에다 저장
				// blob을 쓴 이유는 우리가 image 데이터를 sql에서 blob에 저장한다고 지정해줬기 때문에 blob객체를 가지고 온 것
				Blob blob = result.getBlob("image");
				// blob같은 경우 파일이기 때문에 파일을 쪼개고 쪼개서 byte 타입으로 읽은 다음 배열에 읽은 byte들을 모두 저장한 것
				// getBytes(1, (int)blob.length()) : 1부터 끝까지 가지고 온다는 의미
				// getBytes(시작, 종료)
				byte[] imageData = blob.getBytes(1, (int)blob.length());// byte 장바구니 안에 이미지를 불러오는 것
				response.setContentType("image/jpg");
				// setContentType("image/jpeg") : 파일 형식이 image임을 나타냄
				response.getOutputStream().write(imageData); // 이미지 받아온 걸 출력해서 보여줘야하므로 outputstream
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
