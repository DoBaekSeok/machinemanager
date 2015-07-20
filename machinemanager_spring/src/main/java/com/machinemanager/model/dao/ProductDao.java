package com.machinemanager.model.dao;

import java.util.List;

import com.machinemanager.model.dto.Product;
import com.machinemanager.model.dto.Supply;

public interface ProductDao {
	
	void insertProduct(Product product);
	
	List<Product> getProductList();
	
	List<Product> getProductList(int first, int last);
	
	List<Product> getProductList(String searchType, String searchValue, int first, int last);
	
	int getProductCount();
	
	int getProductSearchCount(String searchType, String searchValue);
	
	Product getproductByProductNo(int productNo);
	
	void updateProduct(Product product);
	
	void deleteproduct(int productNo);
	
}
