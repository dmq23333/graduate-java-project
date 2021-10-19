package com.store.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.common.pojo.StoreResult;
import com.store.common.utils.ExceptionUtil;
import com.store.search.mapper.ItemMapper;
import com.store.search.pojo.Item;
import com.store.search.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;

	@Override
	public StoreResult importAllItems(){
		//查询商品列表
		List<Item> list = itemMapper.getItemList();
		//循环把商品信息写入索引库
		try {
		for (Item item : list) {
			//创建一个SolrInputDocument对象
			SolrInputDocument solrInputDocument = new SolrInputDocument();
			solrInputDocument.setField("id", item.getId());
			solrInputDocument.setField("item_title", item.getTitle());
			solrInputDocument.setField("item_sell_point", item.getSell_point());
			solrInputDocument.setField("item_price", item.getPrice());
			solrInputDocument.setField("item_image", item.getImage());
			solrInputDocument.setField("item_category_name", item.getCategory_name());
			solrInputDocument.setField("item_desc", item.getItem_des());
			//写入索引库
		
				
					solrServer.add(solrInputDocument);
				
		
		}
		//提交修改
		solrServer.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
			return StoreResult.build(500, ExceptionUtil.getStackTrace(e));
		} 
		return StoreResult.ok();
	}

}
