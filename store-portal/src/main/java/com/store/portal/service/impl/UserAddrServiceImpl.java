package com.store.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.store.common.pojo.JsonUtils;
import com.store.common.pojo.StoreResult;
import com.store.common.utils.CookieUtils;
import com.store.common.utils.HttpClientUtil;
import com.store.pojo.TbUser;
import com.store.pojo.TbUserShipping;
import com.store.portal.service.UserAddrService;

@Service
public class UserAddrServiceImpl implements UserAddrService{

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${USER_ADDR_LIST_URL}")
	private String USER_ADDR_LIST_URL;
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	@Value("${USER_ADDR_CREATE}")
	private String USER_ADDR_CREATE;
	@Value("${USER_ADDR_CONFIRM}")
	private String USER_ADDR_CONFIRM;
	@Value("${USER_ADDR_UPDATE}")
	private String USER_ADDR_UPDATE;
	
	@Override
	public List<TbUserShipping> getUserShippingList(HttpServletRequest request, HttpServletResponse response) {
		//从cookie中获取登陆的token
		String tokenString = CookieUtils.getCookieValue(request, "Store_Token");
		
		//根据token调用sso服务获取userId
		String jsonUser= HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+tokenString);
		StoreResult result = StoreResult.formatToPojo(jsonUser, TbUser.class);
			TbUser user = (TbUser) result.getData();
		long userId = user.getId();
		//调用服务获取用户收货地址列表
		String shippingJsonString = HttpClientUtil.doGet(REST_BASE_URL+USER_ADDR_LIST_URL+ userId);
		StoreResult result2 = StoreResult.formatToList(shippingJsonString, TbUserShipping.class);
		if(result2.getStatus()==200) {
			String jsonShipping = JsonUtils.objectToJson(result2.getData());
			List<TbUserShipping> list =JsonUtils.jsonToList(jsonShipping, TbUserShipping.class);
					return list;
		}
		
			
			return new ArrayList<TbUserShipping>();
		}
	

	@Override
	public StoreResult createUserShipping(HttpServletRequest request,TbUserShipping userShipping) {
		//从cookie中获取登陆的token
		String tokenString = CookieUtils.getCookieValue(request, "Store_Token");
		//根据token调用sso服务获取userId
		String jsonUser= HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+tokenString);
		StoreResult result = StoreResult.formatToPojo(jsonUser, TbUser.class);
		TbUser user = (TbUser) result.getData();
		long userId = user.getId();
		userShipping.setUserId(userId);
		//调用rest的服务向表中插入数据
		String url = REST_BASE_URL+USER_ADDR_CREATE;
		String postJson = JsonUtils.objectToJson(userShipping);
		String resultJson = HttpClientUtil.doPostJson(url, postJson);
		StoreResult storeResult = StoreResult.format(resultJson);
		if(storeResult.getStatus()==200) {
			return StoreResult.ok();
		}
		return StoreResult.build(500, "没有插入成功");
	}

	@Override
	public TbUserShipping confirmUserShipping(HttpServletRequest request,
			HttpServletResponse response,long shippingId) {
		//调用rest服务查询用户收货地址信息
		String resultJsonString = HttpClientUtil.doGet(REST_BASE_URL+USER_ADDR_CONFIRM+shippingId);
		StoreResult result = StoreResult.formatToPojo(resultJsonString, TbUserShipping.class);
		TbUserShipping userShipping = (TbUserShipping) result.getData();
		if(userShipping!=null) {
			CookieUtils.setCookie(request, response, "User-Shipping-Id", String.valueOf(userShipping.getId()));
			return userShipping;
		}
		return null;
	}

	@Override
	public StoreResult updateUserShipping(HttpServletRequest request, TbUserShipping userShipping) {
			//从cookie中获取登陆的token
				String tokenString = CookieUtils.getCookieValue(request, "Store_Token");
				//根据token调用sso服务获取userId
				String jsonUser= HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+tokenString);
				StoreResult result = StoreResult.formatToPojo(jsonUser, TbUser.class);
				TbUser user = (TbUser) result.getData();
				long userId = user.getId();
				userShipping.setUserId(userId);
				//调用rest的服务向表中插入数据
				String url = REST_BASE_URL+USER_ADDR_UPDATE;
				String jsonPost = JsonUtils.objectToJson(userShipping);
				String resultJson = HttpClientUtil.doPostJson(url, jsonPost);
				StoreResult storeResult = StoreResult.format(resultJson);
				if(storeResult.getStatus()==200) {
					return StoreResult.ok();
				}
				return StoreResult.build(500, "没有更新成功");
	}

	@Override
	public TbUserShipping getConfirmUserShipping(HttpServletRequest request,
			HttpServletResponse response) {
		//从cookie中获取确认的收货地址列表id
		String userShippingId = CookieUtils.getCookieValue(request, "User-Shipping-Id");
		long uid = Integer.parseInt(userShippingId);
		//调用rest服务查询用户收货地址信息
		String resultJsonString = HttpClientUtil.doGet(REST_BASE_URL+USER_ADDR_CONFIRM+uid);
		StoreResult result = StoreResult.formatToPojo(resultJsonString, TbUserShipping.class);
		TbUserShipping userShipping = (TbUserShipping) result.getData();
				
		return userShipping;
	}
	
	

}
