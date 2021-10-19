package com.store.service;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;

public interface OrderService {
	StoreResult deleteOrder(String[] orderId);
	StoreResult consignOrder(String orderId,String shippingName,String shippingCode);
	EUDataGridResult getOrderList(int page, int rows);
	StoreResult getOrderItem(String orderId);
}
