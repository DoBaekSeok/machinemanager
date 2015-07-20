package com.machinemanager.model.dto;

import java.io.Serializable;
import java.util.List;



public class MachineStock implements Serializable {	
	private static final long serialVersionUID = 1L;
	private int machineStockNo;
	private int setupNo;
	private int productNo;
	private String productName;
	private int productSalePrice;
	private int machineStockRemain;	
	private int machineStockCount;
	private int machineStockMaxCount;
	
	private List<Product> products;
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public MachineStock(){}
	
	public int getProductSalePrice() {
		return productSalePrice;
	}

	public void setProductSalePrice(int productSalePrice) {
		this.productSalePrice = productSalePrice;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	
	public int getMachineStockCount() {
		return machineStockCount;
	}

	public void setMachineStockCount(int machineStockCount) {
		this.machineStockCount = machineStockCount;
	}

	public int getMachineStockNo() {
		return machineStockNo;
	}

	public void setMachineStockNo(int machineStockNo) {
		this.machineStockNo = machineStockNo;
	}

	public int getSetupNo() {
		return setupNo;
	}

	public void setSetupNo(int setupNo) {
		this.setupNo = setupNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getMachineStockRemain() {
		return machineStockRemain;
	}

	public void setMachineStockRemain(int machineStockRemain) {
		this.machineStockRemain = machineStockRemain;
	}

	public int getMachineStockMaxCount() {
		return machineStockMaxCount;
	}

	public void setMachineStockMaxCount(int machineStockMaxCount) {
		this.machineStockMaxCount = machineStockMaxCount;
	}
}
