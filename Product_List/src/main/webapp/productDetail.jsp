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
		<title>��ǰ �� ������</title>
		<%
			String ProductIDPram = request.getParameter("productID");
			Product product = null;
			ArrayList<ProductComment> commentList = null;
			
			//String = id ���� ������ ���ڴ�.
			String productIDValue = request.getParameter("productID");
			 if (productIDValue != null) {
				int productID = Integer.parseInt(productIDValue);
				// DAO �۾�
				ProductDAO productDao = new ProductDAO();
				product = productDao.getProductID(productID);
				commentList = productDao.getCommentByProductID(product.getProductID());
		%>
	</head>
	<body>
		<h1>��ǰ �� ����</h1>
		<p>��ǰ ID : <%=product.getProductID() %></p>
		<p>��ǰ�� : <%=product.getProductID() %></p>
		<p>ī�װ� : <%=product.getProductID() %></p>
		<p>���� : <%=product.getProductID() %></p>
		<p>���� ���� : <%=product.getProductID() %></p>
		<a href="update_product.jsp?productID=<%=product.getProductID()%>">��ǰ �����ϱ�</a>
		<%
        	} else {
    	%>
    	<p>��ǰ�� ã�� �� �����ϴ�..</p>
    	<%
        	}
    	%>
		
		<!-- ��� ��� ǥ�� -->
		<h3>��� ���</h3>
		<%
			// ���� ����� �����Ѵٸ� if
			if(commentList != null){
				for(ProductComment comment : commentList){

		%>
		<p><%= comment.getCommenterName() %>  (<%=comment.getCommentDate() %>) : 
		<!-- 		�ۼ��� �̸� 				(		�ۼ��� �ð�			) : ��� ���� -->
		<%= comment.getCommentText() %>
		</p>
		<%
				}
			}
		%>
		
		<!-- ��� �߰� �� �ۼ�! -->
		<form action="AddCommentServlet" method="post">
			<input type="hidden" name="productID" value="<%= product != null ? product.getProductID() %>"><br>
			<label for="commentName">�̸� : </label>
			<input type="text" name="commnetName" required><br>
			
			<label for="commentText">��� ���� :</label>
			<textarea name="commentText" required></textarea><br>
			
			<input type="submit" value="����߰�">
		</form>
		
	</body>
	
	
	
	
	
</html>