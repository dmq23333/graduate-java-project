package com.store.service;

import java.util.List;

import com.store.pojo.TbItemCat;

public interface ItemCatService {
	public List<TbItemCat> getCatList(long parentId);
}
