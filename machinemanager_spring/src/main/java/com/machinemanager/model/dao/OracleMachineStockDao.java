package com.machinemanager.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.machinemanager.model.dto.MachineStock;
import com.machinemanager.model.dto.MachineStockIO;
import com.machinemanager.model.mapper.MachineStockMapper;

@Repository("machineStockDao")
public class OracleMachineStockDao implements MachineStockDao{
	
	private MachineStockMapper machineStockMapper;
	@Autowired
	@Qualifier("machineStockMapper")
	public void setMachineStockMapper(MachineStockMapper machineStockMapper){
		this.machineStockMapper = machineStockMapper;
	}
	
	@Override
	public List<String> getMachineNoList() {
		
		List<String> machineNoList = machineStockMapper.getMachineNoList();
		return machineNoList;
	}
	@Override
	public void inputProduct(int productNo, int setupNo) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("productNo", productNo);
		params.put("setupNo", setupNo);
		machineStockMapper.inputProduct(params);
	}
	@Override
	public void updateMachineStock(int count, int machineStockNo) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("count", count);
		params.put("machineStockNo", machineStockNo);
		machineStockMapper.updateMachineStock(params);
	}
	@Override
	public MachineStock getProductStockByMachineStockNo(int machineStockNo) {
		MachineStock machineStock = machineStockMapper.getProductStockByMachineStockNo(machineStockNo);
		return machineStock;
	}
	@Override
	public MachineStock getProductStockByMachineNoANDProductNo(int machineNo, int productNo) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("machineNo", machineNo);
		params.put("productNo", productNo);
		MachineStock machineStock = machineStockMapper.getProductStockByMachineNoANDProductNo(params);
		return machineStock;
	}
	@Override
	public void inputMachineStockIO(int machineStockIOCount, int machineStockNo) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("machineStockIOCount", machineStockIOCount);
		params.put("machineStockNo", machineStockNo);
		machineStockMapper.inputMachineStockIO(params);
	}
	@Override
	public void outputMachineStockIO(int machineStockIOCount, int machineStockNo) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("machineStockIOCount", machineStockIOCount);
		params.put("machineStockNo", machineStockNo);
		machineStockMapper.outputMachineStockIO(params);
	}
	@Override
	public List<String> getSetupNoList(){
		List<String> machineNoList = machineStockMapper.getSetupNoList();
		return machineNoList;
	}
	@Override
	public List<MachineStock> getMachineStockListByMachineNo(int setupNo) {
		List<MachineStock> machineStockList = machineStockMapper.getMachineStockListByMachineNo(setupNo);
		return machineStockList;
	}
	@Override
	public List<MachineStockIO> getMachineStockIOList(int first, int last) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("first", first);
		params.put("last", last);
		List<MachineStockIO> machineStockIOs = machineStockMapper.getMachineStockIOList(params);		
		return machineStockIOs;				
	}	
	@Override
	public int getMachineStockIOCount() {
		return machineStockMapper.getMachineStockIOCount();	
	}

	@Override
	public List<MachineStockIO> getSearchMachineStockIOList(String searchType,
			String searchValue, int first, int last) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		params.put("first", first);
		params.put("last", last);
		List<MachineStockIO> machineStockIOs = machineStockMapper.getSearchMachineStockIOList(params);
		return machineStockIOs;		
	}

	@Override
	public int getSearchMachineStockIOCount(String searchType, String searchValue) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		return machineStockMapper.getSearchMachineStockIOCount(params);	
	}

	@Override
	public List<MachineStockIO> getSearchMachineStockIOListByCount(
			String searchType, String searchCountFirstValue,
			String searchCountLastValue, int first, int last) {

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchCountFirstValue", searchCountFirstValue);
		params.put("searchCountLastValue", searchCountLastValue);
		params.put("first", first);
		params.put("last", last);
				
		List<MachineStockIO> machineStockIOs = machineStockMapper.getSearchMachineStockIOListByCount(params);	
		
		return machineStockIOs;
		
	}

	@Override
	public int getSearchMachineStockIOListByCountCount(String searchType,
			String searchCountFirstValue, String searchCountLastValue) {

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchCountFirstValue", searchCountFirstValue);
		params.put("searchCountLastValue", searchCountLastValue);
		
		return machineStockMapper.getSearchMachineStockIOListByCountCount(params);
		
	}

	@Override
	public List<MachineStockIO> getSearchMachineStockIOListByDate(
			String searchType, String searchDateFirstValue,
			String searchDateLastValue, int first, int last) {

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchDateFirstValue", searchDateFirstValue);
		params.put("searchDateLastValue", searchDateLastValue);
		params.put("first", first);
		params.put("last", last);
				
		List<MachineStockIO> machineStockIOs = machineStockMapper.getSearchMachineStockIOListByDate(params);	
		
		return machineStockIOs;
		
	}

	@Override
	public int getSearchMachineStockIOListByDateCount(String searchType,
			String searchDateFirstValue, String searchDateLastValue) {

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchDateFirstValue", searchDateFirstValue);
		params.put("searchDateLastValue", searchDateLastValue);
		
		return machineStockMapper.getSearchMachineStockIOListByDateCount(params);
		
	}	
	
}