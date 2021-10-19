package com.store.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.portal.pojo.Order;

public interface OrderService {
	String createOrder(Order order);
	List<Order> getOrderListByUserId(
			HttpServletRequest request,HttpServletResponse response);
	Order getOrderByOrderId(String orderId,
			HttpServletRequest request,HttpServletResponse response);
}
