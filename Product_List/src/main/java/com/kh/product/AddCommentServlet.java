package com.kh.product;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp ������ ������ ������ ������ ����
		int productID = Integer.parseInt(request.getParameter("productID"));
		String commenterName = request.getParameter("commenterName"); 
		String commentText = request.getParameter("commentText");
		Timestamp commentDate = new Timestamp(System.currentTimeMillis());
		
		// ProductComment�� ���� ��ü �����ؼ� ���� ����
		ProductComment comment = new ProductComment(0, productID, commenterName, commentText, commentDate);
		
		// ProductDAO�� ����Ͽ� ��� �߰�
		ProductDAO productDAO = new ProductDAO();
		productDAO.addComment(comment);
		
		// ��� �߰��� �� ���������� �ٽ� �����ϴ� redirect �ۼ����ֱ�
		response.sendRedirect("productDetail.jsp?prdocutID=" + productID);
	}

}
