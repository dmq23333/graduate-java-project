package com.store.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class TestShowPageController {
	
@RequestMapping("/test")
public String showTestPage() {
	return"shippingList";
}
}
