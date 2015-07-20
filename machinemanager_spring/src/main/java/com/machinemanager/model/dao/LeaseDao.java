package com.machinemanager.model.dao;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.machinemanager.model.dto.Lease;
import com.machinemanager.model.mapper.LeaseMapper;

@Component(value = "leaseDao")
public class LeaseDao implements Lease_Dao {

	private LeaseMapper leaseMapper;

	@Autowired
	@Qualifier("leaseMapper")
	public void setLeaseMapper(LeaseMapper leaseMapper) {
		this.leaseMapper = leaseMapper;
	}

	public List<Lease> getLeaseList() {

		List<Lease> leases = leaseMapper.getLeaseList();

		return leases;
	}

	public List<Lease> getleaseList(int first, int last) {

		HashMap<String, Object> params = new HashMap<>();
		params.put("first", first);
		params.put("last", last);

		List<Lease> leases = leaseMapper.getPagedLeaseList(params);

		return leases;
	}

	public List<Lease> getUsableLeaseList(int first, int last) {

		HashMap<String, Object> params = new HashMap<>();
		params.put("first", first);
		params.put("last", last);

		List<Lease> leases = leaseMapper.getUsableLeaseList(params);

		return leases;
	}

	public int getUsableLeaseCount() {

		return leaseMapper.getUsableLeaseCount();

	}

	public int getLeaseCount() {

		return leaseMapper.getLeaseCount();
	}

	public void insertLease(Lease lease) {

		leaseMapper.insertLease(lease);
	}

	public Lease getLeaseByLeaseNo(int leaseNo) {

		return leaseMapper.getLeaseByLeaseNo(leaseNo);
	}

	public void updateLease(Lease lease) {

		leaseMapper.updateLease(lease);

	}

	public void deleteLease(int leaseNo) {

		leaseMapper.deleteLease(leaseNo);

	}
	
	public List<Lease> getLeaseSearchList(String searchType, String searchValue, int first, int last) {
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		params.put("first", first);
		params.put("last", last);
		
		List<Lease> leases = leaseMapper.getLeaseSearchList(params);	
		
		return leases;
		
	}
	
	public int getLeaseSearchListCount(String searchType, String searchValue) {
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
				
		return leaseMapper.getLeaseSearchListCount(params);
	}
	

}

