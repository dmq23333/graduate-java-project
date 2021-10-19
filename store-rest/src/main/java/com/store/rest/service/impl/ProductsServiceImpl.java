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
import com.store.pojo.TbItemExample;
import com.store.pojo.TbItemExample.Criteria;
import com.store.rest.pojo.Item;
import com.store.rest.pojo.ProductsResult;
import com.store.rest.service.ProductsService;
@Service
public class ProductsServiceImpl implements ProductsService{
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public StoreResult getItemByCatId(long cId, int page, int rows) {
		ProductsResult productsResult = new ProductsResult();
		//设置查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(cId);
		List<TbItem> tbItemList = itemMapper.selectByExample(example);
		TbItemCat itemCat = tbItemCatMapper.selectByPrimaryKey(cId);
		List<Item> itemList = new ArrayList<>();
		if(tbItemList.size()>0&&tbItemList!=null) {
			for (TbItem tbItem : tbItemList) {
				Item item = new Item();
				item.setId(String.valueOf(tbItem.getId()));
				item.setTitle(tbItem.getTitle());
				item.setImage(tbItem.getImage());
				item.setPrice(tbItem.getPrice());
				item.setSell_point(tbItem.getSellPoint());
				item.setCategory_name(itemCat.getName());
				itemList.add(item);
			}
			productsResult.setItemList(itemList);
			long recordCount = itemList.size();
			long pageCount = recordCount/rows;
			productsResult.setCurPage(page);
			productsResult.setRecordCount(recordCount);
			productsResult.setPageCount(pageCount);
			return StoreResult.ok(productsResult);
		}
		
		return StoreResult.build(400, "此分类下无商品");
	}
}
