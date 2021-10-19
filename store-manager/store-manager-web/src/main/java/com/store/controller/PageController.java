package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	/*打开首页 */
	@RequestMapping("/")
	public String showIndex() {
		return "login";
	}
	/*打开其它页面*/
	@RequestMapping("/{page}")
	public String showpage(@PathVariable String page){
		
		return page;
	}
}
