package com.machinemanager.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.machinemanager.model.dto.Machine;
import com.machinemanager.model.dto.Setup;
import com.machinemanager.model.mapper.SetupMapper;

@Repository(value = "setupDao")
public class OracleSetupDao implements SetupDao {
		
	private SetupMapper setupMapper;
	@Autowired
	@Qualifier("setupMapper")
	public void setSetupMapper(SetupMapper setupMapper){
		this.setupMapper = setupMapper;
	}
	
	public List<Setup> getSetupList(int first, int last) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("first", first);
		params.put("last", last);
		
		List<Setup> setups = setupMapper.getSetupList(params);				
				
		return setups;
		
	}

	public int getSetupCount() {
				
		return setupMapper.getSetupCount();
		
	}
	
	public Setup getSetupBySetupNo(int setupNo) {
						
		return setupMapper.getSetupBySetupNo(setupNo);
	}
	
	public void insertSetup(Setup setup) {
		
		setupMapper.insertSetup(setup);
		
	}
	
	public List<Machine> getUsableMachineList() {		
		
		List<Machine> machines = setupMapper.getUsableMachineList();
		
		return machines;
		
	}

	public void updateSetup(Setup setup) {
		
		setupMapper.updateSetup(setup);
		
	}

	public void updateWithdraw(Setup setup) {
		
		setupMapper.updateWithdraw(setup);
		
	}

	public List<Setup> getWithdrawList(int first, int last) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("first", first);
		params.put("last", last);
		
		List<Setup> setups = setupMapper.getWithdrawList(params);				
				
		return setups;
		
	}

	public int getWithdrawCount() {
				
		return setupMapper.getWithdrawCount();
		
	}
	
	public Setup getWithdrawBySetupNo(int setupNo) {
		
		Setup setup = setupMapper.getWithdrawBySetupNo(setupNo);
		
		return setup;
		
	}

	public void updateReSetup(Setup setup) {
		
		setupMapper.updateReSetup(setup);
		
	}

	public int isExistMachineCount(Setup setup) {
		
		return setupMapper.isExistMachineCount(setup);
		
	}

	public List<Setup> getSetupSearchList(String searchType,
			String searchValue, int first, int last) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		params.put("first", first);
		params.put("last", last);
				
		List<Setup> setups = setupMapper.getSetupSearchList(params);	
		
		return setups;
		
	}

	public int getSetupSearchListCount(String searchType, String searchValue) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		
		return setupMapper.getSetupSearchListCount(params);
		
	}

	public List<Setup> getSetupSearchListByCost(String searchType, Integer searchCostFirstValue,
			Integer searchCostLastValue, int first, int last) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchCostFirstValue", searchCostFirstValue);
		params.put("searchCostLastValue", searchCostLastValue);
		params.put("first", first);
		params.put("last", last);
				
		List<Setup> setups = setupMapper.getSetupSearchList(params);	
		
		return setups;
		
	}

	public int getSetupSearchListByCostCount(String searchType, Integer searchCostFirstValue,
			Integer searchCostLastValue) {
				
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchCostFirstValue", searchCostFirstValue);
		params.put("searchCostLastValue", searchCostLastValue);
		
		return setupMapper.getSetupSearchListByCostCount(params);
		
	}

	public List<Setup> getSetupSearchListByDate(String searchType, String searchDateFirstValue,
			String searchDateLastValue, int first, int last) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchDateFirstValue", searchDateFirstValue);
		params.put("searchDateLastValue", searchDateLastValue);
		params.put("first", first);
		params.put("last", last);
				
		List<Setup> setups = setupMapper.getSetupSearchListByDate(params);
		
		return setups;
		
	}

	public int getSetupSearchListByDateCount(String searchType, String searchDateFirstValue,
			String searchDateLastValue) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchDateFirstValue", searchDateFirstValue);
		params.put("searchDateLastValue", searchDateLastValue);
		
		return setupMapper.getSetupSearchListByDateCount(params);
		
	}
	
	public List<Setup> getWithdrawSearchList(String searchType,
			String searchValue, int first, int last) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		params.put("first", first);
		params.put("last", last);
		
		List<Setup> setups = setupMapper.getWithdrawSearchList(params);	
		
		return setups;
		
	}
	
	public int getWithdrawSearchListCount(String searchType, String searchValue) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		
		return setupMapper.getWithdrawSearchListCount(params);
		
	}
	
	public List<Setup> getWithdrawSearchListByCost(String searchType, Integer searchCostFirstValue,
			Integer searchCostLastValue, int first, int last) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchCostFirstValue", searchCostFirstValue);
		params.put("searchCostLastValue", searchCostLastValue);
		params.put("first", first);
		params.put("last", last);
		
		List<Setup> setups = setupMapper.getWithdrawSearchList(params);	
		
		return setups;
		
	}
	
	public int getWithdrawSearchListByCostCount(String searchType, Integer searchCostFirstValue,
			Integer searchCostLastValue) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchCostFirstValue", searchCostFirstValue);
		params.put("searchCostLastValue", searchCostLastValue);
		
		return setupMapper.getWithdrawSearchListByCostCount(params);
		
	}
	
	public List<Setup> getWithdrawSearchListByDate(String searchType, String searchDateFirstValue,
			String searchDateLastValue, int first, int last) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchDateFirstValue", searchDateFirstValue);
		params.put("searchDateLastValue", searchDateLastValue);
		params.put("first", first);
		params.put("last", last);
		
		List<Setup> setups = setupMapper.getWithdrawSearchListByDate(params);
		
		return setups;
		
	}
	
	public int getWithdrawSearchListByDateCount(String searchType, String searchDateFirstValue,
			String searchDateLastValue) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchDateFirstValue", searchDateFirstValue);
		params.put("searchDateLastValue", searchDateLastValue);
		
		return setupMapper.getWithdrawSearchListByDateCount(params);
		
	}
	
}