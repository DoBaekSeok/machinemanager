package com.machinemanager.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.machinemanager.model.dto.Machine;
import com.machinemanager.model.mapper.MachineMapper;

@Repository(value = "machineDao")
public class OracleMachineDao implements MachineDao {
		
	private MachineMapper machineMapper;
	@Autowired
	@Qualifier("machineMapper")
	public void setMachineMapper(MachineMapper machineMapper){
		this.machineMapper = machineMapper;
	}
	
	public List<Machine> getMachineList() {		
		
		List<Machine> machines = machineMapper.getMachineList();				
		
		return machines;
		
	}

	public List<Machine> getMachineList(int first, int last) {
				
		HashMap<String, Object> params = new HashMap<>();
		params.put("first", first);
		params.put("last", last);
		
		List<Machine> machines = machineMapper.getMachineListByPage(params);
		
		return machines;
	}

	public int getMachineCount() {
				
		return machineMapper.getMachineCount();
	}	
	
	public String getSetupStatusByMachineNo(int machineNo) {
		
		return machineMapper.getSetupStatusByMachineNo(machineNo);
		
	}

	public void insertMachine(Machine machine) {
		
		machineMapper.insertMachine(machine);
		
	}

	public Machine getMachineByMachineNo(int machineNo) {
		
		Machine machine = machineMapper.getMachineByMachineNo(machineNo);
		
		return machine;
		
	}

	public void updateMachine(Machine machine) {
		
		machineMapper.updateMachine(machine);

	}

	public void deleteMachine(int machineNo) {
		
		machineMapper.deleteMachine(machineNo);
		
	}
	
	public List<Machine> getUsableMachineList(int first, int last) {
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("first", first);
		params.put("last", last);
		
		List<Machine> machines = machineMapper.getUsableMachineList(params);
		
		return machines;
	}

	public int getUsableMachineCount() {
						
		return machineMapper.getUsableMachineCount();
		
	}
	
	public List<Machine> getMachineSearchList(String searchType, String searchValue, int first, int last) {
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		params.put("first", first);
		params.put("last", last);
		
		List<Machine> machines = machineMapper.getMachineSearchList(params);		
		
		return machines;
		
	}
	
	public int getMachineSearchListCount(String searchType, String searchValue) {
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
				
		return machineMapper.getMachineSearchListCount(params);
	}
}