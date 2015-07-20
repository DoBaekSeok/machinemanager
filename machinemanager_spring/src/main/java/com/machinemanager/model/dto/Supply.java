package com.machinemanager.model.dto;

import java.util.List;

public class Supply {
	private int supplyNo;
	private String supplyName;
	private String supplyAddress;
	private String supplyEmail;
	private String supplyPhone;
	private String supplier;
	private String supplierPhone;
	private String supplyNote;
	private boolean supplyDeleted;
	
	
	//데이터베이스의 Supply와 Orders 테이블 간의 1:Many 관계를 구현한 필드
	private List<Order> orders;
			
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public int getSupplyNo() {
		return supplyNo;
	}
	public void setSupplyNo(int supplyNo) {
		this.supplyNo = supplyNo;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public String getSupplyAddress() {
		return supplyAddress;
	}
	public void setSupplyAddress(String supplyAddress) {
		this.supplyAddress = supplyAddress;
	}
	public String getSupplyEmail() {
		return supplyEmail;
	}
	public void setSupplyEmail(String supplyEmail) {
		this.supplyEmail = supplyEmail;
	}	
	public String getSupplyPhone() {
		return supplyPhone;
	}
	public void setSupplyPhone(String supplyPhone) {
		this.supplyPhone = supplyPhone;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getSupplierPhone() {
		return supplierPhone;
	}
	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
	public String getSupplyNote() {
		return supplyNote;
	}
	public void setSupplyNote(String supplyNote) {
		this.supplyNote = supplyNote;
	}
	public boolean isSupplyDeleted() {
		return supplyDeleted;
	}
	public void setSupplyDeleted(boolean supplyDeleted) {
		this.supplyDeleted = supplyDeleted;
	}
}
