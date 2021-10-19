package com.store.rest.service;

import com.store.common.pojo.StoreResult;

public interface ProductsService {
	StoreResult getItemByCatId(long cId, int page, int rows);
}
