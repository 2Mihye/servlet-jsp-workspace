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
		// jsp 폼에서 전송한 데이터 가지고 오기
		int productID = Integer.parseInt(request.getParameter("productID"));
		String commenterName = request.getParameter("commenterName"); 
		String commentText = request.getParameter("commentText");
		Timestamp commentDate = new Timestamp(System.currentTimeMillis());
		
		// ProductComment에 대한 객체 생성해서 값을 담음
		ProductComment comment = new ProductComment(0, productID, commenterName, commentText, commentDate);
		
		// ProductDAO를 사용하여 댓글 추가
		ProductDAO productDAO = new ProductDAO();
		productDAO.addComment(comment);
		
		// 댓글 추가한 후 상세페이지로 다시 전송하는 redirect 작성해주기
		response.sendRedirect("productDetail.jsp?prdocutID=" + productID);
	}

}
