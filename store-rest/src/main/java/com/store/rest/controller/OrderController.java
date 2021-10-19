package com.store.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.StoreResult;
import com.store.rest.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
			
			
	@RequestMapping("/list/{userId}")
	@ResponseBody
	public StoreResult getOrderListByUserId(@PathVariable long userId) {
		StoreResult result = orderService.getOrderListByUserId(userId);
		return result;
	}
	
	@RequestMapping("/single/{orderId}")
	@ResponseBody
	public StoreResult getOrderByOrderId(@PathVariable String orderId) {
		StoreResult result = orderService.getOrderByOrderId(orderId);
		return result;
	}
	
	@RequestMapping(value="/comment",method=RequestMethod.POST)
	@ResponseBody
	public StoreResult updateOrderComment(@RequestBody Map<String, Object>params) {
		String orderId = params.get("orderId").toString();
		String message = params.get("message").toString();
		StoreResult result = orderService.updateOrderComponent(orderId, message);
		return result;
	}

}
