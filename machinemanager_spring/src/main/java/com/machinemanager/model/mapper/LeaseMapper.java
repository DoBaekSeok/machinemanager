package com.machinemanager.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.machinemanager.model.dto.Lease;

public interface LeaseMapper {
	
	List<Lease> getLeaseList();

	List<Lease> getPagedLeaseList(HashMap<String, Object> params);

	List<Lease> getUsableLeaseList(HashMap<String, Object> params);

	int getUsableLeaseCount();

	int getLeaseCount();

	void insertLease(Lease lease);

	Lease getLeaseByLeaseNo(int leaseNo);

	void updateLease(Lease lease);

	void deleteLease(int leaseNo);
	
	List<Lease> getLeaseSearchList(HashMap<String, Object> params);
	
	int getLeaseSearchListCount(HashMap<String, Object> params);
	

}