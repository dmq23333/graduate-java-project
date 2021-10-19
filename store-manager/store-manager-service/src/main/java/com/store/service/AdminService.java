package com.store.service;



import com.store.common.pojo.StoreResult;

public interface AdminService {
	
	StoreResult adminLogin(String username,String password);

}
