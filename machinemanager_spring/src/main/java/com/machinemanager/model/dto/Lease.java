package com.machinemanager.model.dto;

import java.io.Serializable;

public class Lease implements Serializable{
	private int leaseNo;
	private String leaseName;
	private String leaseAddress;
	private int leaseCost;
	private String leaseDate;
	private String leaseExpirationDate;
	private String lessor;
	private String lessorPhone;
	private String leaseNote;
	private String leaseEmail;
	private boolean leaseDeleted;
	
	public boolean isLeaseDeleted() {
		return leaseDeleted;
	}

	public void setLeaseDeleted(boolean leaseDeleted) {
		this.leaseDeleted = leaseDeleted;
	}

	public String getLeaseDate() {
		return leaseDate;
	}

	public void setLeaseDate(String leaseDate) {
		this.leaseDate = leaseDate;
	}

	public String getLeaseExpirationDate() {
		return leaseExpirationDate;
	}

	public void setLeaseExpirationDate(String leaseExpirationDate) {
		this.leaseExpirationDate = leaseExpirationDate;
	}

	public Lease(){}

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

	public String getLeaseAddress() {
		return leaseAddress;
	}

	public void setLeaseAddress(String leaseAddress) {
		this.leaseAddress = leaseAddress;
	}

	public int getLeaseCost() {
		return leaseCost;
	}

	public void setLeaseCost(int leaseCost) {
		this.leaseCost = leaseCost;
	}

	

	public String getLessor() {
		return lessor;
	}

	public void setLessor(String lessor) {
		this.lessor = lessor;
	}

	public String getLessorPhone() {
		return lessorPhone;
	}

	public void setLessorPhone(String lessorPhone) {
		this.lessorPhone = lessorPhone;
	}

	public String getLeaseNote() {
		return leaseNote;
	}

	public void setLeaseNote(String leaseNote) {
		this.leaseNote = leaseNote;
	}

	public String getLeaseEmail() {
		return leaseEmail;
	}

	public void setLeaseEmail(String leaseEmail) {
		this.leaseEmail = leaseEmail;
	}

	
}