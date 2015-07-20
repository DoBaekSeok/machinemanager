package com.machinemanager.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.machinemanager.common.ConnectionHelper;
import com.machinemanager.model.dto.OrderDetail;
import com.machinemanager.model.dto.Product;
import com.machinemanager.model.dto.ProductDetail;
import com.machinemanager.model.dto.ProductStock;

public interface ProductStockDao {
	
	public List<Product> getProductStockList(int first, int last); 
	
	public List<ProductDetail> getProductDetailList(int productNo, int start, int last);
	
	public void stockConsume(int stockNo, int productCount);
	
	public int getProductStockCount();
	
	public List<Product> getProductPriceSearch(String priceType, String priceOption, int price, int first, int last); 
	
	public int getProductPriceSearchCount(String priceType, String priceOption, int price);

	public List<Product> getProductSearch(String searchType, String searchValue, int first, int last); 
	
	public int getProductSearchCount(String searchtype, String searchvalue); 
	
	public void getStockDelete(int stockno);
	
	public void insertProductStock(int productNo, int orderNo, int count); 
	
	public List<OrderDetail> getOrderDetailList(int first, int last); 
	
	public int getOrderDetailCount();
	
	public void updateOrderStatus(int orderNo, int productNo); 
	
	public ProductStock getProductStockByProductNo(int productno); 

	public int getStockCount(int productstockno); 

	public List<OrderDetail> getOrderDetailSearch(String searchType, String searchValue, int first, int last);
	
	public int getOrderDetailSearchCount(String searchType, String searchValue); 
	
	public int getProductDetailStockCount(int productNo);

}
