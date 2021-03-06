package com.store.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.StoreResult;
import com.store.common.utils.ExceptionUtil;
import com.store.pojo.TbUser;
import com.store.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
		StoreResult result = null;
		//参数有效性校验
		if(StringUtils.isBlank(param)) {
			result = StoreResult.build(400, "校验内容不能为空");
		}
		if(type==null) {
			result = StoreResult.build(400, "校验内容类型不能为空");
		}
		if(type!=1&&type!=2&&type!=3) {
			result = StoreResult.build(400, "校验内容类型错误");
		}
		//校验出错
				if (null != result) {
					if (null != callback) {
						MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
						mappingJacksonValue.setJsonpFunction(callback);
						return mappingJacksonValue;
					} else {
						return result; 
					}
				}
				//调用服务
				try {
					result = userService.checkData(param, type);
					
				} catch (Exception e) {
					result = StoreResult.build(500, ExceptionUtil.getStackTrace(e));
				}
				
				if (null != callback) {
					MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
					mappingJacksonValue.setJsonpFunction(callback);
					return mappingJacksonValue;
				} else {
					return result; 
				}
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public StoreResult createUser(TbUser user) {
		try {
			StoreResult result = userService.createUser(user);
			return result;
		} catch (Exception e) {
			return StoreResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public StoreResult updateUser(TbUser user,HttpServletRequest request) {
		try {
			StoreResult result = userService.updateUser(user,request);
			return result;
		} catch (Exception e) {
			return StoreResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public StoreResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			StoreResult result = userService.userLogin(username, password, request, response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return StoreResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		StoreResult storeResult = null;
		try {
			storeResult = userService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			storeResult = StoreResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		//判断是否为jsonp调用
		if(StringUtils.isBlank(callback)) {
			return storeResult;
		}else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(storeResult);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}
	
	@RequestMapping("/logout/{token}")
	@ResponseBody
	public Object userLogout(@PathVariable String token, String callback) {
		StoreResult result = null;try {
			result = userService.userLogout(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = StoreResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		//判断是否为jsonp调用
		if(StringUtils.isBlank(callback)) {
			return result;
		}else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		
	}
}
