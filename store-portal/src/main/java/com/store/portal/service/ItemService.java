package com.store.portal.service;

import com.store.common.pojo.StoreResult;
import com.store.portal.pojo.ItemInfo;
import com.store.portal.pojo.ProductsResult;

public interface ItemService {
	
	ItemInfo getItemById(long itemId);
	String getItemDescById(long itemId);
	String getItemParamById(long itemId);
	ProductsResult getItemListByCid(long cId);
	ProductsResult getItemListByCid(long cId, int page, int rows);
	String getItemCatNameByCid(long cId);

}
