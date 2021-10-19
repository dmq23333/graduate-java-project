package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;
import com.store.pojo.TbItem;
import com.store.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return  tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="30")Integer rows)throws Exception {
		
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public StoreResult createItem(TbItem tbItem,String desc,String itemParams) throws Exception {
		
		StoreResult result = itemService.createItem(tbItem,desc,itemParams);
		return result;
		
	}
	
	@RequestMapping("/item/desc/{itemId}")
	@ResponseBody
	public StoreResult getItemDesc(@PathVariable Long itemId){
		StoreResult result = itemService.getItemDesc(itemId);
		return result;
	}
	
	@RequestMapping("/item/param/{itemId}")
	@ResponseBody
	public StoreResult getItemparam(@PathVariable Long itemId){
		StoreResult result = itemService.getItemParam(itemId);
		return result;
	}
	
	@RequestMapping(value = "/item/delete", method = RequestMethod.POST)
	@ResponseBody
	public StoreResult deleteItemById(@RequestParam("ids") long[] itemId){
		StoreResult result = itemService.deleteItemById(itemId);
		return result;
	}
	@RequestMapping(value = "/rest/item/update", method = RequestMethod.POST)
	@ResponseBody
	public StoreResult updateItem(TbItem tbItem,String desc,String itemParams) {
		StoreResult result = itemService.updateItem(tbItem, desc, itemParams);
		return result;
	}
	// 上架商品
    @RequestMapping(value = "/rest/item/reshelf", method = RequestMethod.POST)
    @ResponseBody
    private StoreResult upperoffItem(@RequestParam("ids") long []itemId) {
    	StoreResult result = itemService.upperOfItem(itemId);
        return result;
    }

    // 下架商品
    @RequestMapping(value = "/rest/item/instock", method = RequestMethod.POST)
    @ResponseBody
    private StoreResult dropoffItem(@RequestParam("ids") long []itemId) {
    	StoreResult result = itemService.dropOfItem(itemId);
        return result;
    }
}


