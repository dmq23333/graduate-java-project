package com.store.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.store.common.pojo.JsonUtils;
import com.store.common.pojo.StoreResult;
import com.store.common.utils.CookieUtils;
import com.store.common.utils.HttpClientUtil;
import com.store.pojo.TbItem;
import com.store.portal.pojo.CartItem;
import com.store.portal.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		//从cookie中取商品列表
		String cartJson = CookieUtils.getCookieValue(request, "Store_Cart", true);
		if(cartJson==null) {
			return new ArrayList<CartItem>();
		}
		//json转换成商品列表
		try {
			List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CartItem>();
		}
	}
	
	@Override
	public StoreResult addCartItem(long itemId, int num, 
			HttpServletRequest request, HttpServletResponse response) {
		//取商品信息
		CartItem cartItem = null;
		
		//取购物车商品
		List<CartItem> cartList = getCartItemList(request);
		//判断购物车是否有该商品
		for (CartItem cItem : cartList) {
			//存在此商品
			if(cItem.getId()==itemId) {
				//增加商品数量
				cItem.setNum(num+cItem.getNum());
				cartItem = cItem;
				break;
			}
		}
		//无此商品
		if(cartItem==null) {
			cartItem = new CartItem();
			//根据商品id查询商品基本信息
			String jsonString = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
			//把json数据转换成Java对象
			StoreResult storeResult = StoreResult.formatToPojo(jsonString, TbItem.class);
			if(storeResult.getStatus()==200) {
				TbItem tbItem = (TbItem) storeResult.getData();
				cartItem.setId(tbItem.getId());
				cartItem.setImage(tbItem.getImage()==null?"":tbItem.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setTitle(tbItem.getTitle());
				cartItem.setPrice(tbItem.getPrice());
			}
			//添加到购物车列表
			cartList.add(cartItem);
		}
		//把购物车列表写回cookie
		CookieUtils.setCookie(request, response, "Store_Cart", JsonUtils.objectToJson(cartList),true);
		return StoreResult.ok();
	}

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItems = getCartItemList(request);
		return cartItems;
	}

	@Override
	public StoreResult cartNumUpdate(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		
		//取商品信息
		CartItem cartItem = null;
		//取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		//判断购物车商品列表中是否存在此商品
		for (CartItem cItem : itemList) {
			//如果存在此商品
			if (cItem.getId() == itemId) {
				//增加商品数量
				cItem.setNum(num);
				cartItem = cItem;
				break;
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			//根据商品id查询商品基本信息。
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId); 
			//把json转换成java对象
			StoreResult storeResult = StoreResult.formatToPojo(json, TbItem.class);
			if (storeResult.getStatus() == 200) {
				TbItem item = (TbItem) storeResult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "":item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			//添加到购物车列表
			itemList.add(cartItem);
		}
		//把购物车列表写入cookie
		CookieUtils.setCookie(request, response, "Store_Cart", JsonUtils.objectToJson(itemList), true);
		
		return StoreResult.ok();
	}

	@Override
	public StoreResult deleteCartItem(long itemId,HttpServletRequest request, HttpServletResponse response) {
		//从cookie获取商品列表
		List<CartItem> cartList = getCartItemList(request);
		//从列表中找到此商品
		for (CartItem cartItem : cartList) {
			if(cartItem.getId()==itemId) {
				cartList.remove(cartItem);
				break;
			}
		}
		//把购物车列表重新写入cookie
		CookieUtils.setCookie(request, response, "Store_Cart", JsonUtils.objectToJson(cartList),true);
		return StoreResult.ok();
	}
	
	
	
}
