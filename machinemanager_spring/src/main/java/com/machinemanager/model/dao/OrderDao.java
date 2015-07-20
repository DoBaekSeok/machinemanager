package com.machinemanager.model.dao;

import java.util.List;

import com.machinemanager.model.dto.Order;
import com.machinemanager.model.dto.OrderDetail;

public interface OrderDao {
	
	List<Order> getOrderList();
	
	List<Order> getOrderList(int first, int last);
	
	int getOrderCount();

	int insertOrder(Order order);
	
	void insertOrderDetail(OrderDetail orderDetail);

	Order getOrderByOrderNo(int orderNo);

	void updateOrder(Order order);

	void deleteOrder(int orderNo);
	
	List<Order> getOrderSearchList(String searchType, String searchValue, int first, int last);
	
	int getOrderSearchListCount(String searchType, String searchValue);

	List<Order> getOrderSearchListByCost(String searchType, String searchCostFirstValue,
			String searchCostLastValue, int first, int last);
	
	int getOrderSearchListByCostCount(String searchType, String searchCostFirstValue,
			String searchCostLastValue);

	List<Order> getOrderSearchListByDate(String searchType, String searchDateFirstValue,
			String searchDateLastValue, int first, int last);

	int getOrderSearchListByDateCount(String searchType, String searchDateFirstValue,
			String searchDateLastValue);
}