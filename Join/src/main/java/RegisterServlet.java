

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// JDBC불러오기
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUsername = "KHBANK";
		String jdbcPassword = "KHBANK";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			// 넣고자하는 DB 값 설정
			/*
			 CREATE TABLE MemberInfo (
			    MNO INT PRIMARY KEY,
			    MName VARCHAR(50),
			    MEmail VARCHAR(100),
			    MBirth  DATE
			 );
			 */
			int mno = Integer.parseInt(request.getParameter("mno"));
			String mname = request.getParameter("mname");
			String memail = request.getParameter("memail");
			String mbirth = request.getParameter("mbirth");
			
			// 회원가입 insert
			String sql = "INSERT INTO MemberInfo (MNO, MNAME, MEMAIL,MBIRTH) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, mno);
			preparedStatement.setString(2, mname);
			preparedStatement.setString(3, memail);
			preparedStatement.setDate(4, Date.valueOf(mbirth));
			
			preparedStatement.executeUpdate();
			
			/* 
			 * 가입 성공할 경우 회원 정보를 세션에 저장
			 * 세션 객체를 통해 클라이언트와 서버간에 전송할 데이터를 저장하고 공유하는 작업을 수행함
			 * request : 현재 클라이언트의 요청에 대한 정보를 제공하는 역할
			 * session이란? 웹이나 애플리케이션 상태를 유지하고 데이터를 저장하기 위해 사용
			 * getSession() : request에서 현재 세션을 가지고 옴
			 * setAttribute("mno", mno) : 세션에 데이터를 저장하는 메서드로 "mno"라는 이름으로 데이터를 저장하고 mno변수값이 해당 데이터로 설정되도록 함
			   이렇게 저장된 데이터는 다른 서블릿이나 jsp 페이지에서 접근이 가능함
			*/
			request.getSession().setAttribute("mno", mno);
			request.getSession().setAttribute("mname", mname);
			request.getSession().setAttribute("memail", memail);
			request.getSession().setAttribute("mbirth", mbirth);
			
			// 성공할 경우 이동할 페이지 설정해주고 다시 전송
			response.sendRedirect("register_success.jsp");
			
		} catch (SQLException e) {
			// 실패할 경우 이동할 페이지 설정
			response.sendRedirect("register_error.jsp");
			e.printStackTrace();
		}
	}

}
