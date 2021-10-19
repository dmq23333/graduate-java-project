package com.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.JsonUtils;
import com.store.common.pojo.StoreResult;
import com.store.mapper.TbOrderItemMapper;
import com.store.mapper.TbOrderMapper;
import com.store.pojo.TbOrder;
import com.store.pojo.TbOrderExample;
import com.store.pojo.TbOrderItem;
import com.store.pojo.TbOrderItemExample;
import com.store.pojo.TbOrderItemExample.Criteria;
import com.store.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;

	@Override
	public StoreResult deleteOrder(String[] orderId) {
		//订单交易关闭 
		for (String string : orderId) {
			TbOrder order= orderMapper.selectByPrimaryKey(string);
			order.setStatus(6);
			order.setCloseTime(new Date());
			order.setUpdateTime(new Date());
			orderMapper.updateByPrimaryKey(order);
		}
		
		return StoreResult.ok();
	}

	@Override
	public StoreResult consignOrder(String orderId,String shippingName, String shippingCode) {
		TbOrder order = orderMapper.selectByPrimaryKey(orderId);
		order.setShippingName(shippingName);
		order.setShippingCode(shippingCode);
		order.setUpdateTime(new Date());
		order.setStatus(4);
		order.setConsignTime(new Date());
		orderMapper.updateByPrimaryKey(order);
		return StoreResult.ok();
	}

	@Override
	public EUDataGridResult getOrderList(int page, int rows) {
		TbOrderExample example = new TbOrderExample();
		//设置分页
		PageHelper.startPage(page, rows);
		List<TbOrder> orderList = orderMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbOrder> pageInfo = new PageInfo<TbOrder>(orderList);
		long total = pageInfo.getTotal();
		EUDataGridResult result = new EUDataGridResult(total,orderList);
		return result;
	}

	@Override
	public StoreResult getOrderItem(String orderId) {
		TbOrderItemExample example = new TbOrderItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<TbOrderItem> orderItems = orderItemMapper.selectByExample(example);
		if(orderItems!=null&&orderItems.size()>0) {
			return StoreResult.ok(orderItems);
		}
		return StoreResult.build(400, "该订单下无详细商品");
	}

}
