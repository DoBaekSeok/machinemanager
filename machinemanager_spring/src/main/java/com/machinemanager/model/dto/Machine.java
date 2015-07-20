package com.machinemanager.model.dto;


import java.io.Serializable;

public class Machine implements Serializable {	
	private static final long serialVersionUID = 1L;
	private int machineNo;
	private String machineModelName;
	private int machineWeight;
	private int machineWidth;
	private int machineDepth;
	private int machineHeight;
	private int machinePowerConsumption;
	private String machineCompany;
	private String machineType;	
		
	private String setupStatus;
	
	public Machine(){}

	public int getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(int machineNo) {
		this.machineNo = machineNo;
	}

	public int getMachineWeight() {
		return machineWeight;
	}

	public void setMachineWeight(int machineWeight) {
		this.machineWeight = machineWeight;
	}

	public int getMachineWidth() {
		return machineWidth;
	}

	public void setMachineWidth(int machineWidth) {
		this.machineWidth = machineWidth;
	}

	public int getMachineDepth() {
		return machineDepth;
	}

	public void setMachineDepth(int machineDepth) {
		this.machineDepth = machineDepth;
	}

	public int getMachineHeight() {
		return machineHeight;
	}

	public void setMachineHeight(int machineHeight) {
		this.machineHeight = machineHeight;
	}

	public int getMachinePowerConsumption() {
		return machinePowerConsumption;
	}

	public void setMachinePowerConsumption(int machinePowerConsumption) {
		this.machinePowerConsumption = machinePowerConsumption;
	}

	public String getMachineCompany() {
		return machineCompany;
	}

	public void setMachineCompany(String machineCompany) {
		this.machineCompany = machineCompany;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getSetupStatus() {
		return setupStatus;
	}

	public void setSetupStatus(String setupStatus) {
		this.setupStatus = setupStatus;
	}

	public String getMachineModelName() {
		return machineModelName;
	}

	public void setMachineModelName(String machineModelName) {
		this.machineModelName = machineModelName;
	}		
}