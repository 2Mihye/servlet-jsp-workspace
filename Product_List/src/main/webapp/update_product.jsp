<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.product.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>제품 상세 정보 수정하기</title>
	</head>
	<body>
		<%
		String productId = request.getParameter("productID");
		int productID = Integer.parseInt(productId);
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.getProductID(productID);
		%>
		<!-- 수정할 폼 작성 -->
		<form action="UpdateProductServlet" method="post">
				<input type="hidden" name="productID" value="<%=product.getProductID()%>"><br>
				
				<p>제품명 : <input type="text" name="productName" value="<%=product.getProductName()%>"></p><br>
				<p>카테고리 : <input type="text" name="category" value="<%=product.getCategory()%>"></p><br>
				<p>가격 : <input type="text" name="price" value="<%=product.getPrice()%>"></p><br>
				<p>재고수량 : <input type="text" name="stockQunatity" value="<%=product.getStockQuantity()%>"></p><br>
		</form>
	</body>
</html>