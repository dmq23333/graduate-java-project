package com.store.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.common.pojo.StoreResult;
import com.store.mapper.TbItemCatMapper;
import com.store.mapper.TbItemMapper;
import com.store.pojo.TbItem;
import com.store.pojo.TbItemCat;
import com.store.pojo.TbItemCatExample;
import com.store.pojo.TbItemExample;
import com.store.pojo.TbItemCatExample.Criteria;
import com.store.rest.pojo.CatNode;
import com.store.rest.pojo.CatResult;
import com.store.rest.pojo.Item;
import com.store.rest.pojo.ProductsResult;
import com.store.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		//查询分类列表的方法
		catResult.setData(getCatList(0));
		return catResult;
	}
	
	private List<?> getCatList(long parentId) {
		//创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		List resultList = new ArrayList<>();
		//向list中添加节点
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				
				resultList.add(catNode);
			//如果是叶子节点
			} else {
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}

	@Override
	public String getCatNameByCid(long cId) {
		TbItemCat itemCat = tbItemCatMapper.selectByPrimaryKey(cId);
		
		return itemCat.getName();
	}
	
	
}
