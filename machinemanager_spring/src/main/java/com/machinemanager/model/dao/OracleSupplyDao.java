package com.machinemanager.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.machinemanager.model.dto.Supply;
import com.machinemanager.model.mapper.SupplyMapper;

@Repository(value = "supplyDao")
public class OracleSupplyDao implements SupplyDao {
	
	private SupplyMapper supplyMapper;
	@Autowired
	@Qualifier("supplyMapper")
	public void setSupplyMapper(SupplyMapper supplyMapper) {
		this.supplyMapper = supplyMapper;
	}

	public void insertSupply(Supply supply) {
		supplyMapper.insertSupply(supply);
	}
	
	public List<Supply> getSupplyList() {
		
		List<Supply> supplys = supplyMapper.getSupplyList();
		
		return supplys;
		
	}
	
	public List<Supply> getSupplyList(int first, int last) {
		
		HashMap<String, Integer> params = new HashMap<String, Integer>();
		params.put("first", first);
		params.put("last", last);
		
		List<Supply> supplys = supplyMapper.getSupplyListByPage(params);
		
		return supplys;
	}
	
	public List<Supply> getSupplyList(String searchType, String searchValue, int first, int last) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchtype", searchType);
		params.put("searchvalue", searchValue);
		params.put("first", first);
		params.put("last", last);
		
		List<Supply> supplys = supplyMapper.getSupplyListBySearchPage(params);
				
		return supplys;
	}
	
	//전체 목록에서 조회할 데이터의 시작위치, 끝위치
	public int getSupplyCount() {
		
		int count = supplyMapper.getSupplyCount();
		
		return count;
	}
	
	public int getSupplySearchCount(String searchType, String searchValue) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchtype", searchType);
		params.put("searchvalue", searchValue);
		
		int count = supplyMapper.getSupplySearchCount(params);
		
		return count;
	}
	
	public Supply getSupplyBySupplyNo(int supplyNo) {
		
		Supply supply = supplyMapper.getSupplyBySupplyNo(supplyNo);
			
		return supply;
	}
	
	public void updateSupply(Supply supply) {
		
		supplyMapper.updateSupply(supply);
		
	}
	
	public void deleteSupply(int supplyNo) {
		
		supplyMapper.deleteSupply(supplyNo);
		
	}
}
