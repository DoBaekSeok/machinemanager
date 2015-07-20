package com.machinemanager.model.dao;

import java.util.List;

import com.machinemanager.model.dto.Machine;
import com.machinemanager.model.dto.Setup;

public interface SetupDao {
		
	List<Setup> getSetupList(int first, int last);
		
	int getSetupCount();
	
	Setup getSetupBySetupNo(int setupNo);
	
	void insertSetup(Setup setup);
	
	List<Machine> getUsableMachineList();

	void updateSetup(Setup setup);

	void updateWithdraw(Setup setup);

	List<Setup> getWithdrawList(int first, int last);

	int getWithdrawCount();
	
	Setup getWithdrawBySetupNo(int setupNo);

	void updateReSetup(Setup setup);

	int isExistMachineCount(Setup setup);

	List<Setup> getSetupSearchList(String searchType,
			String searchValue, int first, int last);

	int getSetupSearchListCount(String searchType, String searchValue);

	List<Setup> getSetupSearchListByCost(String searchType, Integer searchCostFirstValue,
			Integer searchCostLastValue, int first, int last);

	int getSetupSearchListByCostCount(String searchType, Integer searchCostFirstValue,
			Integer searchCostLastValue);

	List<Setup> getSetupSearchListByDate(String searchType, String searchDateFirstValue,
			String searchDateLastValue, int first, int last);

	int getSetupSearchListByDateCount(String searchType, String searchDateFirstValue,
			String searchDateLastValue);
	
	List<Setup> getWithdrawSearchList(String searchType,
			String searchValue, int first, int last);
	
	int getWithdrawSearchListCount(String searchType, String searchValue);
	
	List<Setup> getWithdrawSearchListByCost(String searchType, Integer searchCostFirstValue,
			Integer searchCostLastValue, int first, int last);
	
	int getWithdrawSearchListByCostCount(String searchType, Integer searchCostFirstValue,
			Integer searchCostLastValue);
	
	List<Setup> getWithdrawSearchListByDate(String searchType, String searchDateFirstValue,
			String searchDateLastValue, int first, int last);
	
	int getWithdrawSearchListByDateCount(String searchType, String searchDateFirstValue,
			String searchDateLastValue);
}