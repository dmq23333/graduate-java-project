package com.store.service;


import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;
import com.store.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	EUDataGridResult getItemList(int page, int rows);
	StoreResult createItem(TbItem tbItem,String desc,String itemParam)throws Exception;
	StoreResult getItemDesc(long itemId);
	StoreResult getItemParam(long itemId);
	StoreResult deleteItemById(long[] itemId);
	StoreResult dropOfItem(long[] itemId);
	StoreResult upperOfItem(long[] itemId);
	StoreResult updateItem(TbItem tbItem,String desc,String itemParam);
}
