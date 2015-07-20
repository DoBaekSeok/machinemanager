package com.machinemanager.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.machinemanager.model.dto.Product;
import com.machinemanager.model.mapper.ProductMapper;

@Repository(value="productDao")
public class OracleProductDao implements ProductDao{
	
	private ProductMapper productMapper;
	@Autowired
	@Qualifier("productMapper")
	public void setProductMapper(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	public void insertProduct(Product product) {
		
		productMapper.insertProduct(product);
	}
	
	public List<Product> getProductList() {
		
		List<Product> products = productMapper.getProductList();
		
		return products;
	}
	
	public List<Product> getProductList(int first, int last) {
		
		HashMap<String, Integer> params = new HashMap<String, Integer>();
		params.put("first", first);
		params.put("last", last);
		
		List<Product> products = productMapper.getProductListByPage(params);
		
		return products;
	}
	
	public List<Product> getProductList(String searchType, String searchValue, int first, int last) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchtype", searchType);
		params.put("searchvalue", searchValue);
		params.put("first", first);
		params.put("last", last);
		
		List<Product> products = productMapper.getProductListBySearchPage(params);
		
		return products;
	}

	public int getProductCount() {
				
		int count = productMapper.getProductCount();
		
		return count;
	}
	
	public int getProductSearchCount(String searchType, String searchValue) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchtype", searchType);
		params.put("searchvalue", searchValue);
		
		int count = productMapper.getProductSearchCount(params);
		
		return count;
	}
	
	
	public Product getproductByProductNo(int productNo) {
		
		Product product = productMapper.getproductByProductNo(productNo);
	
		return product;
	}
	
	public void updateProduct(Product product) {
		
		productMapper.updateProduct(product);
	}

	public void deleteproduct(int productNo) {
	
		productMapper.deleteproduct(productNo);
	}

	
}
