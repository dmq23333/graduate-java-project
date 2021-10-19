package com.store.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.portal.pojo.SearchResult;
import com.store.portal.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String search(@RequestParam("q") String queryString,
			@RequestParam(defaultValue="1") Integer page,Model model) {
		//解决搜索乱码
		
		if(queryString !=null) {
			try {
				queryString = new String(queryString.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		SearchResult searchResult = searchService.search(queryString, page);
		//向页面传递参数
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages",searchResult.getPageCount());
		model.addAttribute("page",page);
		model.addAttribute("itemList", searchResult.getItemList());
		return "search";
	}
	

}
