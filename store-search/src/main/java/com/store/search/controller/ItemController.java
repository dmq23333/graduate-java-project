package com.store.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.StoreResult;
import com.store.search.service.ItemService;

/**
 * 
 * @author 74160
 *索引库维护
 */


@Controller
@RequestMapping("/manager")
public class ItemController {
	@Autowired
	private ItemService itemService;

	//导入所有数据
	@RequestMapping("/importall")
	@ResponseBody
	public StoreResult importAllItems() {
		StoreResult result = itemService.importAllItems();
		
		return result;
	}
	
}
