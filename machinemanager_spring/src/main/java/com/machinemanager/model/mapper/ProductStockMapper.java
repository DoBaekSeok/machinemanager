package com.machinemanager.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.machinemanager.model.dto.Lease;
import com.machinemanager.model.dto.MachineStock;
import com.machinemanager.model.dto.MachineStockIO;
import com.machinemanager.model.dto.OrderDetail;
import com.machinemanager.model.dto.Product;
import com.machinemanager.model.dto.ProductDetail;
import com.machinemanager.model.dto.ProductStock;



public interface ProductStockMapper {
	
	public List<Product> getProductStockList(HashMap<String, Object> params); 
	
	public List<ProductDetail> getProductDetailList(HashMap<String, Object> params);
	
	public void stockConsume(HashMap<String, Object> params);
	
	public void stockConsumeDeleted(int stockNo);
	
	public int getProductStockCount();
	
	public List<Product> getProductPriceSearch(HashMap<String, Object> params); 
	
	public List<Product> getProductSumCountSearch(HashMap<String, Object> params);
	
	public int getProductPriceSearchCount(HashMap<String, Object> params);

	public int getProductSumCountSearchCount(HashMap<String, Object> params);
	
	public List<Product> getProductSearch(HashMap<String, Object> params); 
	
	public int getProductSearchCount(HashMap<String, Object> params); 
	
	public void getStockDelete(int stockno);
	
	public void insertProductStock(HashMap<String, Object> params); 
	
	public List<OrderDetail> getOrderDetailList(HashMap<String, Object> params); 
	
	public int getOrderDetailCount();
	
	public void updateOrderStatus(HashMap<String, Object> params); 
	
	public ProductStock getProductStockByProductNo(int productno); 

	public int getStockCount(int productstockno); 

	public List<OrderDetail> getOrderDetailSearch(HashMap<String, Object> params);
	
	public int getOrderDetailSearchCount(HashMap<String, Object> params); 

	public int getProductDetailStockCount(int productNo);
	
}
