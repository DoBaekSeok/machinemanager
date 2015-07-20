package com.machinemanager.model.dto;

import java.sql.Date;
import java.util.List;

public class Product {
	
	private int productNo;	  				//제품번호
	private String productType;				//제품유형
	private String productName;				//제품명
	private String productCompany;			//제조회사
	private String productExpirationDate;	//유통기한
	private int productPrice;				//원가
	private int productSalePrice;			//판매가
	private boolean productUse;				//사용여부
	private int productCount;				//수량
	private String productMeasure;			//단위
	
	private List<ProductStock> productStocks;
	private List<OrderDetail> orderDetails;
	
	public List<ProductStock> getProductStocks() {
		return productStocks;
	}
	public void setProductStocks(List<ProductStock> productStocks) {
		this.productStocks = productStocks;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
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
	public String getProductExpirationDate() {
		return productExpirationDate;
	}
	public void setProductExpirationDate(String productExpirationDate) {
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
	public boolean isProductUse() {
		return productUse;
	}
	public void setProductUse(boolean productUse) {
		this.productUse = productUse;
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
	
}