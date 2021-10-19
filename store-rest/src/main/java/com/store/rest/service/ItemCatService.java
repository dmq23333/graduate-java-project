package com.store.rest.service;

import com.store.common.pojo.StoreResult;
import com.store.rest.pojo.CatResult;

public interface ItemCatService {
	CatResult getItemCatList();
	String getCatNameByCid(long cId);
}
