package com.store.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.store.common.pojo.JsonUtils;
import com.store.common.pojo.StoreResult;
import com.store.common.utils.HttpClientUtil;
import com.store.pojo.TbContent;
import com.store.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	@Override
	public String getContentList() {
		//调用服务层服务
		String resultString = HttpClientUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL); 
		//把字符串转换成StoreResult
		try {
			StoreResult storeResult = StoreResult.formatToList(resultString, TbContent.class);
			//取内容列表
			List<TbContent> contentList = (List<TbContent>) storeResult.getData();
			//创建一个jsp页面要求的pojo对象
			List<Map> resultList = new ArrayList<>(); 
			for (TbContent tbContent : contentList) {
				Map map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbContent.getPic2());
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

}
