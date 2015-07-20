package com.machinemanager.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.machinemanager.model.dto.Supply;

public interface SupplyMapper {
	
	void insertSupply(Supply supply);
	
	List<Supply> getSupplyList();
	
	List<Supply> getSupplyListByPage(HashMap<String, Integer> params);
	
	List<Supply> getSupplyListBySearchPage(HashMap<String, Object> params);
	
	int getSupplyCount();
	
	int getSupplySearchCount(HashMap<String, Object> params);
	
	Supply getSupplyBySupplyNo(int supplyNo);
	
	void updateSupply(Supply supply);
	
	void deleteSupply(int supplyNo);
		
}
