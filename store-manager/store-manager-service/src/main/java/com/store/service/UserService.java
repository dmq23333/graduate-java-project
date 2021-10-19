package com.store.service;

import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;

public interface UserService {
	
	EUDataGridResult getUserList(int page, int rows);
	StoreResult deleteUserById(long[] userId);
	

}
