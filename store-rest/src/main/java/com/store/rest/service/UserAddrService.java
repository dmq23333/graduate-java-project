package com.store.rest.service;



import com.store.common.pojo.StoreResult;
import com.store.pojo.TbUserShipping;

public interface UserAddrService {
		//根据用户id查询收货地址列表
		StoreResult getUserShipingByUserId(long userId);
		//新增用户收货地址
		StoreResult createUserShippingAddr(TbUserShipping userShipping);
		//根据收货地址列表id查询收货地址
		StoreResult getUserShippingByID(long shippingId);
		//修改收货地址列表
		StoreResult updateUserShippingAddr(TbUserShipping userShipping);
}
