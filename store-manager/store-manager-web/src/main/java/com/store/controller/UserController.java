package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;
import com.store.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/list")
	@ResponseBody
	public EUDataGridResult getUserList(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="30")Integer rows) {
		EUDataGridResult result = userService.getUserList(page, rows);
		
		return result;
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	@ResponseBody
	public StoreResult deleteUserById(@RequestParam("ids") long[] userId) {
		StoreResult result = userService.deleteUserById(userId);
		return result;
				
	}
}
