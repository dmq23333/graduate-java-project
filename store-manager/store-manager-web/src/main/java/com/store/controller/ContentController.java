package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;
import com.store.pojo.TbContent;
import com.store.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getContentList(long categoryId,Integer page,Integer rows) {
		EUDataGridResult result = contentService.getContentList(categoryId,page, rows);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public StoreResult insertContent(TbContent tbContent) {
		StoreResult result = contentService.insertContent(tbContent);
		return result;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public StoreResult editContent(TbContent tbContent) {
		StoreResult result = contentService.editContent(tbContent);
		return result;
	}
}
