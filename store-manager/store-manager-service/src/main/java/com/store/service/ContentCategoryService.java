package com.store.service;

import java.util.List;

import com.store.common.pojo.EUTreeNode;
import com.store.common.pojo.StoreResult;

public interface ContentCategoryService {
	
	List<EUTreeNode> getCategoryList(long parentId);
	StoreResult insertContentCategory(long parentId, String name);
	StoreResult deleteContentCategory(long id);
	StoreResult updateContentCategory(long id,String name);
}
