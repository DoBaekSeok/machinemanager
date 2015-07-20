package com.machinemanager.model.dto;

import java.io.Serializable;
import java.util.List;

public class Setup implements Serializable{
	
	private int setupNo;
	private int machineNo;
	private int leaseNo;
	private String leaseName;
	private String setupDate;
	private int setupCost;
	private String setupStatus;
	private String withdrawDate;
	private String withdrawReason;	
	
	private List<MachineStock> machineStocks;
	
	public Setup(){}

	public int getSetupNo() {
		return setupNo;
	}

	public void setSetupNo(int setupNo) {
		this.setupNo = setupNo;
	}

	public int getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(int machineNo) {
		this.machineNo = machineNo;
	}

	public int getLeaseNo() {
		return leaseNo;
	}

	public void setLeaseNo(int leaseNo) {
		this.leaseNo = leaseNo;
	}

	public String getLeaseName() {
		return leaseName;
	}

	public void setLeaseName(String leaseName) {
		this.leaseName = leaseName;
	}

	public String getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(String setupDate) {
		this.setupDate = setupDate;
	}

	public int getSetupCost() {
		return setupCost;
	}

	public void setSetupCost(int setupCost) {
		this.setupCost = setupCost;
	}

	public String getSetupStatus() {
		return setupStatus;
	}

	public void setSetupStatus(String setupStatus) {
		this.setupStatus = setupStatus;
	}

	public String getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(String withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

	public String getWithdrawReason() {
		return withdrawReason;
	}

	public void setWithdrawReason(String withdrawReason) {
		this.withdrawReason = withdrawReason;
	}

	public List<MachineStock> getMachineStocks() {
		return machineStocks;
	}

	public void setMachineStocks(List<MachineStock> machineStocks) {
		this.machineStocks = machineStocks;
	}	
}