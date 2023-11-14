package com.kh.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateProductSerlvet")
public class UpdateProductSerlvet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
		
        
        Product updateProduct = new Product(productID, productName, category, price, stockQuantity);
        ProductDAO pDAO = new ProductDAO();
        pDAO.updateProduct(updateProduct);
        
        // ���� �Ŀ� ��ǰ�� ���������� �ٽ� �������ֱ�
        response.sendRedirect("productDetail.jsp?productID="+productID);
        
	}

}
