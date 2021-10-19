package com.store.order.service;

import java.util.List;

import com.store.common.pojo.StoreResult;
import com.store.pojo.TbOrder;
import com.store.pojo.TbOrderItem;
import com.store.pojo.TbOrderShipping;

public interface OrderService {

	StoreResult creatOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
	StoreResult getOrderInfoById(String orderId);
	StoreResult getOrderInfoByPage(long userId, int page, int count);
	List<TbOrder> getOrderInfoByUserId(long userId);
	StoreResult changeOrderStatus(String orderId,int status);
}
