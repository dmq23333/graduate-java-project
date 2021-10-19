package com.store.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.common.utils.CookieUtils;
import com.store.pojo.TbOrder;
import com.store.pojo.TbUser;
import com.store.portal.pojo.Order;
import com.store.portal.service.OrderService;
import com.store.portal.service.UserService;

@Controller
@RequestMapping("/user")
public class UserInfoController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/order-info")
	public String showUserOrder(Model model,
			HttpServletRequest request, HttpServletResponse response) {
		List<Order> orderList = orderService.getOrderListByUserId(request, response);
		model.addAttribute("orderList", orderList);
		return "my-orders";
	}
	
	@RequestMapping("/order-comment")
	public String showOrderConmment() {
		return "my-order-comment";
	}
	
	@RequestMapping("/user-info")
	public String showMyInfo(Model model,
			HttpServletRequest request,HttpServletResponse response) {
		String tokenString = CookieUtils.getCookieValue(request, "Store_Token");
		TbUser user = userService.getUserByToken(tokenString);
		model.addAttribute("tbUser", user);
		return "my-info";
	}
	
	@RequestMapping("/single-order/{orderId}")
	public String showSingleOrder(@PathVariable String orderId,Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Order order = orderService.getOrderByOrderId(orderId, request, response);
		model.addAttribute("orderItemList", order.getOrderItems());
		model.addAttribute("totalPrice", order.getPayment());
		model.addAttribute("shippingConfirm", order.getOrderShipping());
		return "order-info";
	}
	
}
