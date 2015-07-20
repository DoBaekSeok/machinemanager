package com.machinemanager.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.machinemanager.common.ConnectionHelper;
import com.machinemanager.model.dto.OrderDetail;
import com.machinemanager.model.dto.Product;
import com.machinemanager.model.dto.ProductDetail;
import com.machinemanager.model.dto.ProductStock;
import com.machinemanager.model.mapper.MachineStockMapper;
import com.machinemanager.model.mapper.ProductStockMapper;

@Repository("productStockDao")
public class OracleProductStockDao implements ProductStockDao{
	
	private ProductStockMapper productStockMapper;
	@Autowired
	@Qualifier("productStockMapper")
	public void setProductStockMapper(ProductStockMapper productStockMapper){
		this.productStockMapper = productStockMapper;
	}
	@Override
	public List<Product> getProductStockList(int first, int last) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("first", first);
		params.put("last", last);
		List<Product> lists = productStockMapper.getProductStockList(params);
		return lists;
	}
	@Override
	public List<ProductDetail> getProductDetailList(int productNo, int first, int last) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("productNo", productNo);
		params.put("first", first);
		params.put("last", last);	
		List<ProductDetail> lists = productStockMapper.getProductDetailList(params);
		return lists;
	}
	
	@Override
	public void stockConsume(int productStockNo, int productCount) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("productStockNo", productStockNo);
		params.put("productCount", productCount);	
		productStockMapper.stockConsume(params);

		if(productCount == 0){
			productStockMapper.stockConsumeDeleted(productStockNo);
		}
		
	}
	@Override
	public int getProductStockCount() {
		int count = productStockMapper.getProductStockCount();
		return count;	
	}
	@Override
	public List<Product> getProductPriceSearch(String priceType, String priceOption, int price, int first, int last) {

		String option = null;
		
		if(priceOption.equals("up")){
			option = ">=";
		}
		if(priceOption.equals("down")){
			option = "<=";
		}
		if(priceOption.equals("equal")){
			option = "=";
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("priceType", priceType);
		params.put("option", option);
		params.put("price", price);
		params.put("first", first);
		params.put("last", last);
					
		List<Product> list = null;
		if(priceType.equals("productCount")){
			list = productStockMapper.getProductSumCountSearch(params);
		}else{
			list = productStockMapper.getProductPriceSearch(params);
		}	
		return list;
	}
	@Override
	public int getProductPriceSearchCount(String priceType, String priceOption, int price) {
		
		String option = null;
		if(priceOption.equals("up")){
			option = ">=";
		}
		if(priceOption.equals("down")){
			option = "<=";
		}
		if(priceOption.equals("equal")){
			option = "=";
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("priceType", priceType);
		params.put("option", option);
		params.put("price", price);
			
		int count = 0;
		if(priceType.equals("productCount")){
			count = productStockMapper.getProductSumCountSearchCount(params);
		}else{
			count = productStockMapper.getProductPriceSearchCount(params);
		}	
		
		return count;
	}
	
	@Override	
	public List<Product> getProductSearch(String searchType, String searchValue, int first, int last) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		params.put("first", first);
		params.put("last", last);
		List<Product> list = productStockMapper.getProductSearch(params);
		return list;
	}

	@Override
	public int getProductSearchCount(String searchType, String searchValue) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		int count = productStockMapper.getProductSearchCount(params);
		return count;
	}
	
	
	@Override	
	public void getStockDelete(int ProductStockNo) {
		productStockMapper.getStockDelete(ProductStockNo);
	}
	@Override
	public void insertProductStock(int productNo, int orderNo, int productCount) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("productNo", productNo);
		params.put("orderNo", orderNo);
		params.put("productCount", productCount);
		productStockMapper.insertProductStock(params);
	}
	@Override
	public List<OrderDetail> getOrderDetailList(int first, int last) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("first", first);
		params.put("last", last);
		List<OrderDetail> list = productStockMapper.getOrderDetailList(params);
		return list;
	}
	@Override
	public int getOrderDetailCount() {
		int count = productStockMapper.getOrderDetailCount();
		return count;
	}
	@Override
	public void updateOrderStatus(int orderNo, int productNo) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("orderNo", orderNo);
		params.put("productNo", productNo);
		productStockMapper.updateOrderStatus(params);
	}
	@Override
	public ProductStock getProductStockByProductNo(int productNo) {	
		ProductStock productStock = productStockMapper.getProductStockByProductNo(productNo);
		return productStock;
	}
	@Override
	public int getStockCount(int productStockNo) {
		int count = productStockMapper.getStockCount(productStockNo);
		return count;	
	}
	@Override
	public List<OrderDetail> getOrderDetailSearch(String searchType, String searchValue, int first, int last) {

		if(searchType.equals("orderNo") || searchType.equals("orderDetailCount") || searchType.equals("orderStatus")){
			searchType = "od." + searchType;
		}else{
			searchType = "p." + searchType;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		params.put("first", first);
		params.put("last", last);
		
		List<OrderDetail> list = productStockMapper.getOrderDetailSearch(params);
		return list;
	}
	@Override
	public int getOrderDetailSearchCount(String searchType, String searchValue) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		int count = productStockMapper.getOrderDetailSearchCount(params);
		return count;
	}
	
	@Override
	public int getProductDetailStockCount(int productNo){
		int count = productStockMapper.getProductDetailStockCount(productNo);
		return count;
	}
	
}
