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
			String memail = request.getParameter("memail"); // �α��� �� �� ���̵� ������ ����� ��
			String mno = request.getParameter("mno"); // �α��� �� �� ��й�ȣ ������ ����� �ѹ�
			
			// select���� ����Ͽ� ��ġ�ϴ� ������ �����ϴ��� Ȯ��
			String sql = "SELECT * FROM MemberInfo WHERE MEmail =? AND Mno =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memail);
			preparedStatement.setString(2, mno);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// �α����� �ϴ� ������ 1���̱⶧���� if���� Ȱ���Ͽ� �ϳ��� ������ ��ȸ�Ѵ�.
			if(resultSet.next()) {
				// ��ġ�ϴ� ���� �����ϸ� �α��� ������ ���� ������ ������ �´�.
				// HTTP Session : ������ Ŭ���̾�Ʈ�� ���� ���� ������ �����ϰ� �����ϴ� �� ����Ѵ�.
				
				// ���� http ��û�� ���� ������ ������ ��
				// ó�� http�� session ��û�� �� �� ������ ������ ���ο� ���� ����, �̹� ������ �����Ѵٸ� �ش� ���� ��ȯ
				HttpSession session = request.getSession();
				session.setAttribute("mno", resultSet.getInt("MNO"));
				session.setAttribute("mname", resultSet.getString("MName"));
				session.setAttribute("memail", memail);
				session.setAttribute("mbirth", resultSet.getDate("MBirth"));
				
				// �α��� �ð��� 30������ ����
				session.setMaxInactiveInterval(1800); // 1800 = 30��
				
				// �α��ο� �����ϸ� ������ ���� login_success�� ��������
				response.sendRedirect("login_success.jsp");
			} else { // ���� �α����� �� �ȴٸ� 
				// ���� �������� ������ �α��� ����
				request.setAttribute("loginError", "true");//�Ӽ��� �α��� ������� �̸����� �Ӽ��� �����ϰ�, �α��ο����� true�� �����ؼ� �α��� ���� �߻������� ��Ÿ���� �Ӽ��̸��� �Ӽ����� �߰�
				request.getRequestDispatcher("home.jsp").forward(request, response);
				// request.getRequestDispatcher("���") : �츮�� ������ ��η� �̵��ϱ� ���� ��ü ��ȯ
				// forward(request, response) : ���� �������� ������ �ߴܵ�
				// ���ݱ��� ������ �ִ� �����͸� Ŭ���̾�Ʈ���� �������� ���� �� ����� ǥ����
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
