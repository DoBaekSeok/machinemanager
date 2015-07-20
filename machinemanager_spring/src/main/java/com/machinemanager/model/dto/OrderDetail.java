package com.machinemanager.model.dto;

public class OrderDetail {
	
	
	private int productNo;		//제품 일련번호
	private String productName;	//제품 명
	private int orderNo;		//주문 일련번호
	private int orderDetailCount;
	private int orderDetailPrice;
	private String orderStatus;
	
	private String productMeasure; //제품단위
	
	public String getProductMeasure() {
		return productMeasure;
	}
	public void setProductMeasure(String productMeasure) {
		this.productMeasure = productMeasure;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getOrderDetailCount() {
		return orderDetailCount;
	}
	public void setOrderDetailCount(int orderDetailCount) {
		this.orderDetailCount = orderDetailCount;
	}
	public int getOrderDetailPrice() {
		return orderDetailPrice;
	}
	public void setOrderDetailPrice(int orderDetailPrice) {
		this.orderDetailPrice = orderDetailPrice;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
