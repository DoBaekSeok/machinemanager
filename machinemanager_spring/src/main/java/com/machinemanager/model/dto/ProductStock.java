package com.machinemanager.model.dto;

import java.util.List;

public class ProductStock {
	private String productStockDate;	//입고일시
	private int productNo;	//제품번호
	private int orderNo;	//주문번호
	private int productStockNo;	//재고번호
	private int productCount;
	
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	private List<Product> products;
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getProductStockDate() {
		return productStockDate;
	}
	public void setProductStockDate(String productStockDate) {
		this.productStockDate = productStockDate;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getProductStockNo() {
		return productStockNo;
	}
	public void setProductStockNo(int productStockNo) {
		this.productStockNo = productStockNo;
	}	
}