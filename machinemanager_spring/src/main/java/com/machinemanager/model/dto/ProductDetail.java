package com.machinemanager.model.dto;

import java.sql.Date;

public class ProductDetail {
	
	private int productNo;	//제품번호
	private int productStockNo;	//재고번호
	private int orderNo;	//주문번호
	private String productName;	//제품명
	private String productCompany;	//제조회사
	private String productType;	//제품유형
	private Date productExpirationDate;	//유통기한
	private int productPrice;	//원가
	private int productSalePrice;	//판매가
	private int productCount;	//수량
	private String productMeasure;	//단위
	private Date productStockDate;	//입고일시
	
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public void setProductStockNo(int productStockNo) {
		this.productStockNo = productStockNo;
	}
	public int getProductStockNo() {
		return productStockNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCompany() {
		return productCompany;
	}
	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Date getProductExpirationDate() {
		return productExpirationDate;
	}
	public void setProductExpirationDate(Date productExpirationDate) {
		this.productExpirationDate = productExpirationDate;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductSalePrice() {
		return productSalePrice;
	}
	public void setProductSalePrice(int productSalePrice) {
		this.productSalePrice = productSalePrice;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public String getProductMeasure() {
		return productMeasure;
	}
	public void setProductMeasure(String productMeasure) {
		this.productMeasure = productMeasure;
	}
	public Date getProductStockDate() {
		return productStockDate;
	}
	public void setProductStockDate(Date productStockDate) {
		this.productStockDate = productStockDate;
	}
	
	
}
