package com.kh;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "KHBANK";
		String password = "KHBANK";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, userName, password);
			//login
			String memail = request.getParameter("memail"); // 로그인 할 때 아이디 값으로 사용할 것
			String mno = request.getParameter("mno"); // 로그인 할 때 비밀번호 값으로 사용할 넘버
			
			// select문을 사용하여 일치하는 유저가 존재하는지 확인
			String sql = "SELECT * FROM MemberInfo WHERE MEmail =? AND Mno =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memail);
			preparedStatement.setString(2, mno);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// 로그인을 하는 유저는 1명이기때문에 if문을 활용하여 하나의 유저만 조회한다.
			if(resultSet.next()) {
				// 일치하는 값이 존재하면 로그인 성공에 따라 정보를 가지고 온다.
				// HTTP Session : 웹에서 클라이언트와 서버 간에 정보를 유지하고 공유하는 데 사용한다.
				
				// 현재 http 요청에 대한 세션을 가지고 옴
				// 처음 http에 session 요청이 올 때 세션이 없으면 새로운 세션 생성, 이미 세션이 존재한다면 해당 세션 소환
				HttpSession session = request.getSession();
				session.setAttribute("mno", resultSet.getInt("MNO"));
				session.setAttribute("mname", resultSet.getString("MName"));
				session.setAttribute("memail", memail);
				session.setAttribute("mbirth", resultSet.getDate("MBirth"));
				
				// 로그인 시간을 30분으로 설정
				session.setMaxInactiveInterval(1800); // 1800 = 30분
				
				// 로그인에 성공하면 성공에 대한 login_success에 전달해줌
				response.sendRedirect("login_success.jsp");
			} else { // 만약 로그인이 안 된다면 
				// 값이 존재하지 않으면 로그인 실패
				request.setAttribute("loginError", "true");//속성에 로그인 에러라는 이름으로 속성을 저장하고, 로그인에러가 true로 설정해서 로그인 오류 발생했음을 나타내는 속성이름과 속성값을 추가
				request.getRequestDispatcher("home.jsp").forward(request, response);
				// request.getRequestDispatcher("경로") : 우리가 설정한 경로로 이동하기 위한 객체 반환
				// forward(request, response) : 현재 페이지에 실행이 중단됨
				// 지금까지 가지고 있는 데이터를 클라이언트에게 응답으로 보낸 후 결과를 표시함
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
