package com.store.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.StoreResult;
import com.store.common.utils.ExceptionUtil;
import com.store.order.pojo.Order;
import com.store.order.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public StoreResult createOrder(@RequestBody Order order) {
		try {
			StoreResult storeResult = orderService.creatOrder(order, order.getOrderItems(), 
					order.getOrderShipping());
			return storeResult;
		} catch (Exception e) {
			e.printStackTrace();
			return StoreResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping("/info/{orderId}")
	@ResponseBody
	public StoreResult getOrderInfoById(@PathVariable String orderId){
		StoreResult result = orderService.getOrderInfoById(orderId);
		return result;
	}
	
	@RequestMapping("/list/{userId}/{page}/{count}")
	@ResponseBody
	public StoreResult getOrderInfoByPage(@PathVariable long userId,@PathVariable Integer page,@PathVariable Integer count ) {
		StoreResult result = orderService.getOrderInfoByPage(userId, page, count);
		return result;
	}
	
	@RequestMapping(value="/changeStatus",method = RequestMethod.POST)
	@ResponseBody
	public StoreResult changeOrderStatus(@RequestBody Map<String, Object> params) {
		try {
			String orderId = params.get("orderId").toString();
			int status = Integer.parseInt(params.get("status").toString());
			StoreResult result = orderService.changeOrderStatus(orderId, status);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return StoreResult.build(400, ExceptionUtil.getStackTrace(e));
		}

		
	}
}
