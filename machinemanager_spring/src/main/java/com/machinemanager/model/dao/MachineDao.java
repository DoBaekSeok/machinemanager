package com.machinemanager.model.dao;

import java.util.List;

import com.machinemanager.model.dto.Machine;

public interface MachineDao {
	
	List<Machine> getMachineList();
	
	List<Machine> getMachineList(int first, int last);
	
	int getMachineCount();
	
	String getSetupStatusByMachineNo(int machineNo);

	void insertMachine(Machine machine);

	Machine getMachineByMachineNo(int machineNo);

	void updateMachine(Machine machine);

	void deleteMachine(int machineNo);
	
	List<Machine> getUsableMachineList(int first, int last);

	int getUsableMachineCount();
	
	List<Machine> getMachineSearchList(String searchType, String searchValue, int first, int last);
	
	int getMachineSearchListCount(String searchType, String searchValue);
	
}