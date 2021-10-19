package com.store.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.StoreResult;
import com.store.common.utils.ExceptionUtil;
import com.store.pojo.TbUserShipping;
import com.store.portal.service.UserAddrService;

@Controller
public class UserAddrController {
	@Autowired
	private UserAddrService userAddrService;
	
	//展示用户
	@RequestMapping("/shippingList")
	public String showShippingList(HttpServletRequest request,
			HttpServletResponse response,Model model) {
		List<TbUserShipping> userShippingList = userAddrService.getUserShippingList(request, response);
		model.addAttribute("userShippingList", userShippingList);
		return "shippingList";
	}

	@RequestMapping(value="/add/slist",method=RequestMethod.POST)
	@ResponseBody
	public StoreResult createUserShipping(HttpServletRequest request,TbUserShipping userShipping) {
		try {
			StoreResult storeResult = userAddrService.createUserShipping(request, userShipping);
			return storeResult;
		} catch (Exception e) {
			return StoreResult.build(400, ExceptionUtil.getStackTrace(e));
		}
		
	}
	
	@RequestMapping(value="/update/slist",method=RequestMethod.POST)
	@ResponseBody
	public StoreResult updateUserShipping(HttpServletRequest request,TbUserShipping userShipping) {
		try {
			StoreResult storeResult = userAddrService.updateUserShipping(request, userShipping);
			return storeResult;
		} catch (Exception e) {
			return StoreResult.build(400, ExceptionUtil.getStackTrace(e));
		}
		
	}
	
	@RequestMapping("/shipping/confirm/{shippingId}")
	@ResponseBody
	public String confirmUserShipping(@PathVariable Long shippingId,HttpServletRequest request,
			HttpServletResponse response) {
		
			TbUserShipping userShipping = userAddrService.confirmUserShipping(request,response, shippingId);
			
				return "forward:/order/order-cart.html";
			
			
			
		
	}
}
