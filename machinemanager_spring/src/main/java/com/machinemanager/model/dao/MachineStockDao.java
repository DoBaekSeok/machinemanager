package com.machinemanager.model.dao;

import java.util.List;

import com.machinemanager.model.dto.MachineStock;
import com.machinemanager.model.dto.MachineStockIO;

public interface MachineStockDao {

	public List<String> getMachineNoList(); 
	
	public void inputProduct(int productNo, int setupNo);
	
	public void updateMachineStock(int count, int machineStockNo);
	
	public MachineStock getProductStockByMachineStockNo(int machineStockNo);
	
	public MachineStock getProductStockByMachineNoANDProductNo(int machineNo, int productNo);

	public void inputMachineStockIO(int machineStockIOCount, int machineStockNo); 
	
	public void outputMachineStockIO(int machineStockIOCount, int machineStockNo); 
	
	public List<String> getSetupNoList();
	
	public List<MachineStock> getMachineStockListByMachineNo(int setupNo); 

	public List<MachineStockIO> getMachineStockIOList(int first, int last); 
	
	public int getMachineStockIOCount();

	public List<MachineStockIO> getSearchMachineStockIOList(String searchType,
			String searchValue, int first, int last);

	public int getSearchMachineStockIOCount(String searchType, String searchValue);

	public List<MachineStockIO> getSearchMachineStockIOListByCount(
			String searchType, String searchCountFirstValue,
			String searchCountLastValue, int first, int last);

	public int getSearchMachineStockIOListByCountCount(String searchType,
			String searchCountFirstValue, String searchCountLastValue);

	public List<MachineStockIO> getSearchMachineStockIOListByDate(
			String searchType, String searchDateFirstValue,
			String searchDateLastValue, int first, int last);

	public int getSearchMachineStockIOListByDateCount(String searchType,
			String searchDateFirstValue, String searchDateLastValue);
	
}