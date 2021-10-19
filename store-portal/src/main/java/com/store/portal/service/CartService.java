package com.store.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.common.pojo.StoreResult;
import com.store.portal.pojo.CartItem;

public interface CartService {
	StoreResult addCartItem(long itemId, int num, 
			HttpServletRequest request, HttpServletResponse response);
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	StoreResult cartNumUpdate(long itemId, int num, 
			HttpServletRequest request, HttpServletResponse response);
	StoreResult deleteCartItem(long itemId,HttpServletRequest request, HttpServletResponse response);
}
