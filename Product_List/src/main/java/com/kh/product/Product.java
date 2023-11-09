package com.kh.product;

public class Product {
	/*
	 
	*/
	private int productID;
	private String productName;
	private String category;
	private double price;
	private int stockQuantity;
	
	public Product(int productID, String productName, String category, double price, int stockQuantity) {
		this.productID = productID;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
	
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getProductID() {
		return productID;
	}
	public String getProductName() {
		return productName;
	}
	public String getCategory() {
		return category;
	}
	public double getPrice() {
		return price;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
}
