package com.machinemanager.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.machinemanager.model.dto.Order;
import com.machinemanager.model.dto.OrderDetail;
import com.machinemanager.model.mapper.OrderMapper;

@Repository(value = "orderDao")
public class OracleOrderDao implements OrderDao {

	private OrderMapper orderMapper;
	@Autowired
	@Qualifier("orderMapper")
	public void setOrderMapper(OrderMapper orderMapper){
		this.orderMapper = orderMapper;
	}

	@Override
	public List<Order> getOrderList() {
		List<Order> orders = orderMapper.getOrderList();
		return orders;
	}

	@Override
	public List<Order> getOrderList(int first, int last) {
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("first", first);
		params.put("last", last);
		
		List<Order> orders = orderMapper.getOrderListByPage(params);
		
		return orders;
	}

	@Override
	public int getOrderCount() {
		
		return orderMapper.getOrderCount();
	}

	@Override
	public int insertOrder(Order order) {
		
		orderMapper.insertOrder(order);
		System.out.println("Mapper에서 가져온 order값"+order.getOrderNo());
		return order.getOrderNo();
		
	}
	
		
	@Override
	public void insertOrderDetail(OrderDetail orderDetail) {
		
		orderMapper.insertOrderDetail(orderDetail);
	}
	
	@Override
	public Order getOrderByOrderNo(int orderNo) {
		
		Order order = orderMapper.getOrderByOrderNo(orderNo);
		
		return order;
	}

	@Override
	public void updateOrder(Order order) {
		
		orderMapper.updateOrder(order);
	}

	@Override
	public void deleteOrder(int orderNo) {
		
		orderMapper.deleteOrder(orderNo);
		
	}

	@Override
	public List<Order> getOrderSearchList(String searchType,
			String searchValue, int first, int last) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		params.put("first", first);
		params.put("last", last);
				
		List<Order> orders = orderMapper.getOrderSearchList(params);	
		
		return orders;
	}

	@Override
	public int getOrderSearchListCount(String searchType, String searchValue) {

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchValue", searchValue);
		
		return orderMapper.getOrderSearchListCount(params);
	}

	@Override
	public List<Order> getOrderSearchListByCost(String searchType,
			String searchCostFirstValue, String searchCostLastValue, int first,
			int last) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchCostFirstValue", searchCostFirstValue);
		params.put("searchCostLastValue", searchCostLastValue);
		params.put("first", first);
		params.put("last", last);
				
		List<Order> orders = orderMapper.getOrderSearchList(params);	
		
		return orders;
	}

	@Override
	public int getOrderSearchListByCostCount(String searchType,
			String searchCostFirstValue, String searchCostLastValue) {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchCostFirstValue", searchCostFirstValue);
		params.put("searchCostLastValue", searchCostLastValue);
		
		return orderMapper.getOrderSearchListByCostCount(params);
	}

	@Override
	public List<Order> getOrderSearchListByDate(String searchType,
			String searchDateFirstValue, String searchDateLastValue, int first,
			int last) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchDateFirstValue", searchDateFirstValue);
		params.put("searchDateLastValue", searchDateLastValue);
		params.put("first", first);
		params.put("last", last);
				
		List<Order> orders = orderMapper.getOrderSearchListByDate(params);
		
		return orders;
	}

	@Override
	public int getOrderSearchListByDateCount(String searchType,
			String searchDateFirstValue, String searchDateLastValue) {

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchDateFirstValue", searchDateFirstValue);
		params.put("searchDateLastValue", searchDateLastValue);
		
		return orderMapper.getOrderSearchListByDateCount(params);
	}



}