package com.machinemanager.model.dto;

import java.util.List;
import java.sql.Date;

public class MachineStockIO {
	private int machineStockIONo;
	private int machineStockNo;
	private Date machineStockIODate;
	private int machineStockIOCount;
	private String machineStockInputOrOutput;
	
	private int machineNo;
	private String machineModelName;
	private String productName;	 
	
	private List<Setup> setups;
	private List<Machine> machines;
	private List<Product> products;
	
	public List<Setup> getSetups() {
		return setups;
	}

	public void setSetups(List<Setup> setups) {
		this.setups = setups;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public MachineStockIO(){}

	public int getMachineStockIONo() {
		return machineStockIONo;
	}

	public void setMachineStockIONo(int machineStockIONo) {
		this.machineStockIONo = machineStockIONo;
	}

	public int getMachineStockNo() {
		return machineStockNo;
	}

	public void setMachineStockNo(int machineStockNo) {
		this.machineStockNo = machineStockNo;
	}

	public Date getMachineStockIODate() {
		return machineStockIODate;
	}

	public void setMachineStockIODate(Date machineStockIODate) {
		this.machineStockIODate = machineStockIODate;
	}

	public int getMachineStockIOCount() {
		return machineStockIOCount;
	}

	public void setMachineStockIOCount(int machineStockIOCount) {
		this.machineStockIOCount = machineStockIOCount;
	}

	public String getMachineStockInputOrOutput() {
		return machineStockInputOrOutput;
	}

	public void setMachineStockInputOrOutput(String machineStockInputOrOutput) {
		this.machineStockInputOrOutput = machineStockInputOrOutput;
	}

	public int getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(int machineNo) {
		this.machineNo = machineNo;
	}

	public String getMachineModelName() {
		return machineModelName;
	}

	public void setMachineModelName(String machineModelName) {
		this.machineModelName = machineModelName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}