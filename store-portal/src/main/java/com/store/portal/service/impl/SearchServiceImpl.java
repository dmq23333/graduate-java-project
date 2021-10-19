package com.store.portal.service.impl;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.store.common.pojo.StoreResult;
import com.store.common.utils.HttpClientUtil;
import com.store.portal.pojo.SearchResult;
import com.store.portal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchResult search(String queryString, int page) {
		//查询参数
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page+"");
		try {
			//调用Search发布的服务
		String jsonString = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
		//把字符串转换成Java对象
		StoreResult storeResult = StoreResult.formatToPojo(jsonString, SearchResult.class);
		if(storeResult.getStatus()==200) {
			SearchResult searchResult = (SearchResult) storeResult.getData();
			return searchResult;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
