package com.machinemanager.model.dto;

import java.util.List;

public class Order {
	private int orderNo;
	private int supplyNo;	
	private int orderTotalPrice;
	private String orderDate;
	private String orderer;
	private String supplyName; // 주문 목록에 보여질 단일 거래처명
	
	//주문과 상세주문 사이 1:M 관계를 위해 설정
	private List<OrderDetail> orderDetail;
	
	//주문 거래처 사이 1:M 관계를 위해 설정
	private List<Supply> supply;

	
	public List<Supply> getSupply() {
		return supply;
	}

	public void setSupply(List<Supply> supply) {
		this.supply = supply;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getSupplyNo() {
		return supplyNo;
	}

	public void setSupplyNo(int supplyNo) {
		this.supplyNo = supplyNo;
	}

	public int getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(int orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderer() {
		return orderer;
	}

	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	
	
}
