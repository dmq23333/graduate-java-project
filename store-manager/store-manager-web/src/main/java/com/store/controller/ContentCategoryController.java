package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.EUTreeNode;
import com.store.common.pojo.StoreResult;
import com.store.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCategory(@RequestParam(value="id",defaultValue="0")
										long parentId){
		List resultList = contentCategoryService.getCategoryList(parentId);
		
		return resultList;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public StoreResult createContentCategory(long parentId,String name) {
		StoreResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public StoreResult deleteContentCategory(long id) {
		StoreResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public StoreResult updateContentCategory(long id,String name) {
		StoreResult result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
}
