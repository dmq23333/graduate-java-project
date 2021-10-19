package com.store.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.store.common.pojo.StoreResult;
import com.store.common.utils.HttpClientUtil;
import com.store.pojo.TbUser;
import com.store.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	
	@Override
	public TbUser getUserByToken(String token) {
		
		try {
			//调用sso系统的服务
		String jsonString = HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+token);
		//把json数据转换
		StoreResult result = StoreResult.formatToPojo(jsonString, TbUser.class);
		if(result.getStatus()==200) {
			TbUser user = (TbUser) result.getData();
			return user;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
