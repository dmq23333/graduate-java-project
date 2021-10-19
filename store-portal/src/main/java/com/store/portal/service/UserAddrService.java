package com.store.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.common.pojo.StoreResult;
import com.store.pojo.TbUserShipping;

public interface UserAddrService {
	
	List<TbUserShipping> getUserShippingList(HttpServletRequest request,
			HttpServletResponse response);
	StoreResult createUserShipping(HttpServletRequest request,
			TbUserShipping userShipping);
	StoreResult updateUserShipping(HttpServletRequest request,
			TbUserShipping userShipping);
	TbUserShipping confirmUserShipping(HttpServletRequest request,
			HttpServletResponse response,long shippingId);
	TbUserShipping getConfirmUserShipping(HttpServletRequest request,
			HttpServletResponse response);

}
