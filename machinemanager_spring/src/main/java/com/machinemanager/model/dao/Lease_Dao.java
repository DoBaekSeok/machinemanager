package com.machinemanager.model.dao;

import java.util.List;

import com.machinemanager.model.dto.Lease;
import com.machinemanager.model.dto.Machine;

public interface Lease_Dao {

	List<Lease> getLeaseList();

	List<Lease> getleaseList(int first, int last);

	List<Lease> getUsableLeaseList(int first, int last);

	int getUsableLeaseCount();

	int getLeaseCount();

	void insertLease(Lease lease);

	Lease getLeaseByLeaseNo(int leaseNo);

	void updateLease(Lease lease);

	void deleteLease(int leaseNo);
	
	List<Lease> getLeaseSearchList(String searchType, String searchValue, int first, int last);
	
	int getLeaseSearchListCount(String searchType, String searchValue);
}
