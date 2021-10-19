package com.store.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.pojo.TbUser;
import com.store.sso.service.UserService;
/**
 * 
 * @author 74160
 *    页面跳转
 */

@Controller
@RequestMapping("/page")
public class PageController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping("/login")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	@RequestMapping("/update")
	public String showUpdate(HttpServletRequest request, Model model) {
		TbUser user = userService.getUserInfo(request);
		model.addAttribute("tbUser", user);
		return "update";
	}

}
