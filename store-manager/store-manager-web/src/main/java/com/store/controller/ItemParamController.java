package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;
import com.store.pojo.TbItemParam;
import com.store.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemParamItemList(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="30")Integer rows)throws Exception{
		EUDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public StoreResult getItemParamByCid(@PathVariable long itemCatId) {
		StoreResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public StoreResult insertItemParam(@PathVariable long cid,String paramData) {
		//创建pojo对象
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		StoreResult result = itemParamService.insertItemParam(tbItemParam);
		return result;
	}
	
	 	@RequestMapping("/delete")
	    @ResponseBody
	    public StoreResult deleteItemParam(@RequestParam("ids") List<Long> ids){
	        //测试下传输过来的参数是否正确
	        for (int i = 0; i < ids.size(); i++) {
	            System.out.println(ids.get(i));
	        }
	        System.out.println(ids.size());
	        return itemParamService.deleteItemParam(ids);
	 	}


}
