package com.store.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.common.pojo.StoreResult;
import com.store.mapper.TbItemCatMapper;
import com.store.mapper.TbItemDescMapper;
import com.store.mapper.TbItemMapper;
import com.store.mapper.TbItemParamItemMapper;
import com.store.pojo.TbItem;
import com.store.pojo.TbItemCat;
import com.store.pojo.TbItemDesc;
import com.store.pojo.TbItemExample;
import com.store.pojo.TbItemParamItem;
import com.store.pojo.TbItemParamItemExample;
import com.store.pojo.TbItemParamItemExample.Criteria;
import com.store.rest.pojo.Item;
import com.store.rest.pojo.ProductsResult;
import com.store.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public StoreResult getItemBasicInfo(long itemId) {
		//根据商品id查询商品信息
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		return StoreResult.ok(item);
	}

	@Override
	public StoreResult getItemDesc(long itemId) {
		//根据商品id查询商品描述信息
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		return StoreResult.ok(itemDesc);
	}

	@Override
	public StoreResult getItemParam(long itemId) {
		//根据商品id查询商品规格参数信息
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0) {
			TbItemParamItem paramItem = list.get(0);
			return StoreResult.ok(paramItem);
		}
		return StoreResult.build(400, "无此商品规格");
	}

}
