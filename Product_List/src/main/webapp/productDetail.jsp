<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.kh.product.Product" %>
<%@ page import="com.kh.product.ProductDAO" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>��ǰ �� ������</title>
	</head>
	<body>
		<h1>��ǰ �� ����</h1>
		<%
			//String = id ���� ������ ���ڴ�.
			String productIDValue = request.getParameter("productID");
			int productID = Integer.parseInt(productIDValue);
			// DAO �۾�
			ProductDAO productDao = new ProductDAO();
			Product product = productDao.getProductID(productID);
		%>
		<p>��ǰ ID : <%=product.getProductID() %></p>
		
	</body>
	
	
	
	
	
</html>