package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;
import com.store.pojo.TbOrder;
import com.store.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired 
	private OrderService orderService;
	
	@RequestMapping(value = "/order/delete", method = RequestMethod.POST)
	@ResponseBody
	public StoreResult deleteOrderById(@RequestParam("ids") String[] orderId) {
		StoreResult result = orderService.deleteOrder(orderId);
		return result;
	}
	
	@RequestMapping(value = "/order/consign", method = RequestMethod.POST)
	@ResponseBody
	public StoreResult consignOrderById(String orderId,String shippingName,String shippingCode) {
		StoreResult result = orderService.consignOrder(orderId, shippingName, shippingCode);
		return result;
	}
	
	@RequestMapping("/order/list")
	@ResponseBody
	public EUDataGridResult getOrderList(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="30")Integer rows)throws Exception {
		
		EUDataGridResult result = orderService.getOrderList(page, rows);
		return result;
	}
	
	@RequestMapping(value = "/order/order-item", method = RequestMethod.POST)
	@ResponseBody
	public StoreResult getOrderItem(@RequestParam("ids") String orderId, Model model) {
		StoreResult resultString = orderService.getOrderItem(orderId);
		model.addAttribute("itemJson",resultString);
		return resultString;
	}
}
