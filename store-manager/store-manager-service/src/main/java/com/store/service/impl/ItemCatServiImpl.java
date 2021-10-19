package com.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.mapper.TbItemCatMapper;
import com.store.pojo.TbItemCat;
import com.store.pojo.TbItemCatExample;
import com.store.pojo.TbItemCatExample.Criteria;
import com.store.service.ItemCatService;

@Service
public class ItemCatServiImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<TbItemCat> getCatList(long parentId) {
		
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		//根据parentId查询子节点
		criteria.andParentIdEqualTo(parentId);
		//返回子节点列表
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		
		return list;
	}

}
