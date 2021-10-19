package com.store.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.common.pojo.StoreResult;
import com.store.pojo.TbUser;

public interface UserService {
	StoreResult checkData(String content, Integer type);
	StoreResult createUser(TbUser user);
	StoreResult userLogin(String username,String password, 
			HttpServletRequest request, HttpServletResponse response);
	StoreResult getUserByToken(String token);
	StoreResult userLogout(String token);
	StoreResult updateUser(TbUser user, HttpServletRequest request);
	TbUser getUserInfo(HttpServletRequest request);
}
