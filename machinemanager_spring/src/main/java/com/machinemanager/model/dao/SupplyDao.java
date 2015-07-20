package com.machinemanager.model.dao;

import java.util.List;

import com.machinemanager.model.dto.Supply;

public interface SupplyDao {
	
	void insertSupply(Supply supply);
	
	List<Supply> getSupplyList();
	
	List<Supply> getSupplyList(int first, int last);
	
	List<Supply> getSupplyList(String searchType, String searchValue, int first, int last);
	
	int getSupplyCount();
	
	int getSupplySearchCount(String searchType, String searchValue);
	
	Supply getSupplyBySupplyNo(int supplyNo);
	
	void updateSupply(Supply supply);
	
	void deleteSupply(int supplyNo);
}
