package com.store.service.impl;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;
import com.store.mapper.TbContentMapper;
import com.store.pojo.TbContent;
import com.store.pojo.TbContentExample;
import com.store.pojo.TbContentExample.Criteria;
import com.store.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Override
	public EUDataGridResult getContentList(long categoryId,int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//设置查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		//获取查询结果
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EUDataGridResult euResult = new EUDataGridResult();
		euResult.setRows(list);
		euResult.setTotal(pageInfo.getTotal());
		return euResult;
	}

	@Override
	public StoreResult insertContent(TbContent tbContent) {
		//补全pojo内容
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		tbContentMapper.insert(tbContent);
		return StoreResult.ok();
	}

	@Override
	public StoreResult editContent(TbContent tbContent) {
		// 补全pojo
		tbContent.setCreated(new Date());
		tbContentMapper.updateByPrimaryKey(tbContent);
		return StoreResult.ok();
	}

}
