package com.store.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.mapper.TbContentMapper;
import com.store.pojo.TbContent;
import com.store.pojo.TbContentExample;
import com.store.pojo.TbContentExample.Criteria;
import com.store.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper tbContentMapper; 

	@Override
	public List<TbContent> getContentList(long contentCid) {
		//创建查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		//执行查询
		List<TbContent> list = tbContentMapper.selectByExample(example);
		
		return list;
	}

}
