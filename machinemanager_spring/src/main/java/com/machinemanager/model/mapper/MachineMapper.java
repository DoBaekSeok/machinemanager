package com.machinemanager.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.machinemanager.model.dto.Machine;

public interface MachineMapper {
	
	List<Machine> getMachineList();
	
	List<Machine> getMachineListByPage(HashMap<String, Object> params);
	
	int getMachineCount();
	
	String getSetupStatusByMachineNo(int machineNo);

	void insertMachine(Machine machine);

	Machine getMachineByMachineNo(int machineNo);

	void updateMachine(Machine machine);

	void deleteMachine(int machineNo);
	
	List<Machine> getUsableMachineList(HashMap<String, Object> params);

	int getUsableMachineCount();
	
	List<Machine> getMachineSearchList(HashMap<String, Object> params);
	
	int getMachineSearchListCount(HashMap<String, Object> params);

}