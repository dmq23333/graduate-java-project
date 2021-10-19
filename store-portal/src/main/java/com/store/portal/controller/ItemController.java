package com.store.portal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.portal.pojo.ItemInfo;
import com.store.portal.pojo.ProductsResult;
import com.store.portal.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId,Model model) {
		ItemInfo item = itemService.getItemById(itemId);
		String catName = itemService.getItemCatNameByCid(item.getCid());
		model.addAttribute("item",item);
		model.addAttribute("catName", catName);
		return "item";
	}
	
	@RequestMapping(value="/item/desc/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		String itemDesc = itemService.getItemDescById(itemId);
		return itemDesc;
	}
	
	@RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		String itemDesc = itemService.getItemParamById(itemId);
		return itemDesc;
	}
	
	@RequestMapping("/products/{cId}")
	public String showProductsById(@PathVariable long cId,Model model) {
		ProductsResult productsResult = itemService.getItemListByCid(cId);
		model.addAttribute("categoryId", cId);
		model.addAttribute("categoryName", productsResult.getItemList().get(0).getCategory_name());
		model.addAttribute("totalPages", productsResult.getPageCount());
		model.addAttribute("itemList", productsResult.getItemList());
		model.addAttribute("page",1);
		model.addAttribute("currentPage", productsResult.getCurPage());
		return "products";
	}
}
