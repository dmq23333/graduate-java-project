package com.store.rest.service;

import com.store.common.pojo.StoreResult;

public interface OrderService {
	StoreResult getOrderListByUserId(long userId);
	StoreResult getOrderByOrderId(String orderId);
	StoreResult updateOrderComponent(String orderId,String message);
}
