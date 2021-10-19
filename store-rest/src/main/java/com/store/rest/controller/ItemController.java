package com.store.rest.controller;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.StoreResult;
import com.store.common.utils.ExceptionUtil;
import com.store.rest.service.ItemService;
import com.store.rest.service.ProductsService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ProductsService productsService;
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public StoreResult getItemBasicInfo(@PathVariable Long itemId){
		StoreResult result = itemService.getItemBasicInfo(itemId);
		return result;
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public StoreResult getItemDesc(@PathVariable Long itemId){
		StoreResult result = itemService.getItemDesc(itemId);
		return result;
	}
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public StoreResult getItemparam(@PathVariable Long itemId){
		StoreResult result = itemService.getItemParam(itemId);
		return result;
	}
	
	@RequestMapping("/cid/list/{cId}")
	@ResponseBody
	public StoreResult getItemListByCid(@PathVariable long cId) {
		try {
			StoreResult result = productsService.getItemByCatId(cId,1,60);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return StoreResult.build(400, ExceptionUtil.getStackTrace(e));
		}
	}
}
