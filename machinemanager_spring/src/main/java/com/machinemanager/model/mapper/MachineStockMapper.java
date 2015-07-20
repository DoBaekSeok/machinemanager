package com.machinemanager.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.machinemanager.model.dto.Lease;
import com.machinemanager.model.dto.MachineStock;
import com.machinemanager.model.dto.MachineStockIO;



public interface MachineStockMapper {
	
	List<String> getMachineNoList(); 
	
	void inputProduct(HashMap<String, Object> params);
	
	void updateMachineStock(HashMap<String, Object> params);
	
	MachineStock getProductStockByMachineStockNo(int machineStockNo);
	
	MachineStock getProductStockByMachineNoANDProductNo(HashMap<String, Object> params);

	void inputMachineStockIO(HashMap<String, Object> params); 
	
	void outputMachineStockIO(HashMap<String, Object> params); 
	
	List<String> getSetupNoList();
	
	List<MachineStock> getMachineStockListByMachineNo(int setupNo); 

	List<MachineStockIO> getMachineStockIOList(HashMap<String, Object> params); 
	
	int getMachineStockIOCount(); 
	
	List<MachineStockIO> getSearchMachineStockIOList(HashMap<String, Object> params);
		
	int getSearchMachineStockIOCount(HashMap<String, Object> params);
	
	List<MachineStockIO> getSearchMachineStockIOListByCount(HashMap<String, Object> params);
	
	int getSearchMachineStockIOListByCountCount(HashMap<String, Object> params);

	List<MachineStockIO> getSearchMachineStockIOListByDate(HashMap<String, Object> params);
	
	int getSearchMachineStockIOListByDateCount(HashMap<String, Object> params);	

}
