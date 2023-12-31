<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.kh.product.Product" %>
<%@ page import="com.kh.product.ProductDAO" %>
<%@ page import="com.kh.product.ProductComment" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>제품 상세 페이지</title>
		<%
			String ProductIDPram = request.getParameter("productID");
			Product product = null;
			ArrayList<ProductComment> commentList = null;
			
			//String = id 값을 가지고 오겠다.
			String productIDValue = request.getParameter("productID");
			 if (productIDValue != null) {
				int productID = Integer.parseInt(productIDValue);
				// DAO 작업
				ProductDAO productDao = new ProductDAO();
				product = productDao.getProductID(productID);
				commentList = productDao.getCommentByProductID(product.getProductID());
		%>
	</head>
	<body>
		<h1>제품 상세 정보</h1>
		<p>제품 ID : <%=product.getProductID() %></p>
		<p>제품명 : <%=product.getProductID() %></p>
		<p>카테고리 : <%=product.getProductID() %></p>
		<p>가격 : <%=product.getProductID() %></p>
		<p>지고 수량 : <%=product.getProductID() %></p>
		<a href="update_product.jsp?productID=<%=product.getProductID()%>">제품 수정하기</a>
		<%
        	} else {
    	%>
    	<p>상품을 찾을 수 없습니다..</p>
    	<%
        	}
    	%>
		
		<!-- 댓글 목록 표시 -->
		<h3>댓글 목록</h3>
		<%
			// 만약 댓글이 존재한다면 if
			if(commentList != null){
				for(ProductComment comment : commentList){

		%>
		<p><%= comment.getCommenterName() %>  (<%=comment.getCommentDate() %>) : 
		<!-- 		작성자 이름 				(		작성한 시간			) : 댓글 내용 -->
		<%= comment.getCommentText() %>
		</p>
		<%
				}
			}
		%>
		
		<!-- 댓글 추가 폼 작성! -->
		<form action="AddCommentServlet" method="post">
			<input type="hidden" name="productID" value="<%= product != null ? product.getProductID() %>"><br>
			<label for="commentName">이름 : </label>
			<input type="text" name="commnetName" required><br>
			
			<label for="commentText">댓글 내용 :</label>
			<textarea name="commentText" required></textarea><br>
			
			<input type="submit" value="댓글추가">
		</form>
		
	</body>
	
	
	
	
	
</html>