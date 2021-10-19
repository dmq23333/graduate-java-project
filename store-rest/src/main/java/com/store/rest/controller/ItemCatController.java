package com.store.rest.controller;

import org.slf4j.spi.LocationAwareLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.JsonUtils;
import com.store.common.pojo.StoreResult;
import com.store.rest.pojo.CatResult;
import com.store.rest.service.ItemCatService;

@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	/*
	 * @RequestMapping(value="/itemcat/list",
	 * produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	 * 
	 * @ResponseBody public String getItemCatList(String callBack) { CatResult
	 * catResult = itemCatService.getItemCatList(); //把pojo转换成json字符串 String json =
	 * JsonUtils.objectToJson(catResult); //拼装返回值 String resultString
	 * =callBack+"("+json+")"; return resultString; }
	 */
	 
	
	@RequestMapping("/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;}
	@RequestMapping("/itemcat/name/{cId}")
	@ResponseBody
	public StoreResult getItemCatName(@PathVariable long cId) {
		return StoreResult.ok(itemCatService.getCatNameByCid(cId));
	}
}
