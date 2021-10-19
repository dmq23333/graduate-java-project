package com.store.rest.service;

import com.store.common.pojo.StoreResult;

public interface ItemService {

	StoreResult getItemBasicInfo(long itemId);
	StoreResult getItemDesc(long itemId); 
	StoreResult getItemParam(long itemId);
}
