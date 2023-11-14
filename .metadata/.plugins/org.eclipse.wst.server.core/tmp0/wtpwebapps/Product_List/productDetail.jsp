<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.kh.product.Product" %>
<%@ page import="com.kh.product.ProductDAO" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>제품 상세 페이지</title>
	</head>
	<body>
		<h1>제품 상세 정보</h1>
		<%
			//String = id 값을 가지고 오겠다.
			String productIDValue = request.getParameter("productID");
			int productID = Integer.parseInt(productIDValue);
			// DAO 작업
			ProductDAO productDao = new ProductDAO();
			Product product = productDao.getProductID(productID);
		%>
		<p>제품 ID : <%=product.getProductID() %></p>
		<p>제품명 : <%=product.getProductID() %></p>
		<p>카테고리 : <%=product.getProductID() %></p>
		<p>가격 : <%=product.getProductID() %></p>
		<p>지고 수량 : <%=product.getProductID() %></p>
		<a href="update_product.jsp?productID=<%=product.getProductID()%>">제품 수정하기</a>
	</body>
	
	
	
	
	
</html>