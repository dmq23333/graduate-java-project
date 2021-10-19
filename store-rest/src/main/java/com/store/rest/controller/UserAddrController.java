package com.store.rest.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.JsonUtils;
import com.store.common.pojo.StoreResult;
import com.store.common.utils.ExceptionUtil;
import com.store.pojo.TbUserShipping;
import com.store.rest.service.UserAddrService;

@Controller
public class UserAddrController {
	
	@Autowired
	private UserAddrService userAddrService;
	
	/*
	 * 获取用户的收货地址列表
	 */
	@RequestMapping("/address/list/{userId}")
	@ResponseBody
	public StoreResult getUserShippingAddr(@PathVariable Long userId) {
		StoreResult result = userAddrService.getUserShipingByUserId(userId);
		return result;
	} 
	
	@RequestMapping(value="/address/create",method=RequestMethod.POST)
	@ResponseBody
	public StoreResult createUserShipping(@RequestBody TbUserShipping userShipping) {
		try {
			StoreResult result = userAddrService.createUserShippingAddr(userShipping);
			if(result.getStatus()!=200) {
				return StoreResult.build(400, "");
			}
			return result;
		} catch (Exception e) {
			return StoreResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping(value="/address/update",method=RequestMethod.POST)
	@ResponseBody
	public StoreResult updateUserShipping(@RequestBody TbUserShipping userShipping) {
		try {
			StoreResult result = userAddrService.updateUserShippingAddr(userShipping);
			return result;
		} catch (Exception e) {
			return StoreResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping("/address/shipping/{shippingId}")
	@ResponseBody
	public StoreResult getUserShippingAddrById(@PathVariable Long shippingId) {
		StoreResult result = userAddrService.getUserShippingByID(shippingId);
		return result;
	} 

}
