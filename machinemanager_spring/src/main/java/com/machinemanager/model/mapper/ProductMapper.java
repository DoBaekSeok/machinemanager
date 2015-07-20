package com.machinemanager.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.machinemanager.model.dto.Product;

public interface ProductMapper {
	
	void insertProduct(Product product);
	
	List<Product> getProductList();
	
	List<Product> getProductListByPage(HashMap<String, Integer> params);
	
	List<Product> getProductListBySearchPage(HashMap<String, Object> params);
	
	int getProductCount();
	
	int getProductSearchCount(HashMap<String, Object> params);
	
	Product getproductByProductNo(int productNo);
	
	void updateProduct(Product product);
	
	void deleteproduct(int productNo);
	
	
}
