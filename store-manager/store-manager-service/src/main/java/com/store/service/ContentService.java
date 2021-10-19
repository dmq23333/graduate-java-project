package com.store.service;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;
import com.store.pojo.TbContent;

public interface ContentService {
	EUDataGridResult getContentList(long categoryId,int page, int rows);
	StoreResult insertContent(TbContent tbContent);
	StoreResult editContent(TbContent tbContent);
}
