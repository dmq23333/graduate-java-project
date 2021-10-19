package com.store.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.common.pojo.StoreResult;
import com.store.mapper.TbOrderItemMapper;
import com.store.mapper.TbOrderMapper;
import com.store.mapper.TbOrderShippingMapper;
import com.store.pojo.TbOrder;
import com.store.pojo.TbOrderExample;
import com.store.pojo.TbOrderExample.Criteria;
import com.store.pojo.TbOrderItem;
import com.store.pojo.TbOrderItemExample;
import com.store.pojo.TbOrderShipping;
import com.store.rest.service.OrderService;
import com.store.rest.pojo.Order;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	
	@Override
	public StoreResult getOrderListByUserId(long userId) {
		List<Order> orderList = new ArrayList<>();
		//通过用户id查询订单列表
		TbOrderExample example = new TbOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<TbOrder> tbOrderList = orderMapper.selectByExample(example);
		for (TbOrder tbOrder : tbOrderList) {
			List<TbOrderItem> orderItems = new ArrayList<TbOrderItem>();
			//设置查询条件
			TbOrderItemExample example2 = new TbOrderItemExample();
			com.store.pojo.TbOrderItemExample.Criteria criteria2 = example2.createCriteria();
			criteria2.andOrderIdEqualTo(tbOrder.getOrderId());
			orderItems = orderItemMapper.selectByExample(example2);
			Order order = new Order();
			order.setBuyerNick("buyerNick");
			order.setOrderId(tbOrder.getOrderId());
			order.setPayment(tbOrder.getPayment());
			order.setPaymentType(tbOrder.getPaymentType());
			order.setCreateTime(tbOrder.getCreateTime());
			order.setStatus(tbOrder.getStatus());
			order.setOrderItems(orderItems);
			orderList.add(order);
			
		}
		if(orderList.size()>0&&orderList!=null) {
			return StoreResult.ok(orderList);
		}
		return StoreResult.build(400, "该用户无订单");
	}

	@Override
	public StoreResult getOrderByOrderId(String orderId) {
		Order order = new Order();
		TbOrder tbOrder = new TbOrder();
		tbOrder = orderMapper.selectByPrimaryKey(orderId);
		List<TbOrderItem> orderItems = new ArrayList<TbOrderItem>();
		TbOrderItemExample  orderItemExample = new TbOrderItemExample();
		com.store.pojo.TbOrderItemExample.Criteria criteria = orderItemExample.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		orderItems = orderItemMapper.selectByExample(orderItemExample);
		TbOrderShipping orderShipping = orderShippingMapper.selectByPrimaryKey(orderId);
		order.setOrderShipping(orderShipping);
		order.setOrderItems(orderItems);
		order.setPayment(tbOrder.getPayment());
		if(orderItems.size()>0&&orderItems!=null) {
			return StoreResult.ok(order);
		}
 		return StoreResult.build(400, "无该订单信息");
	}

	@Override
	public StoreResult updateOrderComponent(String orderId, String message) {
		TbOrder order = orderMapper.selectByPrimaryKey(orderId);
		order.setBuyerMessage(message);
		order.setUpdateTime(new Date());
		orderMapper.updateByPrimaryKey(order);
		return StoreResult.ok();
	}

}
