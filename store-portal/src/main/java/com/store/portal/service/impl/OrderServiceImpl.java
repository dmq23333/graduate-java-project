package com.store.portal.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.store.common.pojo.JsonUtils;
import com.store.common.pojo.StoreResult;
import com.store.common.utils.CookieUtils;
import com.store.common.utils.HttpClientUtil;
import com.store.pojo.TbUser;
import com.store.portal.pojo.Order;
import com.store.portal.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	@Value("${ORDER_LIST_UID}")
	private String ORDER_LIST_UID;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ORDER_INFO_ID}")
	private String ORDER_INFO_ID;

	@Override
	public String createOrder(Order order) {
		
		//补全用户信息
		
		//调用order系统的服务提交订单
		String json = JsonUtils.objectToJson(order);
		String jsonResultString = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREATE_URL, json);
		//把返回的json格式字符串转换
		StoreResult storeResult = StoreResult.format(jsonResultString);
		if(storeResult.getStatus()==200) {
			Object orderId = storeResult.getData();
			return String.valueOf(orderId);
		}
		return "";
	}

	@Override
	public List<Order> getOrderListByUserId(HttpServletRequest request, HttpServletResponse response) {
		//从cookie中获取登陆的token
		String tokenString = CookieUtils.getCookieValue(request, "Store_Token");
		//根据token调用sso服务获取userId
		String jsonUser= HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+tokenString);
		StoreResult result = StoreResult.formatToPojo(jsonUser, TbUser.class);
		TbUser user = (TbUser) result.getData();
		long userId = user.getId();
		//调用rest的服务获取orderList
		String orderString = HttpClientUtil.doGet(REST_BASE_URL+ORDER_LIST_UID+userId);
		//json格式字符串转化
		StoreResult storeResult = StoreResult.formatToList(orderString, Order.class);
		if(storeResult.getStatus()==200) {
			List<Order> orderList = (List<Order>) storeResult.getData();
			return orderList;
		}
		return null;
	}

	@Override
	public Order getOrderByOrderId(String orderId, HttpServletRequest request, HttpServletResponse response) {
		String orderString = HttpClientUtil.doGet(REST_BASE_URL+ORDER_INFO_ID+orderId);
		//把json字符串转化为json对象
		StoreResult storeResult = StoreResult.formatToPojo(orderString, Order.class);
		if(storeResult.getStatus()==200) {
			Order order = (Order) storeResult.getData();
			return order;
		}
		
		return null;
	}
	
}
