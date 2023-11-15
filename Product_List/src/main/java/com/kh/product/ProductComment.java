package com.kh.product;

import java.sql.Timestamp;

public class ProductComment {
	private int commentID;
	private int productID;
	private String commenterName;
	private String commentText;
	private Timestamp commentDate;
	
	
	// 값을 저장하고 저장된 내용을 보내기 위한 생성자
	
	public ProductComment(int commentID, int productID, String commenterName, String commentText, Timestamp commentDate) {
		this.commentID = commentID;
		this.productID = productID;
		this.commenterName = commenterName;
		this.commentText = commentText;
		this.commentDate = commentDate;
	}
	
	public Timestamp getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public int getCommentID() {
		return commentID;
	}
	public int getProductID() {
		return productID;
	}
	public String getCommenterName() {
		return commenterName;
	}
	public String getCommentText() {
		return commentText;
	}

	
}
