package com.store.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.common.utils.ExceptionUtil;
import com.store.pojo.TbUser;
import com.store.pojo.TbUserShipping;
import com.store.portal.pojo.CartItem;
import com.store.portal.pojo.Order;
import com.store.portal.service.CartService;
import com.store.portal.service.OrderService;
import com.store.portal.service.UserAddrService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private UserAddrService userAddrService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		//取收货地址列表
		TbUserShipping shippingConfirm = userAddrService.getConfirmUserShipping(request, response);
		//传递给页面
		model.addAttribute("shippingConfirm", shippingConfirm);
		//取购物车商品列表
		List<CartItem> cartItemList = cartService.getCartItemList(request, response);
		//传递给页面
		model.addAttribute("cartList", cartItemList);
		return "order-cart";
	}
	
	@RequestMapping("/create")
	public String createOrder(Order order, Model model, 
			HttpServletRequest request) {
		try {
			TbUser user = (TbUser) request.getAttribute("user");
			//补全用户信息
			order.setBuyerNick(user.getUsername());
			order.setUserId(user.getId());
			order.setPostFee("0");
			String orderId = orderService.createOrder(order);
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			
			return"success";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "创建订单出错，请稍后重试");
			return "error/exception";
		}
	}

}
