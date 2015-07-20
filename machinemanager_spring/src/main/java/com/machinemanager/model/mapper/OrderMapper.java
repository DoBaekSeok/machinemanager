package com.machinemanager.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.machinemanager.model.dto.Order;
import com.machinemanager.model.dto.OrderDetail;

public interface OrderMapper {
	
	List<Order> getOrderList();
	
	List<Order> getOrderListByPage(HashMap<String, Object> params);
	
	int getOrderCount();
	
	int insertOrder(Order order);
	
	void insertOrderDetail(OrderDetail orderDetail);

	Order getOrderByOrderNo(int orderNo);

	void updateOrder(Order order);

	void deleteOrder(int orderNo);
	
	List<Order> getOrderSearchList(HashMap<String, Object> params);
	
	int getOrderSearchListCount(HashMap<String, Object> params);

	List<Order> getOrderSearchListByCost(HashMap<String, Object> params);

	int getOrderSearchListByCostCount(HashMap<String, Object> params);

	List<Order> getOrderSearchListByDate(HashMap<String, Object> params);

	int getOrderSearchListByDateCount(HashMap<String, Object> params);
}