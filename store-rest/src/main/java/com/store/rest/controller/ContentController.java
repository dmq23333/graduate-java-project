package com.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.common.pojo.StoreResult;
import com.store.common.utils.ExceptionUtil;
import com.store.pojo.TbContent;
import com.store.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public StoreResult getContentList(@PathVariable long contentCategoryId) {
		try {
			List<TbContent> contentList = contentService.getContentList(contentCategoryId);
			return StoreResult.ok(contentList);
		} catch (Exception e) {
			e.printStackTrace();
			return StoreResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		
		
	}
}
