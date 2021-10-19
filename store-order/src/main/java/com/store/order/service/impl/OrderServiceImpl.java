package com.store.order.service.impl;

import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.common.pojo.StoreResult;
import com.store.common.utils.ExceptionUtil;
import com.store.mapper.TbItemMapper;
import com.store.mapper.TbOrderItemMapper;
import com.store.mapper.TbOrderMapper;
import com.store.mapper.TbOrderShippingMapper;
import com.store.order.dao.JedisClient;
import com.store.order.dao.JedisClientImpl;
import com.store.order.pojo.UserOrder;
import com.store.order.service.OrderService;
import com.store.pojo.TbItem;
import com.store.pojo.TbOrder;
import com.store.pojo.TbOrderExample;
import com.store.pojo.TbOrderExample.Criteria;
import com.store.pojo.TbOrderItem;
import com.store.pojo.TbOrderShipping;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private TbItemMapper itemMapper;
	
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;

	@Override
	public StoreResult creatOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
		//向订单表中插入记录
		//获得订单号
		String jediString = jedisClient.get(ORDER_GEN_KEY);
		if(StringUtils.isBlank(jediString)) {
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		long orderId = jedisClient.incr(ORDER_GEN_KEY);
		//补全pojo属性
		order.setOrderId(orderId+1+"");
		//状态1.未付款2.已付款3.未发货4.已发货5.交易成功6.交易关闭
		order.setStatus(1);
		Date date = new Date();
		order.setUpdateTime(date);
		order.setCreateTime(date);
		//0.未评价1.已评价
		order.setBuyerRate(0);
		//插入记录
		orderMapper.insert(order);	
		
		//插入订单明细
		//补全订单明细
		for (TbOrderItem tbOrderItem : itemList) {
			//取订单明细ID
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			tbOrderItem.setId(orderDetailId+"");
			tbOrderItem.setOrderId(orderId+"");
			//向订单明细表插入记录
			orderItemMapper.insert(tbOrderItem);
		}
		//减少商品库存
			for(TbOrderItem tbOrderItem : itemList) {
				String s = tbOrderItem.getItemId();
				long itemId = Long.parseLong(s);
				TbItem item = itemMapper.selectByPrimaryKey(itemId);
				item.setNum(item.getNum()-tbOrderItem.getNum());
				itemMapper.updateByPrimaryKey(item);
			}
		//插入物流信息
		orderShipping.setOrderId(orderId+"");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		return StoreResult.ok(orderId);
	}

	@Override
	public StoreResult getOrderInfoById(String orderId) {
		TbOrder order = orderMapper.selectByPrimaryKey(orderId);
		return StoreResult.ok(order);
	}

	@Override
	public StoreResult getOrderInfoByPage(long userId, int page, int count) {
		
		//设置分页
		PageHelper.startPage(page, count);
		//查询
		List<TbOrder> orderList = getOrderInfoByUserId(userId);
		List<UserOrder> resultList = new ArrayList<UserOrder>();
		for (TbOrder order : orderList) {
			UserOrder userOrder = new UserOrder();
			userOrder.setBuyerMessage(order.getBuyerMessage());
			userOrder.setBuyerNick(order.getBuyerNick());
			userOrder.setCreateTime(order.getCreateTime());
			userOrder.setOrderId(order.getOrderId());
			userOrder.setPayment(order.getPayment());
			userOrder.setPaymentType(order.getPaymentType());
			userOrder.setPostFee(order.getPostFee());
			userOrder.setStatus(order.getStatus());
			userOrder.setUserId(order.getUserId());
			resultList.add(userOrder);
		}
		
 		return StoreResult.ok(resultList);
	}

	@Override
	public List<TbOrder> getOrderInfoByUserId(long userId) {
		//创建查询条件
		TbOrderExample example = new TbOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<TbOrder> orderList = orderMapper.selectByExample(example);
		if(orderList.size()>0&&orderList!=null) {
			return orderList;
		}
		return new ArrayList<TbOrder>();
	}

	@Override
	public StoreResult changeOrderStatus(String orderId, int status) {
		try {
			//查询更改的订单
			TbOrder order = orderMapper.selectByPrimaryKey(orderId);
			//修改信息
			order.setStatus(status);
			order.setPaymentTime(new Date());
			//更细数据库
			orderMapper.updateByPrimaryKey(order);
		} catch (Exception e) {
			e.printStackTrace();
			return StoreResult.build(400, ExceptionUtil.getStackTrace(e));
		}
		return StoreResult.ok();
	}

}
