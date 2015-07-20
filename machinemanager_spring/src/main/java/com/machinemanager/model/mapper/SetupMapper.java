package com.machinemanager.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.machinemanager.model.dto.Machine;
import com.machinemanager.model.dto.Setup;

public interface SetupMapper {
	
	List<Setup> getSetupList(HashMap<String, Object> params);
	
	int getSetupCount();
	
	Setup getSetupBySetupNo(int setupNo);
	
	void insertSetup(Setup setup);

	List<Machine> getUsableMachineList();

	void updateSetup(Setup setup);

	void updateWithdraw(Setup setup);

	List<Setup> getWithdrawList(HashMap<String, Object> params);

	int getWithdrawCount();

	Setup getWithdrawBySetupNo(int setupNo);

	void updateReSetup(Setup setup);

	int isExistMachineCount(Setup setup);

	List<Setup> getSetupSearchList(HashMap<String, Object> params);

	int getSetupSearchListCount(HashMap<String, Object> params);

	List<Setup> getSetupSearchListByCost(HashMap<String, Object> params);

	int getSetupSearchListByCostCount(HashMap<String, Object> params);

	List<Setup> getSetupSearchListByDate(HashMap<String, Object> params);

	int getSetupSearchListByDateCount(HashMap<String, Object> params);
	
	List<Setup> getWithdrawSearchList(HashMap<String, Object> params);
	
	int getWithdrawSearchListCount(HashMap<String, Object> params);
	
	List<Setup> getWithdrawSearchListByCost(HashMap<String, Object> params);
	
	int getWithdrawSearchListByCostCount(HashMap<String, Object> params);
	
	List<Setup> getWithdrawSearchListByDate(HashMap<String, Object> params);
	
	int getWithdrawSearchListByDateCount(HashMap<String, Object> params);

}