package com.store.service;

import java.util.List;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;
import com.store.pojo.TbItemParam;

public interface ItemParamService {
	EUDataGridResult getItemParamList(int page, int rows);
	StoreResult getItemParamByCid(long cid);
	StoreResult insertItemParam(TbItemParam tbItemParam);
	StoreResult deleteItemParam(List<Long> ids);
}
